package br.com.vidaldev.mystocks.controllers;

import br.com.vidaldev.mystocks.infra.security.dtos.AuthenticationDto;
import br.com.vidaldev.mystocks.infra.security.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto){
        return ResponseEntity.ok(authenticationService.authenticateUser(dto));
    }

}
