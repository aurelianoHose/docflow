package com.netradio.service;

import java.util.List;

import com.netradio.entity.Genre;

public interface GenreService {

    Genre getGenre(Long id);

    Genre getGenre(String name);

    List<Genre> getGenres();

    List<Genre> getGenres(String name);

    List<Genre> getGenres(Long id);

    List<Genre> getGenresInfo();

    void saveGenre(Genre gener);

    void deleteGenre(Long id);
}
