package com.netradio.dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netradio.dao.OptionDao;
import com.netradio.entity.Option;

public class OptionDaoImpl extends SqlSessionDaoSupport implements OptionDao {

    @Override
    public void create(final Option o) {
        getSqlSession().insert("Option.create", o);
    }

    @Override
    public Option read(final String id) {
        return getSqlSession().selectOne("Option.read", id);
    }

    @Override
    public void update(final Option o) {
        getSqlSession().update("Option.update", o);
    }

    @Override
    public void delete(final String id) {
    }

    @Override
    public List<Option> findAll() {
        return getSqlSession().selectList("Option.findAll");
    }

    @Override
    public List<Option> find(final String key) {
        return getSqlSession().selectList("Option.findByKey", "%" + key + "%");
    }

    @Override
    public List<Option> findByType(final Long idType) {
        return getSqlSession().selectList("Option.findByType", idType);
    }
}
