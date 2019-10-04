package com.curso.RentCar.service.mapper;

import org.springframework.stereotype.Service;

@Service
public interface Mapper<O,I> {
	/**
	 * Mapeador Entity to Dto
	 * @param in
	 * @return
	 */
	public O map(I in);
}
