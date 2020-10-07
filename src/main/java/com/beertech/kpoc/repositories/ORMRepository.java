package com.beertech.kpoc.repositories;

import com.beertech.kpoc.entities.ORM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ORMRepository extends JpaRepository<ORM, Long> {

	Optional<ORM> findByName(String name);
}
