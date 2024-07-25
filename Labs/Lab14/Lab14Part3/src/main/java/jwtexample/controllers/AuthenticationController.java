package jwtexample.controllers;

import jwtexample.dto.JwtAuthenticationResponse;
import jwtexample.dto.SignInRequest;
import jwtexample.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwtexample.services.AuthenticationService;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private  AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}