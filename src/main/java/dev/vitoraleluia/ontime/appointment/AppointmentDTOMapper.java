package dev.vitoraleluia.ontime.appointment;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AppointmentDTOMapper implements Function<Appointment, AppointmentDTO> {
    @Override
    public AppointmentDTO apply(Appointment appointment) {
        return new AppointmentDTO(); // TODO: 11/12/23 complete this latter 
    }
}
