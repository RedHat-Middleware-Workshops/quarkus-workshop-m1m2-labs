package org.acme.rest;

import java.net.URI;
import java.util.Arrays;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import org.acme.model.Owner;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import org.acme.model.OwnerForm;
import org.acme.service.OwnersService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/")
public class OwnersResource {

    @Inject
    OwnersService service;

    @Inject
    Template owners;

    @Inject
    Template editOwner;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("owners")
    public TemplateInstance findOwners(@QueryParam("id") Long id) {
        return owners.data("active", "owners")
                    .data("owners", ((id == null) ? id : Arrays.asList(service.findById(id))));
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("find")
    public TemplateInstance findByLastName(@QueryParam("lastName") String lastName) {
        return owners.data("active", "owners")
                    .data("lastName", lastName)
                    .data("owners", service.findByLastName(lastName));

    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("addOwner")
    public Response addOwner(@MultipartForm OwnerForm ownerForm) {

        Owner newOwner = ownerForm.addOwner();
        newOwner.persist();
        return Response.status(301)
                    .location(URI.create("/owners?id=" + newOwner.getId()))
                    .build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("getOwner")
    public TemplateInstance editOwner(@QueryParam("ownerId") Long ownerId) {
        
        return editOwner.data("active", "owners")
                        .data("owner", ((ownerId == null) ? "new" : service.findById(ownerId)));
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("editOwner")
    public Response editOwner(@MultipartForm OwnerForm ownerForm, @QueryParam("ownerId") Long ownerId) {

        Owner existingOwner = service.findById(ownerId);
        existingOwner = ownerForm.editOwner(existingOwner);
        return Response.status(301)
                    .location(URI.create("/owners?id=" + ownerId))
                    .build();
    }

}