package org.iss2024.bug_detection.Utility.Validation;

public interface IValidator<E> {

    /**
     * @param entity
     * @throws ValidException
     */
    void validate(E entity) throws ValidException;

}