package com.trotfl.trotflwebapp.repository;

import com.trotfl.trotflwebapp.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Greg Stroud
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
