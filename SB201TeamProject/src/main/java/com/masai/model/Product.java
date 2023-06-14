package com.masai.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@NotNull(message = "Name of product can't be Null")
	private String name;
	@NotNull(message = "Image of product can't be Null")
	private String imageUrl;
	@Min(1)
	private Double price;
	@NotNull(message = "Description of product can't be Null")
	private String description;
	@NotNull(message = "Avalaible or not, fill the field")
	private Boolean availablility;
	@JsonIgnore
	private boolean active=true;
	@JsonIgnore
	@OneToOne(mappedBy = "products",cascade = CascadeType.ALL)
	private Category category;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Orders order;
}
