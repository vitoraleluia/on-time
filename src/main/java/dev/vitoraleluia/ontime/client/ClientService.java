package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.exceptions.ResourceNotFoundException;

public interface ClientService {

    Client createClient(ClientRegistrationDTO clientDTO);

    ClientDTO getClientWithId(Long id);
}
