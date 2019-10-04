package com.curso.RentCar.service;

import com.curso.RentCar.model.dto.CarDto;
import com.curso.RentCar.model.entity.CarEntity;

public class MapperCarEntityToCarDto implements Mapper<CarDto, CarEntity>{

	@Override
	public CarDto map(CarEntity in) {
		return new CarDto(in.getId(), in.getChassis_number(), in.getBrand(), in.getModel());
	}

}
