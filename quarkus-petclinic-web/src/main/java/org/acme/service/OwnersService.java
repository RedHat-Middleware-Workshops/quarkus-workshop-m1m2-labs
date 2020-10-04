package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Owners;

import io.quarkus.panache.common.Sort;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnersService {
   
    public List<Owners> findByLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            return Owners.find("LOWER(lastName) LIKE LOWER(?1) ", 
                Sort.by("firstName"), "%" + lastName + "%").list();
        } else {
            return Owners.listAll();
        }        
    } 

    public Owners findById(Long id) {
        return Owners.findById(id.longValue());
    }

}
