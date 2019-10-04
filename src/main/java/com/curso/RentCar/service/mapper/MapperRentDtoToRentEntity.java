package com.curso.RentCar.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.RentCar.model.dto.RentDto;
import com.curso.RentCar.model.entity.RentEntity;

@Service
public class MapperRentDtoToRentEntity implements Mapper<RentEntity, RentDto> {

	@Override
	public RentEntity map(RentDto in) {
		return new RentEntity(in.getId(), in.getInitD(), in.getEndD(), in.getPrice(),in.getCar(), in.getUser());
	}

}
