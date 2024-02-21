package br.com.vidaldev.mystocks.infra.security.services;

import br.com.vidaldev.mystocks.infra.security.dtos.AuthenticationDto;
import br.com.vidaldev.mystocks.infra.security.dtos.JwtTokenDto;
import br.com.vidaldev.mystocks.infra.security.entities.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    public JwtTokenDto authenticateUser(AuthenticationDto dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = authManager.authenticate(token);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtTokenDto(jwtTokenService.getToken(userDetails));
    }
}
