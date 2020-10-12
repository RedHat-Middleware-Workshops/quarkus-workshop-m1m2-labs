package org.acme.model;

import java.time.LocalDate;
import javax.ws.rs.FormParam;

public class PetForm {

    public @FormParam("name") String name;
    public @FormParam("birthDate") LocalDate birthDate;
    public @FormParam("type") String type;

    public Pet addPet() {
        Pet newPet = new Pet();
        newPet.name = name;
        newPet.birthDate = birthDate;
        return newPet;
    }

    public Pet editPet(Pet existingPet) {
        existingPet.name = name;
        existingPet.birthDate = birthDate;
        return existingPet;
    }
    
}
