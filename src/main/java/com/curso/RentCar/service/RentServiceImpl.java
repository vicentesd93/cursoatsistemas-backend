package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.RentEntity;
import com.curso.RentCar.repository.RentRepository;


public class RentServiceImpl implements RentService{

	@Autowired RentRepository rentRepository;
	
	@Override
	public Page<RentEntity> findAll(Pageable pageable) {
		return rentRepository.findAll(pageable);
	}

	@Override
	public Optional<RentEntity> save(RentEntity r) {
		return Optional.ofNullable(rentRepository.save(r));
	}

	@Override
	public Optional<RentEntity> findById(Integer r) {
		return rentRepository.findById(r);
	}

	@Override
	public void delete(RentEntity r) {
		rentRepository.delete(r);		
	}

}
