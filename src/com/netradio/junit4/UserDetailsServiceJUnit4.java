package com.netradio.junit4;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.netradio.service.UserDetailsServiceImpl;
import com.netradio.service.UserService;

public class UserDetailsServiceJUnit4 extends AbstractServiceJUnit4 {

    private UserDetailsServiceImpl service = new UserDetailsServiceImpl();

    @Autowired
    private UserService srv;

    /**
     * Getting user by existing name
     */
    @Test
    public void loadUserByUserName() {
        service.setUserService(srv);
        UserDetails user = service.loadUserByUsername("admin");
        assertNotNull(user);
    }

    /**
     * Getting user by not existing name
     */
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUserName1() {
        service.setUserService(srv);
        service.loadUserByUsername("admin1000");
    }

}