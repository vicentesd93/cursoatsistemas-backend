package com.curso.RentCar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.RentCar.exception.NotFoundException;
import com.curso.RentCar.model.dto.CarDto;
import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.service.CarService;
import com.curso.RentCar.service.MapperCarDtoToCarEntity;
import com.curso.RentCar.service.MapperCarEntityToCarDto;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired CarService carService;
	@Autowired MapperCarEntityToCarDto carEntitytoCarDto;
	@Autowired MapperCarDtoToCarEntity carDtoToCarEntity;
	
	@GetMapping
	public Page<CarDto> get(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false ,defaultValue = "10") Integer size){
		final Pageable pageable = PageRequest.of(page, size);
		 
		return carService.findAll(pageable)
			.map(x -> carEntitytoCarDto.map(x));
	}
	
	@GetMapping("/{id}")
	public CarDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		carService.findById(id).orElseThrow(() -> new NotFoundException());
		return carEntitytoCarDto.map(carService.findById(id).get());
	}
	
	@PostMapping
	public CarDto create(@RequestBody @Valid CarDto cardto) {
		final CarEntity entityMapped = carDtoToCarEntity.map(cardto);
		final CarEntity entityStored = carService.save(entityMapped).get();

		return carEntitytoCarDto.map(entityStored);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException{
		carService.findById(id).orElseThrow(() -> new NotFoundException());
		carService.delete(carService.findById(id).get());
	}
}
