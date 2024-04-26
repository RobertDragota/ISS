package org.iss2024.bug_detection.Services;

import org.iss2024.bug_detection.Domain.Employee;

public interface IServiceApp {

    Employee login(String username, String password);
    Employee register(String username, String password,String role);
}
