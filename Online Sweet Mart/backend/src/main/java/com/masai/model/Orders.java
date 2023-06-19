package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderId;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private boolean active=true;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;
//	@JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<Product> products;
	@JsonIgnore
	@OneToOne(mappedBy = "orders",cascade = CascadeType.ALL)
	private OrderBill orderBill;
	public boolean getActive() {
		return this.active;
	}
}
