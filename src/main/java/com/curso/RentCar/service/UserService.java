package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.UserEntity;

public interface UserService {
	/**
     * Metodo que devuelve todos los Rents
     * @return Devuelve todos los Rent de la aplicacion
     */
	Page<UserEntity> findAll(Pageable pageable);
	/**
     * Metodo para guardar un Rent
     * @param Rent que va a ser guardado
     * @return Devuelve el Rent que va a ser guardado
     */
	Optional<UserEntity> save(UserEntity u) ;
	/**
     * Metodo para Buscar un Rent
     * @param id de Rent que va a ser buscado
     * @return devuelve el Rent con el id indicado
     */
	Optional<UserEntity> findById(Integer u);
	/**
     * Metodo para eliminar un Rent
     * @param Rent que vamos a eliminar
     */
	void delete(UserEntity u);
}
