package com.curso.RentCar.service;

import com.curso.RentCar.model.dto.CarDto;
import com.curso.RentCar.model.entity.CarEntity;

public class MapperCarDtoToCarEntity implements Mapper<CarEntity, CarDto>{

	@Override
	public CarEntity map(CarDto in) {
		return new CarEntity(in.getId(), in.getChassis_number(), in.getBrand(), in.getModel());
	}

}
