package com.task.notes.commons.exception;

public class ResourceNotFoundException extends RuntimeException {

    protected ResourceNotFoundException(
            final Class<?> resourceType,
            final String identifier,
            final Throwable cause
    ) {
        super(String.format("No %s can be found by given %s.", resourceType.getSimpleName(), identifier), cause);
    }

    public static ResourceNotFoundException createInstance(
            final Class<?> resourceType,
            final String fieldName,
            final Object fieldValue
    ) {
        return new ResourceNotFoundException(resourceType, formatFieldValue(fieldName, fieldValue), null);
    }

    private static String formatFieldValue(final String key, final Object value) {
        return String.format(
                "%s: %s",
                key,
                value instanceof String ? String.format("'%s'", value) : value
        );
    }
}
