package org.acme.rest.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.model.Visit;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient
public interface VisitsRestClient {

    @GET
    @Path("pets/visits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Visit> visitsMultiGet(@QueryParam("petIds") List<Long> petIds);

    @GET
    @Path("owners/*/pets/{petId}/visits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Visit> visits(@PathParam("petId") long petId);

    @POST
    @Path("owners/*/pets/{petId}/visits")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("petId") long petId, Visit theVisit);

}