package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.UserEntity;

public interface UserService {
	/**
     * Metodo que devuelve todos los Users
     * @return Devuelve todos los User de la aplicacion
     */
	Page<UserEntity> findAll(Pageable pageable);
	/**
     * Metodo para guardar un User
     * @param User que va a ser guardado
     * @return Devuelve el User que va a ser guardado
     * @throws BadRequestException 
     */
	Optional<UserEntity> save(UserEntity u) ;
	/**
     * Metodo para Buscar un User
     * @param id de User que va a ser buscado
     * @return devuelve el User con el id indicado
     */
	Optional<UserEntity> findById(Integer u);
	/**
     * Metodo para eliminar un User
     * @param User que vamos a eliminar
     */
	void delete(UserEntity u);
}
