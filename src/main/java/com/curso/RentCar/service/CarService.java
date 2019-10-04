package com.curso.RentCar.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.RentCar.model.entity.CarEntity;;

@Service
public interface CarService {
	/**
     * Metodo que devuelve todos los Cars
     * @return Devuelve todos los Car de la aplicacion
     */
	Page<CarEntity> findAll(Pageable pageable);
	/**
     * Metodo para guardar un Car
     * @param Car que va a ser guardado
     * @return Devuelve el Car que va a ser guardado
     */
	Optional<CarEntity> save(CarEntity c) ;
	/**
	 * Metodo que busca un coche por id
	 * @param entero id
	 * @return carEntity con id indicado
	 */
	Optional<CarEntity> findById(Integer c);
	/**
     * Metodo para eliminar un Car
     * @param Car que vamos a eliminar
     */
	void delete(CarEntity c);
}
