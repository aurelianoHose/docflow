package com.netradio.dao;

import java.util.List;

import com.netradio.entity.Option;

public interface OptionDao extends GenericDao<String, Option> {

    List<Option> findAll();

    List<Option> find(String key);

    List<Option> findByType(Long idType);
}
