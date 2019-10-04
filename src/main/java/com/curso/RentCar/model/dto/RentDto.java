package com.curso.RentCar.model.dto;

import java.time.LocalDate;

import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.model.entity.UserEntity;

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

public class RentDto {
	
	private Integer id;
	private LocalDate initD;
	private LocalDate endD;
	private Double price;
	private CarEntity car;
	private UserEntity user;
}
