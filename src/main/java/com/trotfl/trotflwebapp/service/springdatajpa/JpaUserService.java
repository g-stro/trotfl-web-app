package com.trotfl.trotflwebapp.service.springdatajpa;

import com.trotfl.trotflwebapp.domain.security.User;
import com.trotfl.trotflwebapp.repository.security.UserRepository;
import com.trotfl.trotflwebapp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Greg Stroud
 */
@Service
public class JpaUserService implements UserService {

    private final UserRepository userRepository;

    public JpaUserService(UserRepository userRepository) {
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