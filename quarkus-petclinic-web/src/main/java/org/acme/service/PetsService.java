package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Pets;
import org.acme.model.Visits;
import org.acme.rest.client.VisitsRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetsService {
   
    @Inject
    @RestClient
    VisitsRestClient visitsRestClient;

    public Pets findById(Long id) {
        return Pets.findById(id.longValue());
    }

	public void addVisit(long petId, Visits theVisits) {
        visitsRestClient.create(petId, theVisits);
	}

}
