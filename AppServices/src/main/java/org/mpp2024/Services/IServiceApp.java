package org.mpp2024.Services;


import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Employee;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;
import org.mpp2024.Services.Observer.AppObserverInterface;

public interface IServiceApp {

    Employee login(Employee employee, AppObserverInterface observer);
    Employee register(String username, String password,String role);
    Employee getEmployee(String username, String password);
    void logout(Employee employee);
    void notify(Iterable<Programmer> programmers, Iterable<Validator> validators, Iterable<Bug> bugs);
}
