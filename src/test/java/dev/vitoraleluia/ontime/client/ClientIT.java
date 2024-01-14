package dev.vitoraleluia.ontime.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static dev.vitoraleluia.ontime.client.ClientTestConsts.*;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1-alpine");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createUser() {
        var clientRegistrationDTO = new ClientRegistrationDTO(NAME, EMAIL, PHONE_NUMBER);
        HttpEntity<ClientRegistrationDTO> request = new HttpEntity<>(clientRegistrationDTO);

        ResponseEntity<Client> response = restTemplate.exchange("/client", HttpMethod.POST, request, Client.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void getUser() {
        var clientRegistrationDTO = new ClientRegistrationDTO(NAME, EMAIL, PHONE_NUMBER);
        ResponseEntity<ClientDTO> response = restTemplate.exchange("/client/1", HttpMethod.GET, null, ClientDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
