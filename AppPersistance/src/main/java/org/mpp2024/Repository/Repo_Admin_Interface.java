package org.mpp2024.Repository;


import org.mpp2024.Domain.Admin;

import java.util.Optional;

public interface Repo_Admin_Interface  {
   Optional<Admin> findById(Long id);
    Admin findByUsername(String username);
}
