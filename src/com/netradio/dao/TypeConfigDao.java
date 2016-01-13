package com.netradio.dao;

import java.util.List;

import com.netradio.entity.TypeConfig;

public interface TypeConfigDao extends Dao<TypeConfig> {

    List<TypeConfig> findAll();

    TypeConfig findByOptionKey(String key);

}
