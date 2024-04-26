package org.iss2024.bug_detection.Repository;

import org.iss2024.bug_detection.Domain.Entity;

import java.util.Optional;

public interface Repository_Interface <ID,E extends Entity<ID>>{
     Optional<E> save(E entity);

     Optional<E> delete(E entity);

     Optional<E> update(E entity);

     Optional<E> findById(ID id) ;

     Iterable<E> findAll() ;

     Long size();
}
