package com.trotfl.trotflwebapp.repository.security;

import com.trotfl.trotflwebapp.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Greg Stroud
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
