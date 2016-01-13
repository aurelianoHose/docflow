package com.netradio.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netradio.dao.GenreDao;
import com.netradio.entity.Genre;

public class GenreDaoImpl extends SqlSessionDaoSupport implements GenreDao {

    @Override
    public void create(final Genre genre) {
        getSqlSession().insert("Genre.create", genre);
    }

    @Override
    public Genre read(final Long id) {
        return getSqlSession().selectOne("Genre.read", id);
    }

    @Override
    public void update(final Genre genre) {
        getSqlSession().update("Genre.update", genre);
    }

    @Override
    public void delete(final Long id) {
        getSqlSession().update("Genre.delete", id);
    }

    @Override
    public List<Genre> findAll() {
        return getSqlSession().selectList("Genre.findAll");
    }

    @Override
    public List<Genre> find(final String name) {
        return getSqlSession().selectList("Genre.find", "%" + name + "%");
    }

    @Override
    public List<Genre> findByStreamId(Long id) {
        return getSqlSession().selectList("Genre.findByFlowId", id);
    }

    @Override
    public void addStreamGenre(Long gid, Long fid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("gid", gid);
        map.put("fid", fid);
        getSqlSession().insert("Genre.add", map);
    }

    @Override
    public void deleteStreamGenre(final Long fid) {
        getSqlSession().delete("Genre.deleteByFlowId", fid);
    }

    @Override
    public Genre findByName(String name) {
        return getSqlSession().selectOne("Genre.find", name);
    }

    @Override
    public List<Genre> findAllInfo() {
        return getSqlSession().selectList("Genre.findAllInfo");
    }
}
