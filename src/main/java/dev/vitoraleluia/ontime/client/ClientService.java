package dev.vitoraleluia.ontime.client;

public interface ClientService {

    Client createClient(ClientRegistrationDTO clientDTO);

    ClientDTO getClientWithId(Long id);

    boolean deleteClient(Long id);

    ClientDTO updateClient(Long id, ClientRegistrationDTO clientDTO);
}
