package org.acme.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name="owners")
@Cacheable
public class Owner extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "ownersSequence",
            sequenceName = "owners_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ownersSequence")
    public Long id;

    @Column(name = "first_name")
	@NotEmpty
	public String firstName;

	@Column(name = "last_name")
	@NotEmpty
	public String lastName;
    
    public String address;
    public String city;

    @NotEmpty
	@Digits(fraction = 0, integer = 10)
    public String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    public List<Pet> pets;
    
    public Long getId(){
        return id;
    }

}