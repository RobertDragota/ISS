package org.iss2024.bug_detection.Services;

import org.iss2024.bug_detection.Domain.Employee;
import org.iss2024.bug_detection.Domain.Entity;
import org.iss2024.bug_detection.Domain.Validator;
import org.iss2024.bug_detection.Repository.RepoValidator;
import org.iss2024.bug_detection.Repository.Repo_Validator_Interface;
import org.iss2024.bug_detection.Utility.Validation.IValidator;

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
        public Optional<Validator> findById (String s){
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
}
