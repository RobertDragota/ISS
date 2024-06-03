package org.mpp2024.Services;


import org.mpp2024.Domain.Employee;
import org.mpp2024.Domain.Programmer;

import org.mpp2024.Repository.Repo_Programmer_Interface;
import org.mpp2024.Utility.Validation.IValidator;

import java.util.Optional;

public class ServiceProgrammer implements IServiceProgrammer{

    private final Repo_Programmer_Interface repoProgrammer;
    private final IValidator<Employee> employeeValidator;

    public ServiceProgrammer(Repo_Programmer_Interface repoProgrammer, IValidator<Employee> employeeValidator) {
        this.repoProgrammer = repoProgrammer;
        this.employeeValidator = employeeValidator;
    }
    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Programmer> save(Programmer entity) {
        try {
            employeeValidator.validate(entity);
            return repoProgrammer.save(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Programmer> delete(Programmer entity) {
        try {
            employeeValidator.validate(entity);
            return repoProgrammer.delete(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param entity 
     * @return
     */
    @Override
    public Optional<Programmer> update(Programmer entity) {
        try {
            employeeValidator.validate(entity);
            return repoProgrammer.update(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param s 
     * @return
     */
    @Override
    public Optional<Programmer> findById(Long s) {
        return repoProgrammer.findById(s);
    }

    /**
     * @return 
     */
    @Override
    public Iterable<Programmer> findAll() {
        return repoProgrammer.findAll();
    }

    /**
     * @return 
     */
    @Override
    public Long size() {
        return repoProgrammer.size();
    }

    @Override
    public Programmer findByUsername(String username) {
        return repoProgrammer.findByUsername(username);
    }
}
