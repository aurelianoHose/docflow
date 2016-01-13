package com.netradio.junit4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.netradio.entity.Genre;
import com.netradio.web.controller.GenreController;
import com.netradio.web.controller.GenreController.GenreQuickSearchState;

@WebAppConfiguration
public class GenreControllerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private GenreController gc;

    private MockMvc mock;

    @Autowired
    protected FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(gc).build();
    }

    /**
     * Call the method genres() with existing attribute 'genres'
     */
    @Test
    public void genres1() throws Exception {
        mock.perform(get("/genres"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(forwardedUrl("genre/genres"));
    }

    /**
     * Call the method genres() with existing attribute 'genres' and empty
     * GenreQuickSearchState.search
     */
    @Test
    public void genres2() throws Exception {
        GenreQuickSearchState u = new GenreQuickSearchState();
        u.setSearch("");
        gc.setState(u);
        mock.perform(get("/genres"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(forwardedUrl("genre/genres"));
    }

    /**
     * Call the method genre() with existing attribute 'genre' and forwarding to
     * 'genre/genre'
     */
    @Test
    public void genre1() throws Exception {
        mock.perform(get("/genre")).andExpect(model().attributeExists("genre"))
                .andExpect(forwardedUrl("genre/genre"));
    }

    /**
     * Call the method genre() without existing attribute
     */
    @Test
    public void genre2() throws Exception {
        mock.perform(get("/genre")).andExpect(
                model().attributeDoesNotExist("user"));
    }

    /**
     * Call the method genre() without existing attribute and parameter id
     */
    @Test
    public void genre3() throws Exception {
        mock.perform(get("/genre").param("id", "5")).andExpect(
                model().attributeDoesNotExist("user"));
    }

    /**
     * Call the method genre() with existing attribute and parameter id
     */
    @Test
    public void genre4() throws Exception {
        mock.perform(get("/genre").param("id", "1")).andExpect(
                model().attributeExists("genre"));
    }

    /**
     * Call the method genre() with existing attribute and not existing
     * parameter id
     */
    @Test
    public void genre5() throws Exception {
        mock.perform(get("/genre").param("id", "1000")).andExpect(
                model().attributeDoesNotExist("genre"));
    }

    /**
     * Call the method save() with valid genre name and existing user name
     */
    @Test
    public void save1() throws Exception {
        Genre g = new Genre();
        g.setName("name");

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                post("/savegenre").principal(principal).flashAttr("genre", g))
                .andExpect(redirectedUrl("/genres.html"));
    }

    /**
     * Call the method save() with valid genre name and empty user name
     */
    @Test
    public void save2() throws Exception {
        Genre g = new Genre();
        g.setName("name");

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "";
            }
        };

        mock.perform(
                post("/savegenre").principal(principal).flashAttr("genre", g))
                .andExpect(redirectedUrl("/genres.html"));
    }

    /**
     * Call the method save() with not valid genre name and empty user name
     */
    @Test
    public void save3() throws Exception {
        Genre g = new Genre();
        g.setName("");

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "";
            }
        };

        mock.perform(
                post("/savegenre").principal(principal).flashAttr("genre", g))
                .andExpect(forwardedUrl("genre/genre"));
    }

    /**
     * Call the method delete() with existing parameter 'id'
     */
    @Test
    public void delete1() throws Exception {
        mock.perform(get("/deletegenre").param("id", "10")).andExpect(
                redirectedUrl("/genres.html"));
    }

    /**
     * Call the method delete() without existing parameter 'id'
     */
    @Test
    public void delete2() throws Exception {
        mock.perform(get("/deletegenre")).andExpect(
                redirectedUrl("/genres.html"));
    }

    /**
     * Call the method search() with existing parameter search
     */
    @Test
    public void search1() throws Exception {
        mock.perform(post("/searchGenres").param("search", "123")).andExpect(
                redirectedUrl("/genres.html"));
    }

    /**
     * Call the method search() without existing parameter search
     */
    @Test
    public void search2() throws Exception {
        mock.perform(post("/searchGenres")).andExpect(
                redirectedUrl("/genres.html"));
    }

    /**
     * Call the method search() with empty parameter search
     */
    @Test
    public void search3() throws Exception {
        mock.perform(post("/searchGenres").param("search", "")).andExpect(
                redirectedUrl("/genres.html"));
    }
}
