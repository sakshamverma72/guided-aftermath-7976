package com.masai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class OrderBill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderBillId;
	@Min(1)
	@NotNull(message = "Count Can't be Null")
	private int totalProductCount;
	@Min(1)
	@NotNull(message = "Price Can't be Null")
	private double totalPrice;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private boolean active = true;
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToOne(cascade = CascadeType.ALL)
	private Orders orders;
	public boolean getActive() {
		return this.active;
	}
}
