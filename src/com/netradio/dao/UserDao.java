package com.netradio.dao;

import java.util.List;

import com.netradio.entity.User;

public interface UserDao extends GenericDao<Long, User> {

    List<User> findAll();

    User findByName(String name);

    List<User> find(String name);

    User findByPasswd(User user);

    void updatePasswd(User user);
}
