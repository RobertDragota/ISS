package org.mpp2024.Repository;


import org.mpp2024.Domain.Validator;

public interface Repo_Validator_Interface  extends Repository_Interface<Long, Validator>{
    Validator findByUsername(String username);
}
