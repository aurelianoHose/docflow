package com.netradio.dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netradio.dao.TypeConfigDao;
import com.netradio.entity.TypeConfig;

public class TypeConfigDaoImpl extends SqlSessionDaoSupport implements
        TypeConfigDao {

    @Override
    public List<TypeConfig> findAll() {
        return getSqlSession().selectList("TypeConfig.findAll");
    }

    @Override
    public TypeConfig findByOptionKey(final String key) {
        return getSqlSession().selectOne("TypeConfig.findByOptionKey", key);
    }
}
