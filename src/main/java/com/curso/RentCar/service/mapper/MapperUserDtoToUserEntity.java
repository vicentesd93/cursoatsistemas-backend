package com.curso.RentCar.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.RentCar.model.dto.UserDto;
import com.curso.RentCar.model.entity.UserEntity;

@Service
public class MapperUserDtoToUserEntity implements Mapper<UserEntity, UserDto>{

	@Override
	public UserEntity map(UserDto in) {
		return new UserEntity(in.getId(), in.getName(), in.getLastName(), null);
	}

}
