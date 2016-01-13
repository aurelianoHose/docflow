package com.netradio.dao;

import java.util.List;

import com.netradio.entity.Codec;

public interface CodecDao extends GenericDao<Long, Codec> {

    List<Codec> findAll();
}
