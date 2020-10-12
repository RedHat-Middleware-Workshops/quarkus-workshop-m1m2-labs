package org.acme.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity(name = "vet_specialties")
@Cacheable
public class VetSpecialty extends PanacheEntity {

    @Column(name = "vet_id")
    public Long vetId;

    @Column(name = "specialty_id")
	public Long specialtyId;
    
}
