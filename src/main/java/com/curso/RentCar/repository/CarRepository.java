package com.curso.RentCar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.RentCar.model.entity.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer>{
	/**
	 * Devuelve una pagina con todos los cars
	 * @param pageable
	 * @return
	 */
	Page<CarEntity> findAll(Pageable pageable);
}
