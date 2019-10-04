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
import com.curso.RentCar.model.dto.RentDto;
import com.curso.RentCar.model.entity.RentEntity;
import com.curso.RentCar.service.MapperRentDtoToRentEntity;
import com.curso.RentCar.service.MapperRentEntityToRentDto;
import com.curso.RentCar.service.RentService;

@RestController
@RequestMapping("/rent")
public class RentController {
	@Autowired RentService rentService;
	@Autowired MapperRentEntityToRentDto rentEntityToDto;
	@Autowired MapperRentDtoToRentEntity rentDtoToEntity;
	
	@GetMapping
	public Page<RentDto> get(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false ,defaultValue = "10") Integer size){
		final Pageable pageable = PageRequest.of(page, size);
		 
		return rentService.findAll(pageable)
			.map(x -> rentEntityToDto.map(x));
	}
	
	@GetMapping("/{id}")
	public RentDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		rentService.findById(id).orElseThrow(() -> new NotFoundException());
		return rentEntityToDto.map(rentService.findById(id).get());
	}
	
	@PostMapping
	public RentDto create(@RequestBody @Valid RentDto rentdto) {
		final RentEntity entityMapped = rentDtoToEntity.map(rentdto);
		final RentEntity entityStored = rentService.save(entityMapped).get();

		return rentEntityToDto.map(entityStored);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException{
		RentEntity user = rentService.findById(id).orElseThrow(() -> new NotFoundException());
		rentService.delete(user);
	}
}
