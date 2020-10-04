package org.acme.rest;

import java.net.URI;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.model.Pets;
import org.acme.model.VisitForm;
import org.acme.model.Visits;
import org.acme.service.OwnersService;
import org.acme.service.PetsService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/")
public class VisitsResource {

    @Inject
    OwnersService ownerService;

    @Inject
    PetsService petService;

    @Inject
    Template visit;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("getVisit")
    public TemplateInstance getPet(@QueryParam("ownerId") Long ownerId, @QueryParam("petId") Long petId) {
        Pets pets = petService.findById(petId);

        return visit.data("active", "owners")
                    .data("owner", ownerService.findById(ownerId))
                    .data("pet", pets);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("addVisit")
    public Response addVisit(@MultipartForm VisitForm visitForm, @QueryParam("ownerId") Long ownerId, @QueryParam("petId") Long petId) {

        Visits newVisit = visitForm.addVisit();

        petService.addVisit(petId, newVisit);

        return Response.status(301)
                    .location(URI.create("/owners?id=" + ownerId))
                    .build();
    }

}