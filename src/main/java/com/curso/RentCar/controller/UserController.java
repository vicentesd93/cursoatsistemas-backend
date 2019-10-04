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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.RentCar.model.dto.UserDto;
import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.model.entity.UserEntity;
import com.curso.RentCar.service.CarService;
import com.curso.RentCar.service.UserService;
import com.curso.RentCar.service.mapper.MapperUserDtoToUserEntity;
import com.curso.RentCar.service.mapper.MapperUserEntityToUserDto;
import com.curso.RentCar.exception.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired UserService userService;
	@Autowired MapperUserEntityToUserDto userEntityToDto;
	@Autowired MapperUserDtoToUserEntity userDtoToEntity;
	
	@Autowired CarService carService;
	
	@GetMapping
	public Page<UserDto> get(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false ,defaultValue = "10") Integer size){
		
		final Pageable pageable = PageRequest.of(page, size);
		 
		return userService.findAll(pageable)
			.map(x -> userEntityToDto.map(x));
	}
	
	@GetMapping("/{id}")
	public UserDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		
		userService.findById(id).orElseThrow(() -> new NotFoundException());
		return userEntityToDto.map(userService.findById(id).get());
	}
	
	@PostMapping
	public UserDto create(@RequestBody @Valid UserDto userdto) {
		
		final UserEntity entityMapped = userDtoToEntity.map(userdto);
		final UserEntity entityStored = userService.save(entityMapped).get();

		return userEntityToDto.map(entityStored);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable("id") Integer id) throws NotFoundException{
		
		UserEntity user = userService.findById(id).orElseThrow(() -> new NotFoundException());
		userService.delete(user);
	}
	
	@PutMapping("/{id}/car/{idCar}")
	@ResponseStatus(code = HttpStatus.OK)
	public void userLinkCar(@PathVariable("id") Integer id, @PathVariable("idCar") Integer idcar) 
			throws NotFoundException, CarLinkedUserException {
		
		UserEntity user = userService.findById(id).orElseThrow(() -> new NotFoundException());
		CarEntity car = carService.findById(idcar).orElseThrow(() -> new NotFoundException());
		
		//Comprobamos que no lo tiene ya el coche este usuario
		Optional<CarEntity> carLinked = user.getCars()
											.stream()
											.filter(x -> x.getId() == idcar ? true:false).
										    findFirst();
		
		if (!carLinked.isPresent() && car.getUser() == null) { //Car no puede tener un User asignado
			user.getCars().add(car);
			car.setUser(user);
			System.out.println(user.toString());
			userService.save(user);
		}else
			throw new CarLinkedUserException();
		
	}
}
