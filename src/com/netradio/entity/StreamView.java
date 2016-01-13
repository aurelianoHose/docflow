package com.netradio.entity;

import java.util.List;

public class StreamView extends Stream {

    private static final long serialVersionUID = 1L;

    private String codecName;

    private List<Genre> genres;

    public String getCodecName() {
        return codecName;
    }

    public void setCodecName(final String codecName) {
        this.codecName = codecName;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
