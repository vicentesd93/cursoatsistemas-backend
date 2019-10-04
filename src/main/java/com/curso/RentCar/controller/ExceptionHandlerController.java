package com.curso.RentCar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.curso.RentCar.exception.CarLinkedUserException;
import com.curso.RentCar.exception.CarNotAviableException;
import com.curso.RentCar.exception.NotFoundException;

@ControllerAdvice(basePackages = "com.curso.RentCar.controller")
public class ExceptionHandlerController {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public NotFoundException notFound(final NotFoundException e) {
		return e;
	}
	
	@ResponseBody
	@ExceptionHandler(CarLinkedUserException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public CarLinkedUserException carLinkedUser(final CarLinkedUserException e) {
		return e;
	}
	
	@ResponseBody
	@ExceptionHandler(CarNotAviableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public CarNotAviableException carLinkedUser(final CarNotAviableException e) {
		return e;
	}
	
}
