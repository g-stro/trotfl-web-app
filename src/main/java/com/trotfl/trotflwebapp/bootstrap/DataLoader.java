package com.trotfl.trotflwebapp.bootstrap;

import com.trotfl.trotflwebapp.model.User;
import com.trotfl.trotflwebapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Greg Stroud
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        User user = new User();
        user.setUsername("g-stro");
        User savedUser = userService.save(user);

        System.out.println(savedUser.getUsername());
    }
}