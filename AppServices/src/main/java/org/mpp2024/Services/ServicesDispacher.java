package org.mpp2024.Services;


import org.mpp2024.Domain.*;
import org.mpp2024.Services.Observer.AppObserverInterface;
import org.mpp2024.Utility.Validation.IValidator;
import org.mpp2024.Utility.Validation.ValidException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicesDispacher implements IServiceApp {
    private final ServiceProgrammer serviceProgrammer;
    private final ServiceValidator serviceValidator;
    private final ServiceAdmin serviceAdmin;
    private final ServiceBug serviceBug;
    private final IValidator<Employee> validator;
    private final ConcurrentHashMap<String, AppObserverInterface> loggedUsers;


    public ConcurrentHashMap<String, AppObserverInterface> getLoggedUsers() {
        return loggedUsers;
    }

    public ServicesDispacher(ServiceProgrammer serviceProgrammer, ServiceValidator serviceValidator, ServiceAdmin serviceAdmin, IValidator<Employee> validator, ServiceBug serviceBug) {
        this.serviceProgrammer = serviceProgrammer;
        this.serviceValidator = serviceValidator;
        this.serviceAdmin = serviceAdmin;
        this.validator = validator;
        this.serviceBug = serviceBug;
        loggedUsers = new ConcurrentHashMap<>();
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

    public ServiceBug getServiceBug() {return serviceBug;}



    @Override
    public synchronized Employee login( Employee employee,AppObserverInterface observer) {
        loggedUsers.put(employee.getUsername(), observer);

        return employee;
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


                return serviceProgrammer.save(new Programmer(programmerUseranme, password)).orElse(null);
            }
            case "Validator" -> {
                String validatorUsername = username.toLowerCase() + "_v@" + serviceValidator.size();

                return serviceValidator.save(new Validator(validatorUsername, password)).orElse(null);
            }
            default -> throw new ValidException("Invalid role");
        }
    }

    @Override
    public Employee getEmployee(String username, String password) {

        validator.validate(new Employee(username, password));

        if (username.matches("^(\\w+)_p@\\d+$")) {
            Programmer programmer = serviceProgrammer.findByUsername(username);
            if (programmer != null)
                if (programmer.getPassword().equals(password)) {
                    return programmer;
                } else throw new ValidException("Invalid Password");
        }
        if (username.matches("^(\\w+)_v@\\d+$")) {
            Validator validator = serviceValidator.findByUsername(username);
            if (validator != null)
                if (validator.getPassword().equals(password)) {
                    return validator;
                } else throw new ValidException("Invalid Password");
        }

        if (username.matches("^admin@\\d+$")) {
            Admin admin = serviceAdmin.findByUsername(username);
            if (admin != null)
                if (admin.getPassword().equals(password)) {

                    return admin;
                } else throw new ValidException("Invalid Password");
        }
        throw new ValidException("There is no user with this username and password");
    }

    @Override
    public void logout(Employee employee) {
        loggedUsers.remove(employee.getUsername());
    }

    @Override
    public void notify(Iterable<Programmer> programmers, Iterable<Validator> validators, Iterable<Bug> bugs) {
        System.out.println("Notify");

        int defaultThreadsNo = 5;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
        for (String username : loggedUsers.keySet()) {
            AppObserverInterface client = loggedUsers.get(username);
            if (client != null)
                executor.execute(() -> {
                    try {
                        System.out.println("Notifying [" + username + "]");

                        try {
                            client.update(programmers, validators, bugs);
                        } catch (Exception e) {
                            System.err.println("Error notifying " + e);
                        }

                    } catch (Exception e) {
                        System.err.println("Error notifying " + e);
                    }
                });
        }

        executor.shutdown();
    }


}
