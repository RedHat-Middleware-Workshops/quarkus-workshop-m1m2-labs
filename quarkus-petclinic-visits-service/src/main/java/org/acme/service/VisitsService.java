package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.model.Visits;
import org.jboss.logging.Logger;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitsService {
   
    private static final Logger LOG = Logger.getLogger(VetsResource.class);

    public List<Visits> findByPetId(Long id) {
        return Visits.findByPetId(id.longValue());
    }

	public List<Visits> getAllVisits() {
		return Visits.listAll();
	}

	public List<Visits> findByMultiPetIds(List<Long> petIds) {
		return Visits.findByMultiPetIds(petIds);
	}

    @Transactional
	public void save(Visits theVisits) {

        LOG.info("Persisting: " + theVisits);

        theVisits.persist();
	}

}
