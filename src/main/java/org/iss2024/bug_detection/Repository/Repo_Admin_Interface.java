package org.iss2024.bug_detection.Repository;

import org.iss2024.bug_detection.Domain.Admin;

import java.util.Optional;

public interface Repo_Admin_Interface {
   Optional <Admin >findById(String id);
}
