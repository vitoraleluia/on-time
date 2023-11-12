package dev.vitoraleluia.ontime.client;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientDTO(
        @NotBlank(message = "Name should be provided")
        String name,
        @Past(message = "Date of birth should be in the past")
        LocalDate dateOfBirth,
        @NotBlank(message = "Email should be provided")
        @Email(message = "The email is invalid")
        String email
) {
}
