package com.curso.RentCar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.RentCar.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	/**
	 * Devuelve una pagina con todos los books
	 * @param pageable
	 * @return
	 */
	Page<UserEntity> findAll(Pageable pageable);
}
