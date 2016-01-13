package com.netradio.service;

import java.util.List;

import com.netradio.dao.CodecDao;
import com.netradio.entity.Codec;

public class CodecServiceImpl implements CodecService {

    private CodecDao dao;

    public void setDao(final CodecDao dao) {
        this.dao = dao;
    }

    @Override
    public Codec getCodec(final Long id) {
        if (id == null) {
            return null;
        } else {
            return dao.read(id);
        }
    }

    @Override
    public List<Codec> getCodecs() {
        return dao.findAll();
    }

    @Override
    public void saveCodec(final Codec codec) {
        if (codec.getId() == null) {
            dao.create(codec);
        } else {
            dao.update(codec);
        }
    }

    @Override
    public void deleteCodec(final Long id) {
        dao.delete(id);
    }

}
