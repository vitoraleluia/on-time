package dev.vitoraleluia.ontime.client;

import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }


    public void createClient(ClientDTO clientDTO) {
        Client client = new Client(clientDTO);
        repository.save(client);
    }
}
