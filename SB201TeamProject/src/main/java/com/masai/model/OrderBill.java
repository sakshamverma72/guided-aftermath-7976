package com.masai.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
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
	private int totalProductCount;
	@Min(1)
	private double totalPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Orders order1;
}
