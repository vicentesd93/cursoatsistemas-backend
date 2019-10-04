package com.curso.RentCar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.RentCar.model.entity.RentEntity;

@Repository
public interface RentRepository extends JpaRepository<RentEntity, Integer>{
	/**
	 * Devuelve una pagina con todos los Rents
	 * @param pageable
	 * @return
	 */
	Page<RentEntity> findAll(Pageable pageable);
}
