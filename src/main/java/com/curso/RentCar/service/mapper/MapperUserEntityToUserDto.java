package com.curso.RentCar.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.RentCar.model.dto.UserDto;
import com.curso.RentCar.model.entity.UserEntity;

@Service
public class MapperUserEntityToUserDto implements Mapper<UserDto, UserEntity>{

	@Override
	public UserDto map(UserEntity in) {
		return new UserDto(in.getId(), in.getName(), in.getLastName());
	}

}
