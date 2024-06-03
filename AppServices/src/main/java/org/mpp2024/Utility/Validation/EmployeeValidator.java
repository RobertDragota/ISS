package org.mpp2024.Utility.Validation;


import org.mpp2024.Domain.Employee;

public class EmployeeValidator implements IValidator<Employee> {
    /**
     * @param entity
     * @throws ValidException
     */
    @Override
    public void validate(Employee entity) throws ValidException {

        if (entity == null)
            throw new ValidException("Entity is null");
        boolean validUsername = entity.getUsername().matches("^(\\w+)_p@\\d+$") || entity.getUsername().matches("^(\\w+)_v@\\d+$") || entity.getUsername().matches("^admin@\\d+$");
        if (entity.getUsername().isEmpty())
            throw new ValidException("Username is empty");
        if (entity.getUsername().matches(".*\\d+.*") && !validUsername)
            throw new ValidException("Username contains numbers");
        if (entity.getPassword().isEmpty())
            throw new ValidException("Password is empty");
        if (entity.getPassword().length() < 8)
            throw new ValidException("Password is too short");

    }

}
