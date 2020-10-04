package org.acme.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Vets;
import org.acme.service.VetsService;
import org.jboss.logging.Logger;

@Path("/vets")
@Produces(MediaType.APPLICATION_JSON)
public class VetsResource {

    private static final Logger LOG = Logger.getLogger(VetsResource.class);

    @Inject
    VetsService service;

    @GET
    public List<Vets> get() {
        LOG.info("Inside get() method");
        return service.getAll();
    }
}