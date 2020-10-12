package org.acme.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name="visits")
@Cacheable
public class Visit extends PanacheEntityBase {
  
	@Id
    @SequenceGenerator(
            name = "visitsSequence",
            sequenceName = "visits_id_seq",
            allocationSize = 1,
            initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitsSequence")
	public Long id;
	
	@Column(name = "pet_id")
	public long petId;

    @Column(name = "visit_date")
	public LocalDate date;

	@NotEmpty
	public String description;

	public static List<Visit> findByPetId(long petId) {
		return list("petId", petId);
	}

	public static List<Visit> findByMultiPetIds(List<Long> petIds) {
		return list("petId in (?1)", petIds);
	}

	@Override
	public String toString() {
		return "Visits [date=" + date + ", description=" + description + ", petId=" + petId + ", id=" + id + "]";
	}
	
}