package com.exadel.placebook.model.exception;

public class EntityNotFoundException extends RuntimeException {
    private Class<?> entityClass;

    public EntityNotFoundException(Class<?> entityClass, Long entityId) {
        super(String.format("entity %s with id %d not found",entityClass.getSimpleName(), entityId));
        this.entityClass = entityClass;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }
}
