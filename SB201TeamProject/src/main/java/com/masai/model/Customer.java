package com.masai.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotNull(message = "Name of customer can't be Null")
	private String name;
	@NotNull(message = "Email of customer can't be Null")
	@Email(message = "Email should be in proper Format")
	private String mailOrUsername;
	@NotNull(message = "Password of customer can't be Null")
	private String password;
	@JsonIgnore
	private boolean active=true;
	@JsonIgnore
	private final String role = "ROLE_CUSTOMER";
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	private Cart cart;
	
}
