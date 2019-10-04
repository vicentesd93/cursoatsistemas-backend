package com.curso.RentCar.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.RentCar.model.dto.CarDto;
import com.curso.RentCar.model.entity.CarEntity;

@Service
public class MapperCarEntityToCarDto implements Mapper<CarDto, CarEntity>{

	@Override
	public CarDto map(CarEntity in) {
		return new CarDto(in.getId(), in.getChassis_number(), in.getBrand(), in.getModel(),in.getPrice());
	}

}
