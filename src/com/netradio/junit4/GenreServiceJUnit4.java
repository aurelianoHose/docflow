package com.netradio.junit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.netradio.entity.Genre;
import com.netradio.service.GenreService;

public class GenreServiceJUnit4 extends AbstractServiceJUnit4 {

    @Autowired
    private GenreService service;

    /**
     * Getting all genres info
     */
    @Test
    public void getGenresInfo() {
        List<Genre> genres = service.getGenresInfo();
        assertNotNull(genres);
    }

    /**
     * Getting all genres
     */
    @Test
    public void getGenres1() {
        List<Genre> genres = service.getGenres();
        assertNotNull(genres);
    }

    /**
     * Getting c
     */
    @Test
    public void getGenres2() {
        List<Genre> genres = service.getGenres("testGenre");
        assertNotNull(genres);
    }

    /**
     * Getting genres by not valid name
     */
    @Test
    public void getGenres3() {
        List<Genre> genres = service.getGenres("testGenre2");
        assertTrue(genres.size() == 0);
    }

    /**
     * Getting genres by valid id
     */
    @Test
    public void getGenres4() {
        List<Genre> genres = service.getGenres(0L);
        assertNotNull(genres);
    }

    /**
     * Getting genres by not valid id
     */
    @Test
    public void getGenres5() {
        List<Genre> genres = service.getGenres(1000L);
        assertTrue(genres.size() == 0);
    }

    /**
     * Getting genres by valid id
     */
    @Test
    public void getGenre1() {
        Genre genre = service.getGenre(0L);
        assertNotNull(genre);
    }

    /**
     * Getting genres by not valid id
     */
    @Test
    public void getGenre2() {
        Genre genre = service.getGenre(1000L);
        assertNull(genre);
    }

    /**
     * Getting genres by valid name
     */
    @Test
    public void getGenre3() {
        Genre genre = service.getGenre("testGenre");
        assertNotNull(genre);
    }

    /**
     * Getting genres by not valid name
     */
    @Test
    public void getGenre4() {
        Genre genre = service.getGenre("testGenre2");
        assertNull(genre);
    }

    /**
     * Getting genres by nulled id
     */
    @Test
    public void getGenre5() {
        Long id = null;
        Genre genre = service.getGenre(id);
        assertNull(genre);
    }

    /**
     * Saving genre without genre id
     */
    @Test
    public void saveGenre1() {
        Genre genre = new Genre();
        genre.setName("test");
        genre.setIdCreator(0L);
        service.saveGenre(genre);
    }

    /**
     * Saving genre with existing genre id
     */
    @Test
    public void saveGenre2() {
        Genre genre = new Genre();
        genre.setId(0L);
        genre.setName("test");
        genre.setIdCreator(0L);
        service.saveGenre(genre);
    }

    /**
     * Saving genre with not valid idCreator
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveGenre3() {
        Genre genre = new Genre();
        genre.setName("test");
        genre.setIdCreator(1000L);
        service.saveGenre(genre);
    }

    /**
     * Deleting genre with existing id
     */
    @Test
    public void deleteGenre1() {
        service.deleteGenre(0L);
    }

    /**
     * Deleting genre with not existing id
     */
    @Test
    public void deleteGenre2() {
        service.deleteGenre(1000L);
    }
}
