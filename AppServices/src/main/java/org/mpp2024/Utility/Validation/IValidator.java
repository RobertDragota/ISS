package org.mpp2024.Utility.Validation;

public interface IValidator<E> {

    /**
     * @param entity
     * @throws ValidException
     */
    void validate(E entity) throws ValidException;

}