package dev.vitoraleluia.ontime.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository repository;
    @InjectMocks
    private ClientServiceImpl service;

    @Test
    void createClient() {
        //given
        LocalDate dob = LocalDate.of(2001, 01, 01);
        ClientDTO clientDTO = new ClientDTO("Name",dob, "some@email.com","password");

        Client client = new Client();
        client.setName(clientDTO.name());
        client.setDateOfBirth(clientDTO.dateOfBirth());
        client.setEmail(clientDTO.email());
        client.setPassword(clientDTO.password());

        //when
        Mockito.when(repository.save(client)).thenReturn(client);

        Client savedClient = service.createClient(clientDTO);
        Assertions.assertThat(savedClient).isEqualTo(client);
    }

}