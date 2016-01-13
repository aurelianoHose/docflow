package com.netradio.junit4;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.netradio.web.controller.IndexController;

public class IndexControllerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private IndexController ind;

    private MockMvc mock;
    
    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(ind).build();
    }

    /**
     * Index
     * */
    @Test
    public void index() throws Exception {
        mock.perform(get("/index")).andExpect(redirectedUrl("/stream/list.html"));
    }

    /**
     * Login
     * */
    @Test
    public void login() throws Exception {
        mock.perform(get("/login.html")).andExpect(forwardedUrl("login"));
    }

    /**
     * Error
     * */
    @Test
    public void error() throws Exception {
        mock.perform(get("/error.html")).andExpect(model().attributeExists("error")).andExpect(forwardedUrl("error"));
    }
}
