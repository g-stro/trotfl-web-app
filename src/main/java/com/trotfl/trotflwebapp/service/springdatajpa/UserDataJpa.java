package com.trotfl.trotflwebapp.service.springdatajpa;

import com.trotfl.trotflwebapp.model.User;
import com.trotfl.trotflwebapp.repository.UserRepository;
import com.trotfl.trotflwebapp.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Greg Stroud
 */
@Service
@Profile("springdatajpa")
public class UserDataJpa implements UserService {

    private final UserRepository userRepository;

    public UserDataJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}