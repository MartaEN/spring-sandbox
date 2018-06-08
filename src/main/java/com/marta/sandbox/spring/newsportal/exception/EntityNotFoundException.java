package com.marta.sandbox.spring.newsportal.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(long id, Class aClass) {
        super("Entity " + aClass.getCanonicalName() + " not found with id " + id);
    }

    public EntityNotFoundException(String id, Class aClass) {
        super("Entity " + aClass.getCanonicalName() + " not found with id " + id);
    }

}
