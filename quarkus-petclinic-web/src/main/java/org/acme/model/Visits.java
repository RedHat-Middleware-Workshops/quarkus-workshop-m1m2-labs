package org.acme.model;

import java.time.LocalDate;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class Visits extends PanacheEntity {
  
	public long petId;

    @Column(name = "visit_date")
	public LocalDate date;

	@NotEmpty
	public String description;

	
}