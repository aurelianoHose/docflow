package com.netradio.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("image/*")
public class StreamImageProvider implements MessageBodyWriter<byte[]> {

    @Override
    public long getSize(byte[] image, Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype) {
        long size = -1;
        if (image != null) {
            size = image.length;
        }
        return size;
    }

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype) {
        return byte[].class.equals(clazz);
    }

    @Override
    public void writeTo(byte[] image, Class<?> clazz, Type type, Annotation[] annotations, MediaType mtype, MultivaluedMap<String, Object> mmap, OutputStream os) throws IOException, WebApplicationException {
        os.write(image);
    }
}
