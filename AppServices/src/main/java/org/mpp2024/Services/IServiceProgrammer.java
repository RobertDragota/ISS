package org.mpp2024.Services;


import org.mpp2024.Domain.Programmer;

public interface IServiceProgrammer extends IService<Long, Programmer> {
    Programmer findByUsername(String username);

}
