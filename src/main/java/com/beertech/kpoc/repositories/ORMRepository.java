package com.beertech.kpoc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beertech.kpoc.entities.ORM;

import java.util.Optional;

public interface ORMRepository extends JpaRepository<ORM, Long> {

	Optional<ORM> findByName(String name);
}
