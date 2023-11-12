package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.appointment.AppointmentDTO;

import java.time.LocalDate;
import java.util.List;

public record ClientDTO(
        String name,
        LocalDate dateOfBirth,
        String email,
        List<AppointmentDTO> appointments
) {
}
