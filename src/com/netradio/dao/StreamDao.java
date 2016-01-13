package com.netradio.dao;

import java.util.List;

import com.netradio.entity.Stream;

public interface StreamDao extends GenericDao<Long, Stream> {

    List<Stream> findAll();

    void setActual(Long id, boolean act);

    void setFavorite(Long uid, Long fid);

    void delFavorite(Long uid, Long fid);

    List<Long> getFavorites(Long uid);
}
