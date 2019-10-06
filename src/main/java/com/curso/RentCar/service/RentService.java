package com.curso.RentCar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.RentCar.model.entity.CarEntity;
import com.curso.RentCar.model.entity.RentEntity;

public interface RentService {

	/**
     * Metodo que devuelve todos los Rents
     * @return Devuelve todos los Rent de la aplicacion Page<>
     */
	Page<RentEntity> findAll(Pageable pageable);
	/**
     * Metodo que devuelve todos los Rents
     * @return Devuelve todos los Rent de la aplicacion Page<>
     */
	List<RentEntity> findAll();
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
	/**
	 * 
	 * @param iniD fecha inicio 
	 * @param endD fecha fin de la reserva 
	 * @param car coche que se va a alquilar
	 * @return true si esta disponible para las fechas
	 */
	boolean carAviable(LocalDate iniD,LocalDate endD, CarEntity car);
}
