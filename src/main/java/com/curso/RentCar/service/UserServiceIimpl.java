package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.RentCar.model.entity.UserEntity;
import com.curso.RentCar.repository.UserRepository;

@Service
public class UserServiceIimpl implements UserService{

	@Autowired UserRepository userRepository;
	
	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public Optional<UserEntity> save(UserEntity u) {
		return Optional.ofNullable(userRepository.save(u));
	}

	@Override
	public Optional<UserEntity> findById(Integer u) {
		return userRepository.findById(u);
	}

	@Override
	public void delete(UserEntity u) {
		userRepository.delete(u);
	}

}
