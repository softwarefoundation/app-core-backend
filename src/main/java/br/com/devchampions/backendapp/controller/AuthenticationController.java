package br.com.devchampions.backendapp.controller;

import br.com.devchampions.backendapp.security.login.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("authenticate")
    public String authenticate(Authentication authentication){
        return this.authenticationService.authenticate(authentication);
    }

}
