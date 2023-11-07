package dev.vitoraleluia.ontime.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
@SpringBootTest
class ClientServiceTest {
    @Autowired
    private ClientService service;
    @MockBean
    private ClientRepository repository;

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