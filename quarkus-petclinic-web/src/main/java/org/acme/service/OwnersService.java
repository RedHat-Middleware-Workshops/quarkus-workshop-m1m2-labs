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

import org.acme.model.Owner;
import org.acme.model.Pet;
import org.acme.model.Visit;
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

    public List<Owner> findByLastName(String lastName) {

        if (lastName != null && !lastName.isEmpty()) {
            return Owner.find("LOWER(lastName) LIKE LOWER(?1) ",
                    Sort.by("firstName"), "%" + lastName + "%").list();
        } else {
            return Owner.listAll();
        }
    }

    public Owner findById(Long id) {
        Owner theOwner = Owner.findById(id.longValue());
        // assignPetVisits(theOwner);

        assignPetVisitsMulti(theOwner);

        return theOwner;
    }

    private void assignPetVisits(Owner theOwner) {
        List<Pet> pets = theOwner.pets;

        for (Pet tempPet : pets) {
            List<Visit> visits = visitsRestClient.visits(tempPet.id);
            tempPet.visits = visits;
        }
    }

    private void assignPetVisitsMulti(Owner theOwner) {
        List<Pet> thePets = theOwner.pets;

        System.out.println("assigning pets visits");

        Map<Long, Pet> thePetsMap = thePets.stream()
                .collect(Collectors.toMap(Pet::getId, pets -> pets));

        List<Long> petIds = new ArrayList<>(thePetsMap.keySet());
        List<Visit> visitsCollection = visitsRestClient.visitsMultiGet(petIds);

        visitsCollection.forEach(tempVisit -> {
            long petId = tempVisit.petId;

            Pet thePet = thePetsMap.get(petId);

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