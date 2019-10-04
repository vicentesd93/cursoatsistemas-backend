package com.curso.RentCar.service;

import com.curso.RentCar.model.dto.RentDto;
import com.curso.RentCar.model.entity.RentEntity;

public class MapperRentEntityToRentDto implements Mapper<RentDto, RentEntity> {

	@Override
	public RentDto map(RentEntity in) {
		return new RentDto(in.getId(), in.getInitD(), in.getEndD(), in.getCar(), in.getUser());
	}

}
