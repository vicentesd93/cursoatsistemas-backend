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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.RentCar.model.dto.UserDto;
import com.curso.RentCar.model.entity.UserEntity;
import com.curso.RentCar.service.MapperUserDtoToUserEntity;
import com.curso.RentCar.service.MapperUserEntityToUserDto;
import com.curso.RentCar.service.UserService;

import com.curso.RentCar.exception.NotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired UserService userService;
	@Autowired MapperUserEntityToUserDto userEntityToDto;
	@Autowired MapperUserDtoToUserEntity userDtoToEntity;
	
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
	public void delete(@PathVariable("id") Integer id) throws NotFoundException{
		UserEntity user = userService.findById(id).orElseThrow(() -> new NotFoundException());
		userService.delete(user);
	}
	
	@PutMapping("/{id}/car/{idCar}")
	public void userLinkCar(@PathVariable("id") Integer id, @PathVariable("idCar") Integer idcar) throws NotFoundException {
		UserEntity user = userService.findById(id).orElseThrow(() -> new NotFoundException());
		/*TERMINAR*/
	}
}
