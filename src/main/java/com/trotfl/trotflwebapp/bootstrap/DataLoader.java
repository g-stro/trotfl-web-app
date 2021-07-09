package com.trotfl.trotflwebapp.bootstrap;

import com.trotfl.trotflwebapp.domain.security.Authority;
import com.trotfl.trotflwebapp.domain.security.User;
import com.trotfl.trotflwebapp.repository.security.AuthorityRepository;
import com.trotfl.trotflwebapp.repository.security.UserRepository;
import com.trotfl.trotflwebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Greg Stroud
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserService userService, UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority developer = authorityRepository.save(Authority.builder().role("DEVELOPER").build());
        Authority user = authorityRepository.save(Authority.builder().role("USER").build());

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("pass"))
                .email("admin@email.com")
                .authority(admin)
                .build());

        userRepository.save(User.builder()
                .username("dev")
                .password(passwordEncoder.encode("pass"))
                .authority(developer)
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("pass"))
                .authority(user)
                .build());

        log.debug("Users Loaded: " + userRepository.count());

    }
}