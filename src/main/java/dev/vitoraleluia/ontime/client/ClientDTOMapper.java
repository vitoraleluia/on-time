package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.appointment.AppointmentDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClientDTOMapper implements Function<Client, ClientDTO> {
    private final AppointmentDTOMapper appointmentMapper;

    public ClientDTOMapper(AppointmentDTOMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public ClientDTO apply(Client client) {
        return new ClientDTO(
                client.getName(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getAppointments().stream().map(appointmentMapper).toList()
        );
    }
}
