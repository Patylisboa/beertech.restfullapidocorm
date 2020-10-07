package com.beertech.kpoc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orms")
public class ORMController {


    private final ORMService ormService;

    @Autowired
    public ORMController(ORMService ormService) {
        this.ormService = ormService;
    }
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