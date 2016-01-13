package com.netradio.junit4;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.netradio.entity.User;
import com.netradio.web.controller.UsersController;
import com.netradio.web.controller.UsersController.UserQuickSearchState;

@WebAppConfiguration
public class UsersControllerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private UsersController us;

    private MockMvc mock;

    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(us).build();
    }

    /**
     * Get all users
     * */
    @Test
    public void users1() throws Exception {
        mock.perform(get("/users.html")).andExpect(
                model().attributeExists("users"));
    }

    /**
     * Get all users
     * */
    @Test
    public void users2() throws Exception {
        UserQuickSearchState qs = new UserQuickSearchState();
        qs.setSearch("user");
        us.setState(qs);
        mock.perform(get("/users.html")).andExpect(
                model().attributeExists("users"));
    }

    /**
     * Get user without id
     * */
    @Test
    public void user1() throws Exception {
        mock.perform(get("/user.html"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("roles"));
    }

    /**
     * Get user with exist id
     * */
    @Test
    public void user2() throws Exception {
        mock.perform(get("/user.html").param("id", "2"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("userRoles"))
                .andExpect(model().attributeExists("roles"));
    }

    /**
     * Get user with not exist id
     * */
    @Test
    public void user3() throws Exception {
        mock.perform(get("/user.html").param("id", "2000"))
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().attributeDoesNotExist("userRoles"))
                .andExpect(model().attributeExists("roles"));
    }

    /**
     * Delete user without id
     * */
    @Test
    public void delete1() throws Exception {
        mock.perform(get("/deleteuser"))
                .andExpect(redirectedUrl("/users.html"));
    }

    /**
     * Delete user with exist id
     * */
    @Test
    public void delete2() throws Exception {
        mock.perform(get("/deleteuser").param("id", "4")).andExpect(
                redirectedUrl("/users.html"));
    }

    /**
     * Delete user with not exist id
     * */
    @Test
    public void delete3() throws Exception {
        mock.perform(get("/deleteuser").param("id", "4000")).andExpect(
                redirectedUrl("/users.html"));
    }

    /**
     * Search user without parameter
     * */
    @Test
    public void search1() throws Exception {
        mock.perform(post("/searchUsers")).andExpect(
                redirectedUrl("/users.html"));
    }

    /**
     * Search user with parameter
     * */
    @Test
    public void search2() throws Exception {
        mock.perform(post("/searchUsers").param("search", "user")).andExpect(
                redirectedUrl("/users.html"));
    }

    /**
     * Search user with empty parameter
     * */
    @Test
    public void search3() throws Exception {
        mock.perform(post("/searchUsers").param("search", " ")).andExpect(
                redirectedUrl("/users.html"));
    }

    /**
     * Save password with valid parameters
     * */
    @Test
    public void savepasswd1() throws Exception {
        User user = new User();
        user.setId(7L);
        user.setName("test");
        user.setPasswd("test");
        mock.perform(
                get("/savepasswd").param("passwdOld", "secret")
                        .param("passwdConfirm", "test").flashAttr("user", user))
                .andExpect(model().attributeExists("messageSuccess"))
                .andExpect(model().attributeExists("user"));
    }

    /**
     * Save password with not valid confirm password
     * */
    @Test
    public void savepasswd2() throws Exception {
        User user = new User();
        user.setId(7L);
        user.setName("test");
        user.setPasswd("test");
        mock.perform(
                get("/savepasswd").param("passwdOld", "secret")
                        .param("passwdConfirm", "test3")
                        .flashAttr("user", user))
                .andExpect(model().attributeExists("errorConfirmed"))
                .andExpect(model().attributeExists("user"));
    }

    /**
     * Save password with not correct password
     * */
    @Test
    public void savepasswd3() throws Exception {
        User user = new User();
        user.setId(7L);
        user.setName("test");
        user.setPasswd("test");
        mock.perform(
                get("/savepasswd").param("passwdOld", "secret4")
                        .param("passwdConfirm", "test").flashAttr("user", user))
                .andExpect(model().attributeExists("errorPasswd"))
                .andExpect(model().attributeExists("user"));
    }

    /**
     * Save password with not valid new password
     * */
    @Test
    public void savepasswd4() throws Exception {
        User user = new User();
        user.setId(7L);
        user.setName("test");
        user.setPasswd("#$$%#^%$#^");
        mock.perform(
                get("/savepasswd").param("passwdOld", "secret")
                        .param("passwdConfirm", "test").flashAttr("user", user))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("user"));
    }

    /**
     * Get user info
     * */
    @Test
    public void userInfo() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "user1";
            }
        };
        mock.perform(get("/info").principal(principal)).andExpect(status().isOk()).andExpect(model().attributeExists("user"));
    }

    /**
     * Save user without roles
     * */
    @Test
    public void save1() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };
        User user = new User();
        user.setName("test");
        user.setPasswd("test");
        mock.perform(post("/save").principal(principal).flashAttr("user", user)).andExpect(model().attributeExists("errorRoles"));
    }

    /**
     * Save user with roles
     * */
    @Test
    public void save2() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };
        User user = new User();
        user.setName("test");
        user.setPasswd("test");
        mock.perform(post("/save").principal(principal).flashAttr("user", user).param("role", "2", "1")).andExpect(redirectedUrl("/users.html"));
    }

    /**
     * Save user with roles, empty principal
     * */
    @Test
    public void save3() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "";
            }
        };
        User user = new User();
        user.setName("test");
        user.setPasswd("test");
        mock.perform(post("/save").principal(principal).flashAttr("user", user).param("role", "2", "1")).andExpect(redirectedUrl("/users.html"));
    }

    /**
     * Save user with roles, null principal
     * */
    @Test
    public void save4() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return null;
            }
        };
        User user = new User();
        user.setName("test");
        user.setPasswd("test");
        mock.perform(post("/save").principal(principal).flashAttr("user", user).param("role", "2", "1")).andExpect(redirectedUrl("/users.html"));
    }

    /**
     * Save user with not valid name
     * */
    @Test
    public void save5() throws Exception {
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };
        User user = new User();
        user.setName("%^$%$&^$*&^^&");
        user.setPasswd("test");
        mock.perform(post("/save").principal(principal).flashAttr("user", user).param("role", "2")).andExpect(forwardedUrl("user"));
    }
}
