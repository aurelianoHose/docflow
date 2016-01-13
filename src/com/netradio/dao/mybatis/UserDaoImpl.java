package com.netradio.dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netradio.dao.UserDao;
import com.netradio.entity.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Override
    public void create(final User user) {
        getSqlSession().insert("User.create", user);
    }

    @Override
    public User read(final Long id) {
        return getSqlSession().selectOne("User.read", id);
    }

    @Override
    public void update(final User user) {
        getSqlSession().update("User.update", user);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("User.delete", id);
    }

    @Override
    public List<User> findAll() {
        return getSqlSession().selectList("User.findAll");
    }

    @Override
    public User findByName(final String name) {
        return getSqlSession().selectOne("User.readByName", name);
    }

    @Override
    public List<User> find(final String name) {
        return getSqlSession().selectList("User.findByName", "%" + name + "%");
    }

    @Override
    public User findByPasswd(final User user) {
        return getSqlSession().selectOne("User.findByIdAndPasswd", user);
    }

    @Override
    public void updatePasswd(final User user) {
        getSqlSession().update("User.updatePasswd", user);
    }
}
