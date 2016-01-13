package com.netradio.junit4;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.netradio.entity.Option;
import com.netradio.entity.TypeConfig;
import com.netradio.service.OptionService;

public class OptionServiceJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private OptionService service;

    /**
     * Getting option by existing key
     */
    @Test
    public void getOption1() {
        Option option = service.getOption("key");
        assertNotNull(option);
    }

    /**
     * Getting option by not existing key
     */
    @Test
    public void getOption2() {
        Option option = service.getOption("key2");
        assertNull(option);
    }

    /**
     * Saving option
     */
    @Test
    public void saveOption1() {
        Option option = new Option();
        service.saveOption(option);
    }

    /**
     * Getting all options
     */
    @Test
    public void getOptions1() {
        List<Option> option = service.getOptions();
        assertNotNull(option);
    }

    /**
     * Getting options by valid key
     */
    @Test
    public void getOptions2() {
        List<Option> option = service.getOptions("key");
        assertNotNull(option);
    }

    /**
     * Getting options by not valid key
     */
    @Test
    public void getOptions3() {
        List<Option> option = service.getOptions("key2");
        assertTrue(option.size() == 0);
    }

    /**
     * Getting options by type id
     */
    @Test
    public void getOptions4() {
        List<Option> option = service.getOptions(1L);
        assertNotNull(option);
    }

    /**
     * Getting options by not existing type id
     */
    @Test
    public void getOptions5() {
        List<Option> option = service.getOptions(1000L);
        assertTrue(option.size() == 0);
    }

    /**
     * Getting TypeOption by existing key
     */
    @Test
    public void getTypeByKey() {
        TypeConfig option = service.getTypeByKey("key");
        assertNotNull(option);
    }

    /**
     * Getting TypeOption by not existing key
     */
    @Test
    public void getTypeByKey2() {
        TypeConfig option = service.getTypeByKey("key2");
        assertNull(option);
    }
}