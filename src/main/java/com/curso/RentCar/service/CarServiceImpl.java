package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.repository.CarRepository;

public class CarServiceImpl implements CarService{
	
	@Autowired CarRepository carRepository;
	
	@Override
	public Page<CarEntity> findAll(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	@Override
	public Optional<CarEntity> save(CarEntity c) {
		return Optional.ofNullable(carRepository.save(c));
	}

	@Override
	public Optional<CarEntity> findById(Integer c) {
		return carRepository.findById(c);
	}

	@Override
	public void delete(CarEntity c) {
		carRepository.delete(c);
	}

}
