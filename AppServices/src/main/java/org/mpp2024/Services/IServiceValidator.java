package org.mpp2024.Services;


import org.mpp2024.Domain.Validator;

public interface IServiceValidator extends IService<Long, Validator>{
    Validator findByUsername(String username);
}
