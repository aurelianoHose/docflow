package com.netradio.service;

import java.util.List;

import com.netradio.entity.Role;
import com.netradio.entity.User;

public interface UserService {

    User getUser(Long id);

    User getUser(String name);

    Boolean chekUserPasswd(User user);

    void saveUser(User user);

    void saveUserPasswd(User user);

    void saveUser(User user, Long[] rids);

    void deleteUser(Long[] id);

    List<User> getUsers();

    List<User> getUsers(String name);

    List<Role> getRoles();

    List<Role> getUsersRoles(Long id);
}
