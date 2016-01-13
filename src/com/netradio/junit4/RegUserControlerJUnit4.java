package com.netradio.junit4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.netradio.entity.User;
import com.netradio.web.controller.RegUserController;

@WebAppConfiguration
public class RegUserControlerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private RegUserController wac;

    private MockMvc mock;

    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(wac).build();
    }

    /**
     * Create new user for registration
     * */
    @Test
    public void user() throws Exception {
        mock.perform(get("/reguser/user")).andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    /**
     * Save user with valid data
     * */
    @Test
    public void save1() throws Exception {
        User user = new User();
        user.setName("test");
        user.setPasswd("test1");
        mock.perform(post("/reguser/save").flashAttr("user", user)).andExpect(
                redirectedUrl("/login.html"));
    }

    /**
     * Save user with not valid user name
     * */
    @Test
    public void save2() throws Exception {
        User user = new User();
        user.setName("#$%$#%$#%");
        user.setPasswd("test");
        mock.perform(post("/reguser/save").flashAttr("user", user)).andExpect(
                model().hasErrors());
    }

    /**
     * Save user with not valid password
     * */
    @Test
    public void save3() throws Exception {
        User user = new User();
        user.setName("test");
        user.setPasswd("!@#!$@!#%@%#");
        mock.perform(post("/reguser/save").flashAttr("user", user)).andExpect(
                model().hasErrors());
    }

    /**
     * Save user with empty data
     * */
    @Test
    public void save4() throws Exception {
        User user = new User();
        user.setName("");
        user.setPasswd("");
        mock.perform(post("/reguser/save").flashAttr("user", user)).andExpect(
                model().hasErrors());
    }

    /**
     * Save user with not valid data
     * */
    @Test
    public void save5() throws Exception {
        User user = new User();
        user.setName("!@!#@!@#");
        user.setPasswd("@#$@#$@$");
        mock.perform(post("/reguser/save").flashAttr("user", user)).andExpect(
                model().hasErrors());
    }
}
