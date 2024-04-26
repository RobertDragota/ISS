package org.iss2024.bug_detection.Utility.Validation;

import org.iss2024.bug_detection.Domain.Employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
