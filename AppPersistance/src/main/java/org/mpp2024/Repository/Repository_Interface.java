package org.mpp2024.Repository;



import org.mpp2024.Domain.Entity;

import java.util.Optional;

public interface Repository_Interface <ID,E extends Entity<ID>>{
     Optional<E> save(E entity);

     Optional<E> delete(E entity);

     Optional<E> update(E entity);

     Optional<E> findById(ID id) ;

     Iterable<E> findAll() ;

     Long size();
}
