package org.acme.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "types")
@Cacheable
public class PetType extends PanacheEntity {
  
    public String name;

    public static PetType findByName(String name) {
        return find("name", name).firstResult();
    }
    
}