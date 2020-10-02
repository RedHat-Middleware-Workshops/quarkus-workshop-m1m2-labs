package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.model.Vets;

@ApplicationScoped
public class VetsService {
   
    public List<Vets> getAll() {
        return Vets.listAll();
    }

}
