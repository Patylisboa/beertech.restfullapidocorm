package com.beertech.kpoc.controllers;

import com.beertech.kpoc.entities.ORM;
import com.beertech.kpoc.repositories.ORMRepository;
import com.beertech.kpoc.services.ORMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orms")
public class ORMController {

	private final ORMService ormService;
	private final ORMRepository repository;

	@Autowired
	public ORMController(ORMService ormService, ORMRepository repository) {
		this.ormService = ormService;
		this.repository = repository;
	}


	@GetMapping
	public ResponseEntity<List<ORM>> findAllORMs() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/orm/{name}")
	public ResponseEntity<ORM> findORMByName(@PathVariable String name) {
		Optional<ORM> orm = repository.findByName(name);
		return orm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}

	@PostMapping("/orms")
	public ResponseEntity<ORM> createORM(@RequestBody ORM orm) {
		ORM persistedORM = repository.save(orm);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
				.buildAndExpand(persistedORM.getId()).toUri();

		return ResponseEntity.created(location).build();
	}


	@PutMapping("/{ormId}")
	public ORM update(@PathVariable Long ormId, @RequestBody ORM ormToUpdate) {
		return ormService.update(ormId, ormToUpdate);
	}

	@DeleteMapping("/{ormId}")
	public ResponseEntity remove(@PathVariable Long ormId) {
		ormService.remove(ormId);
		return ResponseEntity.status(204).build();
	}
}