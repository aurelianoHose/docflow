package com.netradio.junit4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.netradio.entity.Option;
import com.netradio.web.controller.OptionsController;

@WebAppConfiguration
public class OptionsControllerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private OptionsController oc;

    private MockMvc mock;

    @Autowired
    protected FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(oc).build();
    }

    /**
     * Call the method options()
     */
    @Test
    public void options() throws Exception {
        mock.perform(get("/options"))
                .andExpect(model().attributeExists("options"))
                .andExpect(forwardedUrl("options/options"));
    }

    /**
     * Call the method option() with existing parameter key
     */
    @Test
    public void option1() throws Exception {
        mock.perform(get("/option").param("key", "crontab"))
                .andExpect(model().attributeExists("option"))
                .andExpect(forwardedUrl("options/option"));
    }

    /**
     * Call the method option() without existing parameter key
     */
    @Test
    public void option2() throws Exception {
        mock.perform(get("/option").param("key", "234"))
                .andExpect(model().attributeDoesNotExist("option"))
                .andExpect(forwardedUrl("options/option"));
    }

    /**
     * Call the method save() with not valid option 'crontab'
     */
    @Test
    public void save1() throws Exception {
        Option o = new Option();
        o.setKey("crontab");
        o.setValue("100 100 100 100 100");
        mock.perform(post("/saveOption").flashAttr("option", o))
                .andExpect(model().attributeExists("option"))
                .andExpect(forwardedUrl("options/option"));
    }

    /**
     * Call the method save() without valid option
     */
    @Test
    public void save2() throws Exception {
        Option o = new Option();
        o.setKey("poolSize");
        o.setValue("30");
        mock.perform(post("/saveOption").flashAttr("option", o)).andExpect(
                redirectedUrl("/options.html"));
    }
    
    /**
     * Call the method save() without valid option
     */
    @Test
    public void save3() throws Exception {
        Option o = new Option();

        mock.perform(post("/saveOption").flashAttr("option", o)).andExpect(
                redirectedUrl("/options.html"));
    }
    
    /**
     * Call the method save() without valid option 'crontab'
     */
    @Test
    public void save4() throws Exception {
        Option o = new Option();
        o.setKey("crontab");
        o.setValue("10 10 * * * *");
        mock.perform(post("/saveOption").flashAttr("option", o))
                .andExpect(model().attributeExists("option"))
                .andExpect(redirectedUrl("/options.html"));
    }
}
