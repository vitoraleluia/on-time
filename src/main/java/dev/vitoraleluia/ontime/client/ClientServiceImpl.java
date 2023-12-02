package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientDTOMapper mapper;

    public ClientServiceImpl(ClientRepository repository, ClientDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public Client createClient(ClientRegistrationDTO clientDTO) {
        Client client = new Client(clientDTO);
        return repository.save(client);
    }

    @Override
    public ClientDTO getClientWithId(Long id) {
        return repository.findById(id)
                .map(mapper)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find Client with id [%s]".formatted(id)));
    }

    @Override
    public boolean deleteClient(Long id) {
        repository.deleteById(id);
        return true;
    }
}
