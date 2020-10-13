package com.ibm.kpoc.controllers;

import com.ibm.kpoc.entities.ORM;
import com.ibm.kpoc.repositories.ORMRepository;
import com.ibm.kpoc.services.ORMService;

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

	@Autowired
	public ORMController(ORMService ormService) {
		this.ormService = ormService;
	}


	@GetMapping
	public ResponseEntity<List<ORM>> findAllORMs() {
		return ResponseEntity.ok(ormService.findAll());
	}

	@GetMapping("/orm/{name}")
	public ResponseEntity<ORM> findORMByName(@PathVariable String name) {
		Optional<ORM> orm = ormService.findByName(name);
		return orm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}

	@PostMapping
	public ResponseEntity<ORM> createORM(@RequestBody ORM orm) {
		ORM persistedORM = ormService.save(orm);
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