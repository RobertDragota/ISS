package org.mpp2024.Repository;


import org.mpp2024.Domain.Programmer;

public interface Repo_Programmer_Interface extends Repository_Interface<Long, Programmer>{
    Programmer findByUsername(String username);
}
