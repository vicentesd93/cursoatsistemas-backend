package com.curso.RentCar.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.RentCar.model.dto.RentDto;
import com.curso.RentCar.model.entity.RentEntity;

@Service
public class MapperRentEntityToRentDto implements Mapper<RentDto, RentEntity> {

	@Override
	public RentDto map(RentEntity in) {
		return new RentDto(in.getId(), in.getInitD(), in.getEndD(), in.getPrice(), in.getCar(), in.getUser());
	}

}
