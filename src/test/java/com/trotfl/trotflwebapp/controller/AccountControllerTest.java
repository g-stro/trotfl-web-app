package com.trotfl.trotflwebapp.controller;

import com.trotfl.trotflwebapp.domain.security.User;
import com.trotfl.trotflwebapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Greg Stroud
 */
@SpringBootTest
class AccountControllerTest {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "user@email.com";

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Mock
    UserService userService;

    @InjectMocks
    AccountController accountController;

    User user;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        mockMvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    @WithMockUser
    void findUser() throws Exception {
        when(userService.findByUsername(USERNAME)).thenReturn(user);

        mockMvc.perform(get("/account/accountManagement"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/accountManagement"))
                .andExpect(model().attributeExists("user"));

    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/account/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/create"))
                .andExpect(model().attributeExists("user"));

        verifyNoInteractions(userService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(userService.save(ArgumentMatchers.any())).thenReturn(user);

        mockMvc.perform(post("/account/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/account/login"))
                .andExpect(model().attributeExists("user"));

        verify(userService).save(ArgumentMatchers.any());
    }
}