package com.netradio.dao;

import java.util.List;

import com.netradio.entity.Genre;

public interface GenreDao extends GenericDao<Long, Genre> {

    List<Genre> findAll();

    List<Genre> findAllInfo();

    List<Genre> find(String name);

    Genre findByName(String name);

    List<Genre> findByStreamId(Long id);

    void addStreamGenre(Long gid, Long fid);

    public void deleteStreamGenre(final Long fid);
}
