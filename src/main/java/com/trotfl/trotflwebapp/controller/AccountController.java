package com.trotfl.trotflwebapp.controller;

import com.trotfl.trotflwebapp.domain.security.User;
import com.trotfl.trotflwebapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
/**
 * @author Greg Stroud
 */
@RequestMapping("/account")
@Controller
public class AccountController {

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/accountManagement")
    public String findUser(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "account/accountManagement";
    }

    @GetMapping("/create")
    public String initCreationForm(Model model) {
        model.addAttribute(User.builder().build());
        return "account/create";
    }

    @PostMapping("/create")
    public String processCreationForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "account/create";
        } else {
            userService.save(user);
            return "redirect:/account/login";
        }
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

}
