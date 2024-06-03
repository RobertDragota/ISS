package org.mpp2024.Services.Observer;

import org.mpp2024.Domain.Bug;
import org.mpp2024.Domain.Programmer;
import org.mpp2024.Domain.Validator;

public interface AppObserverInterface {
    void update(Iterable<Programmer> programmers, Iterable<Validator> validators, Iterable<Bug> bugs);

}
