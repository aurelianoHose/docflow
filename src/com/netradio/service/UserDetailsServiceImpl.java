package com.netradio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.netradio.entity.Role;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService srv;

    public void setUserService(final UserService srv) {
        this.srv = srv;
    }

    @Override
    public UserDetails loadUserByUsername(final String name)
            throws UsernameNotFoundException {
        com.netradio.entity.User tmp = srv.getUser(name);
        if (tmp == null)
            throw new UsernameNotFoundException("User: " + name + " not found!");
        Collection<GrantedAuthority> cols = new ArrayList<GrantedAuthority>();
        List<Role> roles = srv.getUsersRoles(tmp.getId());
        cols.add(new SimpleGrantedAuthority(name));
        for (Role role : roles) {
            cols.add(new SimpleGrantedAuthority(role.getName()));
        }
        User user = new User(tmp.getName(), tmp.getPasswd(), cols);
        return user;
    }

}
