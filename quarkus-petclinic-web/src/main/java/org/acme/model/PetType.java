package org.acme.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity(name = "types")
@Cacheable
public class PetType extends PanacheEntity {
  
    public String name;

    public static PetType findByName(String name) {
        return find("name", name).firstResult();
    }
    
}