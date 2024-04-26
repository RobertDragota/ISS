package org.iss2024.bug_detection.Services;

import org.iss2024.bug_detection.Domain.Admin;
import org.iss2024.bug_detection.Domain.Employee;
import org.iss2024.bug_detection.Repository.RepoAdmin;
import org.iss2024.bug_detection.Repository.Repo_Admin_Interface;
import org.iss2024.bug_detection.Utility.Validation.IValidator;

import java.util.Optional;

public class ServiceAdmin implements IServiceAdmin{

    private final Repo_Admin_Interface repoAdmin;
    private IValidator<Employee> employeeValidator;

    public ServiceAdmin(Repo_Admin_Interface repoAdmin, IValidator<Employee> employeeValidator) {
        this.repoAdmin = repoAdmin;
        this.employeeValidator = employeeValidator;
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public Optional<Admin> findById(String id) {
        return repoAdmin.findById(id);
    }
}
