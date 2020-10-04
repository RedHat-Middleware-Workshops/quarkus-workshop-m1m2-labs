package org.acme.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Owners;
import org.acme.model.Pets;
import org.acme.model.Visits;
import org.acme.rest.client.VisitsRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.panache.common.Sort;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnersService {
   
    @Inject
    @RestClient
    VisitsRestClient visitsRestClient;

    public List<Owners> findByLastName(String lastName) {

        if (lastName != null && !lastName.isEmpty()) {
            return Owners.find("LOWER(lastName) LIKE LOWER(?1) ", 
                Sort.by("firstName"), "%" + lastName + "%").list();
        } else {
            return Owners.listAll();
        }        
    } 

    public Owners findById(Long id) {
        Owners theOwner = Owners.findById(id.longValue());
        // assignPetVisits(theOwner);
        
        assignPetVisitsMulti(theOwner);

        return theOwner;
    }

    private void assignPetVisits(Owners theOwner) {
        List<Pets> thePetsCollection = theOwner.pets;

        for (Pets tempPet : thePetsCollection) {
            List<Visits> theVisitsCollection = visitsRestClient.visits(tempPet.id);
            tempPet.visits = theVisitsCollection;
        }
    }

    private void assignPetVisitsMulti(Owners theOwner) {
        List<Pets> thePetsCollection = theOwner.pets;

        System.out.println("assigning pets visits");
        
        Map<Long, Pets> thePetsMap = thePetsCollection.stream()
                                                      .collect(Collectors.toMap(Pets::getId, pets -> pets));

        List<Long> petIds = new ArrayList<>(thePetsMap.keySet());
        List<Visits> visitsCollection = visitsRestClient.visitsMultiGet(petIds);

        visitsCollection.forEach(tempVisit -> {
            long petId = tempVisit.petId;

            Pets thePet = thePetsMap.get(petId);

            if (thePet.visits == null) {
                thePet.visits = new ArrayList<>();
            }

            thePet.visits.add(tempVisit);
        });

        /*
        for (Visits tempVisit : visitsCollection) {
            long petId = tempVisit.petId;

            Pets thePet = thePetsMap.get(petId);

            if (thePet.visits == null) {
                thePet.visits = new ArrayList<>();
            }

            thePet.visits.add(tempVisit);
        }
        */
    }

}
