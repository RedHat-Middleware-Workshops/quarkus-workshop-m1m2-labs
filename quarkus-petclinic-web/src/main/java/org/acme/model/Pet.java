package org.acme.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name="pets")
@Cacheable
public class Pet extends PanacheEntityBase {
  
	@Id
    @SequenceGenerator(
            name = "petsSequence",
            sequenceName = "pets_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petsSequence")
	public Long id;
	
	public String name;
	
    @Column(name = "birth_date")
	public LocalDate birthDate;

	@ManyToOne
	@JoinColumn(name = "type_id")
	public PetType type;

	public Object getPetType() {
		return this.type;
	}

	public void setPetType(PetType type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "owner_id")
	public Owner owner;

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	@Transient
	public List<Visit> visits;
	
	public Long getId(){
        return id;
    }
    
}