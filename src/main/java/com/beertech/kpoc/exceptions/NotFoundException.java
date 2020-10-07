package com.beertech.kpoc.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("ORM not found");
    }

}
