package com.netradio.service;

import java.util.List;

import com.netradio.dao.GenreDao;
import com.netradio.entity.Genre;

public class GenreServiceImpl implements GenreService {

    private GenreDao dao;

    public void setDao(final GenreDao dao) {
        this.dao = dao;
    }

    @Override
    public Genre getGenre(final Long id) {
        if (id == null) {
            return null;
        } else {
            return dao.read(id);
        }
    }

    @Override
    public Genre getGenre(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Genre> getGenres() {
        return dao.findAll();
    }

    public List<Genre> getGenres(final String name) {
        return dao.find(name);
    }

    @Override
    public void saveGenre(final Genre genre) {
        if (genre.getId() == null) {
            dao.create(genre);
        } else {
            dao.update(genre);
        }
    }

    @Override
    public List<Genre> getGenres(Long id) {
        return dao.findByStreamId(id);
    }

    @Override
    public void deleteGenre(final Long id) {
        dao.delete(id);
    }

    @Override
    public List<Genre> getGenresInfo() {
        return dao.findAllInfo();
    }
}
