package org.acme.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.acme.model.Visit;
import org.acme.service.VisitsService;
import org.jboss.logging.Logger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class VisitsResource {

    private static final Logger LOG = Logger.getLogger(VisitsService.class);

    @Inject
    VisitsService visitsService;
    
    // Note: To test in browser or curl:
    //       $ curl "http://localhost:6060/pets/visits?petIds=8&petIds=7"
    //
    @GET
    @Path("pets/visits")
    public List<Visit> visitsMultiGet(@QueryParam("petIds") List<Long> petIds) {
        return visitsService.findByMultiPetIds(petIds);
    }

    @GET
    @Path("owners/*/pets/{petId}/visits")
    public List<Visit> visits(@PathParam("petId") long petId) {
        return visitsService.findByPetId(petId);
    }

    @POST
    @Path("owners/*/pets/{petId}/visits")
    public Response create(@PathParam("petId") long petId, Visit theVisit, @Context UriInfo uriInfo) {
        theVisit.petId = petId;

        LOG.info("Persisting: " + theVisit);
        visitsService.save(theVisit);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

        return Response.created(uriBuilder.build()).entity(theVisit).build();
    }

}