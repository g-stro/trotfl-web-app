package com.trotfl.trotflwebapp.web.controller;

import com.trotfl.trotflwebapp.domain.security.User;
import com.trotfl.trotflwebapp.service.UserService;
import com.trotfl.trotflwebapp.web.dto.UserEmailDto;
import com.trotfl.trotflwebapp.web.dto.UserPasswordDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        UserPasswordDto userPasswordDto = UserPasswordDto.builder().build();
        model
                .addAttribute("form", "password")
                .addAttribute("user", userPasswordDto);
        return "account/update";
    }

    @PostMapping("/update/password")
    public String processUpdatePasswordForm(@Valid @ModelAttribute("user") UserPasswordDto userPasswordDto,
                                            BindingResult result, Authentication authentication, Model model) {
        if (result.hasErrors()) {
            // Maintain the form type
            model.addAttribute("form", "password");
            return "account/update";
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (userService.checkPasswordsMatch(user, userPasswordDto.getPassword())) {
                userService.updatePassword(user, userPasswordDto.getNewPassword());
            }
            return "redirect:/account/accountManagement";
        }
    }

    @GetMapping("/update/email")
    public String initUpdateEmailForm(Model model) {
        UserEmailDto userEmailDto = UserEmailDto.builder().build();
        model
                .addAttribute("form", "email")
                .addAttribute("user", userEmailDto);
        return "account/update";
    }

    @PostMapping("/update/email")
    public String processUpdateEmailForm(@Valid @ModelAttribute("user") UserEmailDto userEmailDto,
                                         BindingResult result, Authentication authentication, Model model) {
        if (result.hasErrors()) {
            // Maintain the form type
            model.addAttribute("form", "email");
            return "account/update";
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (userService.checkPasswordsMatch(user, userEmailDto.getPassword())) {
                userService.updateEmail(user, userEmailDto.getEmail());
            }
            return "redirect:/account/accountManagement";
        }
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

}
