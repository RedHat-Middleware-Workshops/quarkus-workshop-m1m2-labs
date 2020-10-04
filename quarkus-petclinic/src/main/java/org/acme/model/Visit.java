package org.acme.model;

import java.time.LocalDate;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="visits")
@Cacheable
public class Visit extends PanacheEntity {
  
	@ManyToOne
	@JoinColumn(name = "pet_id")
	public Pet pet;

    @Column(name = "visit_date")
	public LocalDate date;

	@NotEmpty
	public String description;

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
}