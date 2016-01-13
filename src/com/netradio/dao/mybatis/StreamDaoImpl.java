package com.netradio.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netradio.dao.StreamDao;
import com.netradio.entity.Stream;

public class StreamDaoImpl extends SqlSessionDaoSupport implements StreamDao {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public final void create(final Stream stream) {
        getSqlSession().insert("Stream.create", stream);
    }

    @Override
    public final Stream read(final Long id) {
        return getSqlSession().selectOne("Stream.read", id);
    }

    @Override
    public final void update(final Stream stream) {
        getSqlSession().update("Stream.update", stream);
    }

    @Override
    public final void delete(final Long id) {
        getSqlSession().delete("Stream.delete", id);
    }

    @Override
    public final List<Stream> findAll() {
        return getSqlSession().selectList("Stream.findAll");
    }

    @Override
    public void setActual(final Long id, final boolean act) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("act", act);
        getSqlSession().update("Stream.setActual", map);
    }

    @Override
    public void setFavorite(Long uid, Long fid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("fid", fid);
        getSqlSession().insert("Stream.addFavorite", map);
    }

    @Override
    public void delFavorite(Long uid, Long fid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("fid", fid);
        getSqlSession().insert("Stream.delFavorite", map);
    }

    @Override
    public List<Long> getFavorites(Long uid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        return getSqlSession().selectList("Stream.getFavorites", map);
    }
}
