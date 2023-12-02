package dev.vitoraleluia.ontime.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ClientRegistrationDTO(
        @NotBlank(message = "Name should be provided")
        String name,

        @NotBlank(message = "Email should be provided")
        @Email(message = "The email is invalid")
        String email,

        @NotBlank(message = "Phone number should be provided")
        @Pattern(regexp = "^9\\d{8}$", message = "The phone number is invalid")
        String phoneNumber
) {
}
