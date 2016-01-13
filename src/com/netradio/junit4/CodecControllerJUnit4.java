package com.netradio.junit4;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.netradio.web.controller.CodecController;

public class CodecControllerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private CodecController cc;

    private MockMvc mock;

    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(cc).build();
    }

    /**
     * Get all codecs
     * */
    @Test
    public void codecs() throws Exception {
        mock.perform(get("/codec/list"))
                .andExpect(model().attributeExists("codecs"))
                .andExpect(status().isOk());
    }

    /**
     * Get codec with exist id
     * */
    @Test
    public void codec1() throws Exception {
        mock.perform(get("/codec/codec").param("id", "2"))
                .andExpect(model().attributeExists("codec"))
                .andExpect(status().isOk());
    }

    /**
     * Get codec with not exist id
     * */
    @Test
    public void codec2() throws Exception {
        mock.perform(get("/codec/codec").param("id", "100"))
                .andExpect(model().attributeDoesNotExist("codec"))
                .andExpect(status().isOk());
    }

    /**
     * Save codec with valid id and name
     * */
    @Test
    public void save1() throws Exception {
        mock.perform(post("/codec/save").param("id", "2").param("name", "test"))
                .andExpect(redirectedUrl("/codec/list.html"));
    }

    /**
     * Save codec without id
     * */
    @Test
    public void save2() throws Exception {
        mock.perform(post("/codec/save").param("name", "test")).andExpect(
                redirectedUrl("/codec/list.html"));
    }

    /**
     * Delete codec without id
     * */
    @Test
    public void delete1() throws Exception {
        mock.perform(get("/codec/delete")).andExpect(
                redirectedUrl("/codec/list.html"));
    }

    /**
     * Delete codec with valid id
     * */
    @Test
    public void delete2() throws Exception {
        mock.perform(get("/codec/delete").param("id", "9")).andExpect(
                redirectedUrl("/codec/list.html"));
    }
}
