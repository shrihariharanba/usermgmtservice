package org.usermgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.usermgmt.service.LoginService;

/**
 * Login operations
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping()
    public void login(@RequestParam String emailAddress, @RequestParam String password) {
        loginService.validate(emailAddress, password);
    }
}
