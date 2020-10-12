package org.acme.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Vet;
import org.acme.service.VetsService;

@Path("/vets")
@Produces(MediaType.APPLICATION_JSON)
public class VetsResource {

    @Inject
    VetsService service;

    @GET
    public List<Vet> get() {
        System.out.println("Inside get() method");
        return service.getAll();
    }
}