package org.acme.rest.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Vet;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/vets")
@RegisterRestClient
public interface VetsRestClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vet> getAll();

}
