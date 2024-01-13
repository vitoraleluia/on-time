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
        var client = new Client(clientDTO);
        return repository.save(client);
    }

    @Override
    public ClientDTO getClientWithId(Long id) {
        Client client = tryToFindClientById(id);
        return mapper.apply(client);
    }

    private Client tryToFindClientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find Client with id [%s]".formatted(id)));
    }

    @Override
    public boolean deleteClient(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public ClientDTO updateClient(Long id, ClientRegistrationDTO clientDTO) {
        Client client = tryToFindClientById(id);
        
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPhoneNumber(clientDTO.phoneNumber());

        client = repository.save(client);

        return mapper.apply(client);
    }
}
