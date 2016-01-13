package com.netradio.junit4;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.netradio.entity.Role;
import com.netradio.entity.User;
import com.netradio.service.UserService;

public class UserServiceJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private UserService service;

    /**
     * Get all users
     * */
    @Test
    public void getUsers() {
        List<User> users = service.getUsers();
        assertFalse(users.isEmpty());
    }

    /**
     * Get all users with valid name
     * */
    @Test
    public void getUsersByName1() {
        List<User> users = service.getUsers("user");
        assertFalse(users.isEmpty());
    }

    /**
     * Get all users with not valid name
     * */
    @Test
    public void getUsersByName() {
        List<User> users = service.getUsers("user777777");
        assertTrue(users.isEmpty());
    }

    /**
     * Get user by valid id
     * */
    @Test
    public void getUserById1() {
        User user = service.getUser(3L);
        assertNotNull(user);
    }

    /**
     * Get user by not valid id
     * */
    @Test
    public void getUserById2() {
        User user = service.getUser(100000L);
        assertNull(user);
    }

    /**
     * Get user by valid name
     * */
    @Test
    public void getUserByName1() {
        User user = service.getUser("user");
        assertNotNull(user);
    }

    /**
     * Get user by not valid name
     * */
    @Test
    public void getUserByName2() {
        User user = service.getUser("hghfthth");
        assertNull(user);
    }

    /**
     * Get roles
     * */
    @Test
    public void getRoles() {
        List<Role> roles = service.getRoles();
        assertNotNull(roles);
    }

    /**
     * Get user's roles by valid id
     * */
    @Test
    public void getUserRoles1() {
        List<Role> roles = service.getUsersRoles(1L);
        assertFalse(roles.isEmpty());
    }

    /**
     * Get user's roles by not valid id
     * */
    @Test
    public void getUserRoles() {
        List<Role> roles = service.getUsersRoles(188L);
        assertTrue(roles.isEmpty());
    }

    /**
     * Delete users by valid id
     * */
    @Test
    public void deleteUser1() {
        Long[] ids = { 12L, 3L, 4L };
        service.deleteUser(ids);
    }

    /**
     * Delete users by not valid id
     * */
    @Test
    public void deleteUser2() {
        Long[] ids = { 1002L, 300L, 40L };
        service.deleteUser(ids);
    }

    /**
     * Save empty user
     * */
    @Test
    public void saveUser1() {
        User user = new User();
        service.saveUser(user);
    }

    /**
     * Save(update) user with id
     * */
    @Test
    public void saveUser2() {
        User user = new User();
        user.setId(5L);
        user.setName("test");
        user.setPasswd("test");
        service.saveUser(user);
    }

    /**
     * Save new user
     * */
    @Test
    public void saveUser3() {
        User user = new User();
        user.setName("test");
        service.saveUser(user);
    }

    /**
     * Save null user
     * */
    @Test(expected = java.lang.NullPointerException.class)
    public void saveUser4() {
        service.saveUser(null);
    }

    /**
     * Save user's password as null user
     * */
    @Test
    public void saveUserPasswd1() {
        service.saveUserPasswd(null);
    }

    /**
     * Save user's password empty user
     * */
    @Test
    public void saveUserPasswd2() {
        User user = new User();
        service.saveUserPasswd(user);
    }

    /**
     * Save user's password valid user
     * */
    @Test
    public void saveUserPasswd3() {
        User user = new User();
        user.setId(2L);
        user.setPasswd("test");
        service.saveUserPasswd(user);
    }

    /**
     * Save user whit empty roles
     * */
    @Test
    public void saveUserWithRoles1() {
        User user = new User();
        user.setId(2L);
        service.saveUser(user, null);
    }

    /**
     * Save user whit valid roles
     * */
    @Test
    public void saveUserWithRoles2() {
        User user = new User();
        user.setId(2L);
        Long[] rols = { 1L, 2L };
        service.saveUser(user, rols);
    }

    /**
     * Save user whit not valid roles
     * */
    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void saveUserWithRoles3() {
        User user = new User();
        user.setId(2L);
        Long[] rols = { 77L, 5L };
        service.saveUser(user, rols);
    }

    /**
     * Check password null user
     * */
    @Test
    public void checkPasswd1() {
        assertFalse(service.chekUserPasswd(null));
    }

    /**
     * Check password valid user
     * */
    @Test
    public void checkPasswd2() {
        User user = new User();
        user.setId(2L);
        user.setName("user");
        user.setPasswd("secret");
        assertTrue(service.chekUserPasswd(user));
    }

    /**
     * Check password valid user, not valid password
     * */
    @Test
    public void checkPasswd3() {
        User user = new User();
        user.setId(2L);
        user.setName("user");
        user.setPasswd("seret");
        assertFalse(service.chekUserPasswd(user));
    }
}