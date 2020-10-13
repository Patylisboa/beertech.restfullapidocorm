package com.ibm.kpoc.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("ORM not found");
    }

}
