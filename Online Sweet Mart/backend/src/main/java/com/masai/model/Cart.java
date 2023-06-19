package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
		
//	@JsonIgnore
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	private List<Orders> orders;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private boolean active=true;
}
