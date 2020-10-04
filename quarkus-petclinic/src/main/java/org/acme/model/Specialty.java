package org.acme.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="specialties")
@Cacheable
public class Specialty extends PanacheEntity {
  
    public String name;

    @ManyToMany(mappedBy = "specialties")
    public List<Vet> vets;

}