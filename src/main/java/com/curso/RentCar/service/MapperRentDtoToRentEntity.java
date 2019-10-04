package com.curso.RentCar.service;

import com.curso.RentCar.model.dto.RentDto;
import com.curso.RentCar.model.entity.RentEntity;

public class MapperRentDtoToRentEntity implements Mapper<RentEntity, RentDto> {

	@Override
	public RentEntity map(RentDto in) {
		return new RentEntity(in.getId(), in.getInitD(), in.getEndD(), in.getCar(), in.getUser());
	}

}
