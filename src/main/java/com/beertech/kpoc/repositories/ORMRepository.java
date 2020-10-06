package com.beertech.kpoc.repositories;

import com.beertech.kpoc.entities.ORM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMRepository extends JpaRepository<ORM, Long> {
}
