package com.netradio.dao.mybatis;

import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.netradio.dao.CodecDao;
import com.netradio.entity.Codec;

public class CodecDaoImpl extends SqlSessionDaoSupport implements CodecDao {

    @Override
    public void create(final Codec codec) {
        getSqlSession().insert("Codec.create", codec);
    }

    @Override
    public Codec read(final Long id) {
        return getSqlSession().selectOne("Codec.read", id);
    }

    @Override
    public void update(final Codec codec) {
        getSqlSession().update("Codec.update", codec);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().delete("Codec.delete", id);
    }

    @Override
    public List<Codec> findAll() {
        return getSqlSession().selectList("Codec.findAll");
    }
}
