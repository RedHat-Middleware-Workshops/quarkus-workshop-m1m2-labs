package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import org.acme.model.Owner;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnersService {
   
    public List<Owner> findByLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            return org.acme.model.Owner.find("LOWER(lastName) LIKE LOWER(?1) ",
                Sort.by("firstName"), "%" + lastName + "%").list();
        } else {
            return org.acme.model.Owner.listAll();
        }        
    } 

    public Owner findById(Long id) {
        return org.acme.model.Owner.findById(id.longValue());
    }

}
