package org.iss2024.bug_detection.Services;

import org.iss2024.bug_detection.Domain.Admin;

import java.util.Optional;

public interface IServiceAdmin {

    Optional<Admin> findById(String id);
}
