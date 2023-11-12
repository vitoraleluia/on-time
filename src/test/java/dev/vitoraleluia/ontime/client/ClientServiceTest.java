package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private ClientDTOMapper mapper;
    @InjectMocks
    private ClientServiceImpl service;

    @Test
    void createClient() {
        //given
        ClientRegistrationDTO clientDTO = new ClientRegistrationDTO("Name",getValidDob(), "some@email.com");

        Client client = new Client();
        client.setName(clientDTO.name());
        client.setDateOfBirth(clientDTO.dateOfBirth());
        client.setEmail(clientDTO.email());

        //when
        when(repository.save(client)).thenReturn(client);

        Client savedClient = service.createClient(clientDTO);
        assertThat(savedClient).isEqualTo(client);
    }

    @Test
    void getClientFromId(){
        ClientDTO expectedDto = new ClientDTO("name", getValidDob(), "some@email.com", Collections.emptyList());
        Client clientFromRepo = new Client(expectedDto.name(), expectedDto.dateOfBirth(), expectedDto.email(), Collections.emptyList());

        when(repository.findById(1L)).thenReturn(Optional.of(clientFromRepo));
        when(mapper.apply(clientFromRepo)).thenReturn(expectedDto);


        ClientDTO clientFromService = service.getClientWithId(1L);
        assertThat(clientFromService).isEqualTo(expectedDto);
    }

    @Test
    void getClientFromIdNotFound(){
        ClientDTO expectedDto = new ClientDTO("name", getValidDob(), "some@email.com", Collections.emptyList());
        Client clientFromRepo = new Client(expectedDto.name(), expectedDto.dateOfBirth(), expectedDto.email(), Collections.emptyList());

        when(repository.findById(1L)).thenReturn(Optional.empty());

        try{
            ClientDTO clientFromService = service.getClientWithId(1L);
            fail("It should have thrown an exception");
        }catch (ResourceNotFoundException ex){
            assertThat(ex.getMessage()).isEqualTo("Couldn't find Client with id [1]");
        }
    }

    LocalDate getValidDob(){
        return LocalDate.of(2001, 01, 01);
    }

}