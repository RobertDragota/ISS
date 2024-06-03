package org.mpp2024.Services;

import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Employee;
import org.mpp2024.Repository.Repo_Bug_Interface;
import org.mpp2024.Repository.Repo_Programmer_Interface;
import org.mpp2024.Utility.Validation.IValidator;

import java.util.Optional;

public class ServiceBug implements IServiceBug{

    private final Repo_Bug_Interface repoProgrammer;


    public ServiceBug(Repo_Bug_Interface repoProgrammer) {
        this.repoProgrammer = repoProgrammer;

    }

    @Override
    public Optional<Bug> save(Bug entity) {
        try {

            return repoProgrammer.save(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bug> delete(Bug entity) {
        try {

            return repoProgrammer.delete(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bug> update(Bug entity) {
        try {

            return repoProgrammer.update(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bug> findById(Long aLong) {
        return repoProgrammer.findById(aLong);
    }

    @Override
    public Iterable<Bug> findAll() {
        return repoProgrammer.findAll();
    }

    @Override
    public Long size() {
        return repoProgrammer.size();
    }
}
