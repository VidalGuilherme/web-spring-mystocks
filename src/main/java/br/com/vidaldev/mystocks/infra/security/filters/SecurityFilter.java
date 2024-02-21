package br.com.vidaldev.mystocks.infra.security.filters;

import br.com.vidaldev.mystocks.domain.users.repositories.UserRepository;
import br.com.vidaldev.mystocks.infra.security.SecurityConfiguration;
import br.com.vidaldev.mystocks.infra.security.entities.UserDetailsImpl;
import br.com.vidaldev.mystocks.infra.security.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(checkIfEndPointIsNotPublic(request)){
            var token = getToken(request);
            if(token == null){
                throw new RuntimeException("Token not sent.");
            }

            var subject = tokenService.getSubject(token);
            var user = userRepository.findByLogin(subject).get();
            var userDetails = new UserDetailsImpl(user);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean checkIfEndPointIsNotPublic(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.AUTHENTICATION_NOT_REQUIRED).contains(requestUri);
    }

    private String getToken(HttpServletRequest request) {
        var authToken = request.getHeader("Authorization");
        if(authToken == null){
            return null;
        }

        return authToken.replace("Bearer ","");
    }
}
