package com.trotfl.trotflwebapp.web.controller;

import com.trotfl.trotflwebapp.domain.security.User;
import com.trotfl.trotflwebapp.service.UserService;
import com.trotfl.trotflwebapp.web.dto.UserDto;
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

    @GetMapping("/update/password")
    public String initUpdatePasswordForm(Model model) {
        UserDto userDto = UserDto.builder().build();
        model
                .addAttribute("form", "password")
                .addAttribute("user", userDto);
        return "account/update";
    }

    @PostMapping("/update/password")
    public String processUpdatePasswordForm(@Valid UserDto userDto, Authentication authentication, BindingResult result) {
        if (result.hasErrors()) {
            return "account/update/password";
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (userService.checkPasswordsMatch(user, userDto.getPassword())) {
                userService.updatePassword(user, userDto.getNewPassword());
            }
            return "redirect:/account/accountManagement";
        }
    }

    @GetMapping("/update/email")
    public String initUpdateEmailForm(Model model) {
        UserDto userDto = UserDto.builder().build();
        model
                .addAttribute("form", "email")
                .addAttribute("user", userDto);
        return "account/update";
    }

    @PostMapping("/update/email")
    public String processUpdateEmailForm(@Valid UserDto userDto, Authentication authentication, BindingResult result) {
        if (result.hasErrors()) {
            return "account/update/email";
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (userService.checkPasswordsMatch(user, userDto.getPassword())) {
                userService.updateEmail(user, userDto.getEmail());
            }
            return "redirect:/account/accountManagement";
        }
    }
    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

}
