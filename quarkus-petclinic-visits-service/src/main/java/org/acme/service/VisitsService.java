package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Visit;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitsService {
   

    public List<Visit> findByPetId(Long id) {
        return Visit.findByPetId(id.longValue());
    }

	public List<Visit> getAllVisits() {
		return Visit.listAll();
	}

	public List<Visit> findByMultiPetIds(List<Long> petIds) {
		return Visit.findByMultiPetIds(petIds);
	}

    @Transactional
	public void save(Visit theVisit) {

    	System.out.println("saving: " + theVisit);
        theVisit.persist();
	}

}
