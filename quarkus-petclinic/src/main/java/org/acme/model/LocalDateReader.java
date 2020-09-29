package org.acme.model;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

// https://github.com/quarkusio/quarkus/issues/10466

@Provider
@Produces(MediaType.TEXT_PLAIN)
public class LocalDateReader implements MessageBodyReader<LocalDate> {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type == LocalDate.class;
    }

    @Override
    public LocalDate readFrom(Class<LocalDate> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {

        return LocalDate.parse(new String(inputStream.readAllBytes()));
    }
}