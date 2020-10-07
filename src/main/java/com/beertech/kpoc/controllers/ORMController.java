package com.beertech.kpoc.controllers;

import com.beertech.kpoc.entities.ORM;
import com.beertech.kpoc.repositories.ORMRepository;
import com.beertech.kpoc.services.ORMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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