package org.acme.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import org.acme.model.Vet;
import org.acme.rest.client.VetsRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("vets")
public class VetsResource {

    @Inject
    @RestClient
    VetsRestClient vetsRestClient;

    @Inject
    Template vets;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {

        System.out.println("Calling vetsRestClient");
        List<Vet> data = vetsRestClient.getAll();
        System.out.println("Received data from vetsRestClient: " + data);

        return vets.data("active", "vets")
                .data("vets", data);
    }
}