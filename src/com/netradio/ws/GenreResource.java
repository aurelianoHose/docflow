package com.netradio.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.netradio.entity.Genre;
import com.netradio.service.GenreService;

@Path("genres")
public class GenreResource {

    private GenreService srv;

    public void setSrv(GenreService srv) {
        this.srv = srv;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Genre[] genres() {
        List<Genre> list = srv.getGenresInfo();
        return list.toArray(new Genre[list.size()]);
    }
}
