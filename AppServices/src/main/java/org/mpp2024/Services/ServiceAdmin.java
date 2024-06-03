package org.mpp2024.Services;



import org.mpp2024.Domain.Admin;
import org.mpp2024.Domain.Employee;
import org.mpp2024.Repository.Repo_Admin_Interface;
import org.mpp2024.Utility.Validation.IValidator;

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
    public Optional<Admin> findById(Long id) {
        return repoAdmin.findById(id);
    }

    @Override
    public Admin findByUsername(String username) {
        return repoAdmin.findByUsername(username);
    }
}
