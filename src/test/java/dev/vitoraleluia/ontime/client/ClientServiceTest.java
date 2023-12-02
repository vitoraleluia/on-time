package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    public static final String EMAIL = "some@email.com";
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientDTOMapper mapper;
    @InjectMocks
    private ClientServiceImpl service;

    @Test
    void createClient() {
        //given
        ClientRegistrationDTO clientDTO = new ClientRegistrationDTO("Name", "some@email.com",ClientTestConsts.PHONE_NUMBER);

        Client client = new Client();
        client.setName(clientDTO.name());
        client.setPhoneNumber(ClientTestConsts.PHONE_NUMBER);
        client.setEmail(clientDTO.email());

        //when
        when(repository.save(client)).thenReturn(client);

        Client savedClient = service.createClient(clientDTO);
        assertThat(savedClient).isEqualTo(client);
    }

    @Test
    void getClientFromId() {
        ClientDTO expectedDto = new ClientDTO("name", ClientTestConsts.PHONE_NUMBER, EMAIL, Collections.emptyList());
        Client clientFromRepo = new Client(expectedDto.name(), expectedDto.phoneNumber(), expectedDto.email(), Collections.emptyList());

        when(repository.findById(1L)).thenReturn(Optional.of(clientFromRepo));
        when(mapper.apply(clientFromRepo)).thenReturn(expectedDto);


        ClientDTO clientFromService = service.getClientWithId(1L);
        assertThat(clientFromService).isEqualTo(expectedDto);
    }

    @Test
    void getClientFromIdNotFound() {
        ClientDTO expectedDto = new ClientDTO("name", ClientTestConsts.PHONE_NUMBER, "some@email.com", Collections.emptyList());
        Client clientFromRepo = new Client(expectedDto.name(), expectedDto.email(),expectedDto.phoneNumber(), Collections.emptyList());

        when(repository.findById(1L)).thenReturn(Optional.empty());

        try {
            ClientDTO clientFromService = service.getClientWithId(1L);
            fail("It should have thrown an exception");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Couldn't find Client with id [1]");
        }
    }
}