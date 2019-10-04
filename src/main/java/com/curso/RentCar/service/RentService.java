package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.RentEntity;

public interface RentService {

	/**
     * Metodo que devuelve todos los Rents
     * @return Devuelve todos los Car de la aplicacion
     */
	Page<RentEntity> findAll(Pageable pageable);
	/**
     * Metodo para guardar un Rent
     * @param Rent que va a ser guardado
     * @return Devuelve el Rent que va a ser guardado
     */
	Optional<RentEntity> save(RentEntity c) ;
	/**
	 * Metodo que busca un rent por id
	 * @param entero id
	 * @return rentEntity con id indicado
	 */
	Optional<RentEntity> findById(Integer c);
	/**
     * Metodo para eliminar un Rent
     * @param Rent que vamos a eliminar
     */
	void delete(RentEntity c);
}
