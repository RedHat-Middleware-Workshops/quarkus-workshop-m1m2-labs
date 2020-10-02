package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.model.Specialties;

@ApplicationScoped
public class SpecialtiesService {
    
    public List<Specialties> getAll() {
        return Specialties.listAll();
    }

}
