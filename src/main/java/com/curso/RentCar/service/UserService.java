package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.RentEntity;

public interface UserService {
	/**
     * Metodo que devuelve todos los Rents
     * @return Devuelve todos los Rent de la aplicacion
     */
	Page<RentEntity> findAll(Pageable pageable);
	/**
     * Metodo para guardar un Rent
     * @param Rent que va a ser guardado
     * @return Devuelve el Rent que va a ser guardado
     */
	Optional<RentEntity> save(RentEntity u) ;
	/**
     * Metodo para Buscar un Rent
     * @param id de Rent que va a ser buscado
     * @return devuelve el Rent con el id indicado
     */
	Optional<RentEntity> findById(Integer u);
	/**
     * Metodo para eliminar un Rent
     * @param Rent que vamos a eliminar
     */
	void delete(RentEntity u);
}
