package com.netradio.service;

import java.util.List;

import com.netradio.entity.Option;
import com.netradio.entity.TypeConfig;

public interface OptionService {

    Option getOption(String key);

    void saveOption(Option option);

    List<Option> getOptions();

    List<Option> getOptions(String key);

    List<Option> getOptions(Long idType);

    TypeConfig getTypeByKey(String key);
}
