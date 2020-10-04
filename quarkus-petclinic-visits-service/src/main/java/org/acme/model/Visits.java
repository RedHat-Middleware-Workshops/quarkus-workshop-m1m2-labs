package org.acme.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
// @Cacheable
public class Visits extends PanacheEntity {
  
	@Column(name = "pet_id")
	public long petId;

    @Column(name = "visit_date")
	public LocalDate date;

	@NotEmpty
	public String description;

	public static List<Visits> findByPetId(long petId) {
		return list("petId", petId);
	}

	public static List<Visits> findByMultiPetIds(List<Long> petIds) {
		return list("petId in (?1)", petIds);
	}

	@Override
	public String toString() {
		return "Visits [date=" + date + ", description=" + description + ", petId=" + petId + ", id=" + id + "]";
	}
	
}