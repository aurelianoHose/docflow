package com.netradio.service;

import java.util.List;

import com.netradio.dao.OptionDao;
import com.netradio.dao.TypeConfigDao;
import com.netradio.entity.Option;
import com.netradio.entity.TypeConfig;

public class OptionServiceImpl implements OptionService {

    private OptionDao optionDao;

    private TypeConfigDao typeConfigDao;

    public void setTypeConfigDao(final TypeConfigDao typeConfigDao) {
        this.typeConfigDao = typeConfigDao;
    }

    public void setOptionDao(final OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    @Override
    public Option getOption(final String key) {
        return optionDao.read(key);
    }

    @Override
    public void saveOption(final Option option) {
        optionDao.update(option);
    }

    @Override
    public List<Option> getOptions() {
        return optionDao.findAll();
    }

    @Override
    public List<Option> getOptions(final String key) {
        return optionDao.find(key);
    }

    @Override
    public List<Option> getOptions(final Long idType) {
        return optionDao.findByType(idType);
    }

    @Override
    public TypeConfig getTypeByKey(final String key) {
        return typeConfigDao.findByOptionKey(key);
    }
}
