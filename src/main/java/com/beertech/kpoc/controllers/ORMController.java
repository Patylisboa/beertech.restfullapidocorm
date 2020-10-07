package com.beertech.kpoc.controllers;

import com.beertech.kpoc.entities.ORM;
import com.beertech.kpoc.repositories.ORMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orms")
public class ORMController {
	@Autowired
	private ORMRepository repository;



	@GetMapping
	public List<ORM> findAllORMs() {
		return repository.findAll();
	}

	@GetMapping("/orm/{name}")
	public ORM findORMByName(@PathVariable String name) {
		Optional<ORM> orm = repository.findByName(name);
		return orm.get();
	}

	@PostMapping("/orms")
	public ResponseEntity<ORM> createORM(@RequestBody ORM orm) {
		ORM persistedORM = repository.save(orm);
		return ResponseEntity.ok(persistedORM);
	}
}
