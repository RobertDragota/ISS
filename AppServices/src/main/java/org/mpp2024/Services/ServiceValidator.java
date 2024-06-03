package org.mpp2024.Services;


import org.mpp2024.Domain.Employee;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Repository.Repo_Validator_Interface;
import org.mpp2024.Utility.Validation.IValidator;

import java.util.Optional;

public class ServiceValidator implements IServiceValidator {

    private final Repo_Validator_Interface repoValidator;
    private IValidator<Employee> entityValidator;

    public ServiceValidator(Repo_Validator_Interface repoValidator, IValidator<Employee> entityValidator) {
        this.repoValidator = repoValidator;
        this.entityValidator = entityValidator;
    }
        /**
         * @param entity
         * @return
         */
        @Override
        public Optional<Validator> save (Validator entity){
            try {
                entityValidator.validate(entity);
                return repoValidator.save(entity);
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
        public Optional<Validator> delete (Validator entity){
            try {
                entityValidator.validate(entity);
                return repoValidator.delete(entity);
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
        public Optional<Validator> update (Validator entity){
            try {
                entityValidator.validate(entity);
                return repoValidator.update(entity);
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
        public Optional<Validator> findById (Long s){
            return repoValidator.findById(s);
        }

        /**
         * @return
         */
        @Override
        public Iterable<Validator> findAll () {
            return repoValidator.findAll();
        }

    /**
     * @return 
     */
    @Override
    public Long size() {
        return repoValidator.size();
    }

    @Override
    public Validator findByUsername(String username) {
        return repoValidator.findByUsername(username);
    }
}
