package com.beertech.kpoc.services;

import com.beertech.kpoc.entities.ORM;
import com.beertech.kpoc.exceptions.NotFoundException;
import com.beertech.kpoc.repositories.ORMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ORMService {

    private final ORMRepository ormRepository;

    @Autowired
    public ORMService(ORMRepository ormRepository) {
        this.ormRepository = ormRepository;
    }

    public ORM update(Long id, ORM orm) {
        ORM ormToUpdate = this.ormRepository.findById(id).orElseThrow(NotFoundException::new);
        ormToUpdate.setName(orm.getName());
        return this.ormRepository.save(ormToUpdate);
    }

    public void remove(Long ormId) {
        this.ormRepository.deleteById(ormId);
    }

    public List<ORM> findAll() {
        return this.ormRepository.findAll();
    }

    public Optional<ORM> findByName(String name) {
        return this.ormRepository.findByName(name);
    }

    public ORM save(ORM orm) {
        return this.ormRepository.save(orm);
    }
}
