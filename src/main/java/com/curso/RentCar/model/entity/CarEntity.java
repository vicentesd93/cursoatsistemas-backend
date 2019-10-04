package com.curso.RentCar.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column 
	private String chassis_number;
	@Column
	private String brand;
	@Column 
	private String model;
	@Column
	private Double price;
	@ManyToOne
	private UserEntity user;
	
	@Override
	public String toString() {
        return "Car [id=" + id + ", chasis_number=" + chassis_number + ", brand=" + brand + 
        		", model=" + model + ", price="+ price + ", user.id="+ user.getId() + "]";
    }
}
