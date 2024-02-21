package br.com.vidaldev.mystocks.infra.security.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
