package com.netradio.junit4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.math.BigDecimal;
import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.netradio.entity.Stream;
import com.netradio.web.controller.StreamController;
import com.netradio.web.controller.StreamController.StreamQuickSearchState;

@WebAppConfiguration
public class StreamControlerJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private StreamController wac;

    private MockMvc mock;

    @Before
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(wac).build();
    }

    /**
     * Getting streams with flag2(favorites)=false and empty search value
     * */
    @Test
    public void streams1() throws Exception {

        wac.setFlag2(false);
        StreamQuickSearchState state = new StreamQuickSearchState();
        wac.setState(state);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(get("/stream/list").principal(principal))
                .andExpect(forwardedUrl("stream/streams"))
                .andExpect(model().attributeExists("_page_"))
                .andExpect(model().attributeExists("_count_"));

    }

    /**
     * Getting streams with flag2(favorites)=true
     * */
    @Test
    public void streams2() throws Exception {

        wac.setFlag2(true);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(get("/stream/list").principal(principal))
                .andExpect(forwardedUrl("stream/streams"))
                .andExpect(model().attributeExists("_page_"))
                .andExpect(model().attributeExists("_count_"));
    }

    /**
     * Getting streams with flag2(favorites)=true and existing search value
     * */
    @Test
    public void streams3() throws Exception {

        wac.setFlag2(true);
        StreamQuickSearchState state = new StreamQuickSearchState();
        state.setSearch("search");
        state.setType("type");
        wac.setState(state);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(get("/stream/list").principal(principal))
                .andExpect(forwardedUrl("stream/streams"))
                .andExpect(model().attributeExists("_page_"))
                .andExpect(model().attributeExists("_count_"))
                .andExpect(model().attributeExists("search"))
                .andExpect(model().attributeDoesNotExist("streams"));
        ;
    }

    /**
     * Getting streams with setting up flag1 (fl). Flag2(favorites)=false
     * */
    @Test
    public void streams4() throws Exception {

        mock.perform(get("/stream/listA").param("fl", "false")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Getting streams with setting up flag1 (fl). Flag2(favorites)=true
     * */
    @Test
    public void streams5() throws Exception {

        mock.perform(get("/stream/listB").param("fl", "false")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Getting stream without parameter id
     * */
    @Test
    public void stream1() throws Exception {
        mock.perform(get("/stream/stream"))
                .andExpect(forwardedUrl("stream/stream"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("streamModel"))
                .andExpect(model().attributeExists("codecs"));
    }

    /**
     * Getting stream without valid parameter id
     * */
    @Test
    public void stream2() throws Exception {
        mock.perform(get("/stream/stream").param("id", "1000"))
                .andExpect(forwardedUrl("stream/stream"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("streamModel"))
                .andExpect(model().attributeExists("codecs"));
    }

    /**
     * Getting stream with valid parameter id
     * */
    @Test
    public void stream3() throws Exception {
        mock.perform(get("/stream/stream").param("id", "1"))
                .andExpect(forwardedUrl("stream/stream"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("streamModel"))
                .andExpect(model().attributeExists("codecs"));
    }

    /**
     * Save stream with valid data
     * */
    @Test
    public void save() throws Exception {
        Stream s = new Stream();
        s.setUserName("admin");
        s.setLink("test");
        s.setActual(true);
        s.setBitrade(new BigDecimal(1));
        s.setCodecId(1L);
        s.setFlow("test");
        s.setIcon("test");
        s.setIdCreator(1L);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                fileUpload("/stream/save.html").file("file", "test".getBytes())
                        .principal(principal).flashAttr("streamModel", s)
                        .param("genres", "Acid")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Save stream with empty stream name
     * */
    @Test
    public void save2() throws Exception {
        Stream s = new Stream();
        s.setUserName("admin");
        s.setLink("test");
        s.setActual(true);
        s.setBitrade(new BigDecimal(1));
        s.setCodecId(1L);
        s.setFlow(" ");
        s.setIcon("test");
        s.setIdCreator(1L);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                fileUpload("/stream/save.html").file("file", "test".getBytes())
                        .principal(principal).flashAttr("streamModel", s)
                        .param("genres", "Acid")).andExpect(
                forwardedUrl("stream/stream"));
    }

    /**
     * Save stream with not existing genre name
     * */
    @Test
    public void save3() throws Exception {
        Stream s = new Stream();
        s.setUserName("admin");
        s.setLink("test");
        s.setActual(true);
        s.setBitrade(new BigDecimal(1));
        s.setCodecId(1L);
        s.setFlow(" ");
        s.setIcon("test");
        s.setIdCreator(1L);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                fileUpload("/stream/save.html").file("file", "test".getBytes())
                        .principal(principal).flashAttr("streamModel", s)
                        .param("genres", "Acidgggg")).andExpect(
                forwardedUrl("stream/stream"));
    }

    /**
     * Delete stream with valid parameter id
     * */
    @Test
    public void delete1() throws Exception {
        mock.perform(get("/stream/delete").param("id", "1")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Delete stream without valid parameter id
     * */
    @Test
    public void delete2() throws Exception {
        mock.perform(get("/stream/delete").param("id", "1000")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Delete stream without parameter id
     * */
    @Test
    public void delete3() throws Exception {
        mock.perform(get("/stream/delete")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Set stream to favorites without parameter fid
     * */
    @Test
    public void setFavorite1() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                get("/stream/setFavorite").param("fid", "")
                        .principal(principal)).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Set stream to favorites without parameter fid
     * */
    @Test
    public void setFavorite2() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                get("/stream/setFavorite").param("fid", "1").principal(
                        principal)).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Set stream to favorites without valid parameter fid
     * */
    @Test
    public void setFavorite3() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                get("/stream/setFavorite").param("fid", "1000").principal(
                        principal)).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Delete stream from favorites without parameter fid
     * */
    @Test
    public void delFavorite1() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                get("/stream/delFavorite").param("fid", "")
                        .principal(principal)).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Delete stream from favorites without parameter fid
     * */
    @Test
    public void delFavorite2() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                get("/stream/delFavorite").param("fid", "1").principal(
                        principal)).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Delete stream from favorites without valid parameter fid
     * */
    @Test
    public void delFavorite3() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "admin";
            }
        };

        mock.perform(
                get("/stream/delFavorite").param("fid", "1000").principal(
                        principal)).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Search stream with empty parameter 'search'
     * */
    @Test
    public void search1() throws Exception {

        mock.perform(post("/stream/search").param("search", "")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Search stream with parameter 'search'
     * */
    @Test
    public void search2() throws Exception {

        mock.perform(post("/stream/search").param("search", "search"))
                .andExpect(redirectedUrl("/stream/list.html"));
    }

    /**
     * Search stream without empty parameter 'search'
     * */
    @Test
    public void search3() throws Exception {

        mock.perform(post("/stream/search")).andExpect(
                redirectedUrl("/stream/list.html"));
    }

    /**
     * Search stream by genre with empty parameter 'search'
     * */
    @Test
    public void searchByGenre1() throws Exception {

        mock.perform(get("/stream/searchbygenre").param("search", ""))
                .andExpect(redirectedUrl("/stream/list.html"));
    }

    /**
     * Search stream by genre with parameter 'search'
     * */
    @Test
    public void searchByGenre2() throws Exception {

        mock.perform(get("/stream/searchbygenre").param("search", "search"))
                .andExpect(redirectedUrl("/stream/list.html"));
    }

    /**
     * Search stream by genre without empty parameter 'search'
     * */
    @Test
    public void searchByGenre3() throws Exception {

        mock.perform(get("/stream/searchbygenre")).andExpect(
                redirectedUrl("/stream/list.html"));
    }
}
