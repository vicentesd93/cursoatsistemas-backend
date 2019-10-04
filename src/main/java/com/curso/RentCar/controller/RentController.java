package com.curso.RentCar.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.RentCar.exception.CarNotAviableException;
import com.curso.RentCar.exception.NotFoundException;
import com.curso.RentCar.model.dto.RentDto;
import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.model.entity.RentEntity;
import com.curso.RentCar.model.entity.UserEntity;
import com.curso.RentCar.service.CarService;
import com.curso.RentCar.service.RentService;
import com.curso.RentCar.service.UserService;
import com.curso.RentCar.service.mapper.MapperRentDtoToRentEntity;
import com.curso.RentCar.service.mapper.MapperRentEntityToRentDto;

@RestController
@RequestMapping("/rent")
public class RentController {
	@Autowired RentService rentService;
	@Autowired CarService carService;
	@Autowired UserService userService;
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
	
	@PostMapping("/user/{idUser}/car/{idCar}")
	@ResponseStatus(code = HttpStatus.OK)
	public RentDto create(@RequestBody @Valid RentDto rentdto,
			@PathVariable("idUser") Integer iduser, @PathVariable("idCar") Integer idcar) 
					throws NotFoundException, CarNotAviableException {
		RentEntity entityStored = new RentEntity();
		UserEntity user = userService.findById(iduser).orElseThrow(() -> new NotFoundException());
		CarEntity car = carService.findById(idcar).orElseThrow(() -> new NotFoundException());
		
		//Comprobamos que el coche que queremos este disponible en las fechas indicadas
		Optional<RentEntity> rentAviable = rentService.findAll()
										.stream()
										.filter(x -> !(x.getEndD().isBefore(rentdto.getEndD()) &&
												x.getInitD().isAfter(rentdto.getInitD())) || 
												!(x.getEndD().isBefore(rentdto.getInitD()) &&
														x.getInitD().isAfter(rentdto.getEndD())) && 
												x.getCar().equals(car) ? true : false).
										findFirst();
		
		if(!rentAviable.isPresent()) {
			rentdto.setId(null);
			rentdto.setCar(car);
			rentdto.setUser(user);
			rentdto.setPrice(Math.abs(rentdto.getInitD().compareTo(rentdto.getEndD())) * car.getPrice());
			
			final RentEntity entityMapped = rentDtoToEntity.map(rentdto);
			entityStored = rentService.save(entityMapped).get();
		}else
			throw new CarNotAviableException("COCHE NO DISPONIBLE EN LAS FECHAS INDICADAS");	

		return rentEntityToDto.map(entityStored);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable("id") Integer id) throws NotFoundException{
		RentEntity user = rentService.findById(id).orElseThrow(() -> new NotFoundException());
		rentService.delete(user);
	}
}
