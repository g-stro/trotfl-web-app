package com.trotfl.trotflwebapp.repository.security;

import com.trotfl.trotflwebapp.domain.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Greg Stroud
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
