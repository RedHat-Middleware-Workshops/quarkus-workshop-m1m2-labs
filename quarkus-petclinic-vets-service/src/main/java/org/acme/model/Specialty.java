package org.acme.model;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity(name="specialties")
@Cacheable
public class Specialty extends PanacheEntity {
  
    public String name;

    @JsonbTransient
    @ManyToMany(mappedBy = "specialties")
    public List<Vet> vets;

}