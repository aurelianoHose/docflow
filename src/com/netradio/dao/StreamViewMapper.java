package com.netradio.dao;

import java.util.List;

import com.netradio.entity.StreamView;

public interface StreamViewMapper {

    List<StreamView> findAll(Integer page, Integer count, boolean act);

    List<StreamView> find(String name, String type, Integer page, Integer count, boolean act);

    Integer count(String name, String type, boolean act, boolean flag2, Long id);

    List<StreamView> findByFavorite(Long id, String name, String type, Integer page, Integer count, boolean act);

    List<StreamView> findByGenres(Long[] gids);

    StreamView findStreamInfoById(Long id);
}
