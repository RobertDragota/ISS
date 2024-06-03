package org.mpp2024.Services;


import org.mpp2024.Domain.Admin;

import java.util.Optional;

public interface IServiceAdmin {

    Optional<Admin> findById(Long id);
    Admin findByUsername(String username);
}
