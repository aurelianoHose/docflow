package com.netradio.junit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.netradio.entity.Codec;
import com.netradio.service.CodecService;

public class CodecServiceJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private CodecService service;

    /**
     * Getting all codecs
     */
    @Test
    public void getCodecs1() {
        List<Codec> codec = service.getCodecs();
        assertNotNull(codec);
    }

    /**
     * Getting codec by valid id
     */
    @Test
    public void getCodec1() {
        Codec codec = service.getCodec(0L);
        assertNotNull(codec);
    }

    /**
     * Getting codec by not valid id
     */
    @Test
    public void getCodec2() {
        Codec codec = service.getCodec(1000L);
        assertNull(codec);
    }

    /**
     * Getting codec by nulled id
     */
    @Test
    public void getCodec3() {
        Long id = null;
        Codec codec = service.getCodec(id);
        assertNull(codec);
    }

    /**
     * Deleting codec with existing id
     */
    @Test
    public void deleteCodec1() {
        service.deleteCodec(0L);
    }

    /**
     * Deleting codec with not existing id
     */
    @Test
    public void deleteCodec2() {
        service.deleteCodec(1000L);
    }

    /**
     * Saving codec without codec id
     */
    @Test
    public void saveCodec1() {
        Codec codec = new Codec();
        codec.setName("test");
        service.saveCodec(codec);
    }

    /**
     * Saving codec with existing codec id
     */
    @Test
    public void saveGenre2() {
        Codec codec = new Codec();
        codec.setId(0L);
        codec.setName("test");
        service.saveCodec(codec);
    }
}