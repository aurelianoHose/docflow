package com.netradio.service;

import java.util.List;

import com.netradio.dao.RoleDao;
import com.netradio.dao.UserDao;
import com.netradio.entity.Role;
import com.netradio.entity.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User getUser(final Long id) {
        return userDao.read(id);
    }

    @Override
    public User getUser(final String name) {
        return userDao.findByName(name);
    }

    @Override
    public void saveUser(final User user) {
        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
    }

    public void saveUser(final User user, final Long[] rids) {
        saveUser(user);
        roleDao.deleteUserRoles(user.getId());
        if (rids != null) {
            for (Long rid : rids) {
                roleDao.addUserRole(user.getId(), rid);
            }
        }
    }

    public void deleteUser(final Long[] id) {
        if (id != null)
            for (Long i : id)
                userDao.delete(i);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    public List<User> getUsers(final String name) {
        return userDao.find(name);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> getUsersRoles(final Long id) {
        return roleDao.findByUserId(id);
    }

    @Override
    public Boolean chekUserPasswd(final User user) {
        return (userDao.findByPasswd(user) != null);
    }

    @Override
    public void saveUserPasswd(final User user) {
        userDao.updatePasswd(user);
    }

}
