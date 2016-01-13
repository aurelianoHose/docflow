package com.netradio.service;

import java.util.List;

import com.netradio.entity.Codec;

public interface CodecService {

    Codec getCodec(Long id);

    List<Codec> getCodecs();

    void saveCodec(Codec codec);

    void deleteCodec(Long id);

}
