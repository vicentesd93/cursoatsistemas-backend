package com.curso.RentCar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.model.entity.RentEntity;
import com.curso.RentCar.repository.RentRepository;

@Service
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

	@Override
	public List<RentEntity> findAll() {
		return rentRepository.findAll();
	}

	@Override
	public boolean carAviable(LocalDate iniD, LocalDate endD, CarEntity car) {
		Optional<RentEntity> rentAviable = rentRepository.findAll()
				.stream()
				.filter(x -> !(iniD.isAfter(x.getEndD()) &&
						endD.isAfter(x.getEndD())) && 
						!(iniD.isBefore(x.getInitD()) &&
								endD.isBefore(x.getInitD())) && 
						x.getCar().equals(car) ? true : false)
				.findFirst();

		if(!rentAviable.isPresent())
			return true;
		else
			return false;
	}

}
