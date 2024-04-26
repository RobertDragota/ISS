package org.iss2024.bug_detection.Services;

import org.iss2024.bug_detection.Domain.Admin;
import org.iss2024.bug_detection.Domain.Employee;
import org.iss2024.bug_detection.Domain.Programmer;
import org.iss2024.bug_detection.Domain.Validator;
import org.iss2024.bug_detection.Utility.Validation.IValidator;
import org.iss2024.bug_detection.Utility.Validation.ValidException;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicesDispacher implements IServiceApp {
    private final ServiceProgrammer serviceProgrammer;
    private final ServiceValidator serviceValidator;
    private final ServiceAdmin serviceAdmin;
    private final IValidator<Employee> validator;

    public ServicesDispacher(ServiceProgrammer serviceProgrammer, ServiceValidator serviceValidator, ServiceAdmin serviceAdmin, IValidator<Employee> validator) {
        this.serviceProgrammer = serviceProgrammer;
        this.serviceValidator = serviceValidator;
        this.serviceAdmin = serviceAdmin;
        this.validator = validator;
    }

    public ServiceProgrammer getServiceProgrammer() {
        return serviceProgrammer;
    }

    public ServiceValidator getServiceValidator() {
        return serviceValidator;
    }

    public ServiceAdmin getServiceAdmin() {
        return serviceAdmin;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee login(String username, String password) {

        validator.validate(new Employee(username, password));

        if (username.matches("^(\\w+)_p@\\d+$")) {
            Programmer programmer = serviceProgrammer.findById(username).orElse(null);
            if (programmer != null)
                if (programmer.getPassword().equals(password)) {
                    return programmer;
                } else throw new ValidException("Invalid Password");
        }
        if (username.matches("^(\\w+)_v@\\d+$")) {
            Validator validator = serviceValidator.findById(username).orElse(null);
            if (validator != null)
                if (validator.getPassword().equals(password)) {
                    return validator;
                } else throw new ValidException("Invalid Password");
        }

        if (username.matches("^admin@\\d+$")) {
            Admin admin = serviceAdmin.findById(username).orElse(null);
            if (admin != null)
                if (admin.getPassword().equals(password)) {
                    return admin;
                } else throw new ValidException("Invalid Password");
        }
        throw new ValidException("There is no user with this username and password");
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee register(String username, String password, String role) {
        var entity = new Employee(username, password);
        validator.validate(entity);
        switch (role) {
            case "Programmer" -> {
                String programmerUseranme = username.toLowerCase() + "_p@" + serviceProgrammer.size();
                Programmer programmer = serviceProgrammer.findById(programmerUseranme).orElse(null);
                if (programmer != null) {
                    throw new ValidException("Programmer already exists");
                }

                return serviceProgrammer.save(new Programmer(programmerUseranme, password)).orElse(null);
            }
            case "Validator" -> {
                String validatorUsername = username.toLowerCase() + "_v@" + serviceValidator.size();
                Validator validator = serviceValidator.findById(validatorUsername).orElse(null);
                if (validator != null) {
                    throw new ValidException("Validator already exists");
                }
                return serviceValidator.save(new Validator(validatorUsername, password)).orElse(null);
            }
            default -> throw new ValidException("Invalid role");
        }
    }
}
