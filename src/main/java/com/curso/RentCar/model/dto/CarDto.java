package com.curso.RentCar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CarDto {

	private Integer id;
	private String chassis_number;
	private String brand;
	private String model;
}
