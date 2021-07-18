package com.trotfl.trotflwebapp.web.controller;

import com.trotfl.trotflwebapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Greg Stroud
 */
@Controller
public class IndexController {

    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }
}
