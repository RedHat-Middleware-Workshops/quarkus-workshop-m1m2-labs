package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Specialties;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialtiesService {
    
    public List<Specialties> getAll() {
        return Specialties.listAll();
    }

}
