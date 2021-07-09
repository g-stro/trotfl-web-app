package com.trotfl.trotflwebapp.service;

import com.trotfl.trotflwebapp.domain.security.User;

/**
 * @author Greg Stroud
 */
public interface UserService extends CrudService<User, Long> {

    User findByUsername(String username);
}
