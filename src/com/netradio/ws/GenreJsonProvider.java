package com.netradio.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.netradio.entity.Genre;
import com.netradio.util.GsonUtils;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GenreJsonProvider implements MessageBodyWriter<Genre[]> {

    @Override
    public long getSize(Genre[] genres, Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] anotations, MediaType mtype) {
        return Genre[].class.equals(clazz);
    }

    @Override
    public void writeTo(Genre[] genres, Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype, MultivaluedMap<String, Object> mMap, OutputStream os) throws IOException, WebApplicationException {
        Gson gson = GsonUtils.createGson();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        gson.toJson(genres, osw);
        osw.flush();
    }
}
