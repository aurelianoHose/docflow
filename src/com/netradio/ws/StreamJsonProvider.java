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
import com.netradio.entity.StreamView;
import com.netradio.util.GsonUtils;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class StreamJsonProvider implements MessageBodyWriter<StreamView> {

    @Override
    public long getSize(StreamView stream, Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype) {
        return StreamView.class.equals(clazz);
    }

    @Override
    public void writeTo(StreamView stream, Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype, MultivaluedMap<String, Object> mMap, OutputStream os)
            throws IOException, WebApplicationException {
        Gson gson = GsonUtils.createGson();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        gson.toJson(stream, osw);
        osw.flush();
    }

}

