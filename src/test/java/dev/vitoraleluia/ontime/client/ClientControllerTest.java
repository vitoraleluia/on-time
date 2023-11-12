package dev.vitoraleluia.ontime.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.vitoraleluia.ontime.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService service;
    private static ObjectMapper mapper;

    @BeforeAll
    public static void init() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        //Needed to format correctly the date (ex: 2000-01-01)
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUserWithInvalidData() throws Exception {
        LocalDate dob = LocalDate.of(2001, 01, 02);
        ClientRegistrationDTO clientDTO = new ClientRegistrationDTO("Example name", dob, "not-valid-email");
        String jsonBody = this.mapper.writeValueAsString(clientDTO);

        MockHttpServletRequestBuilder request = post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUserIsSuccessfully() throws Exception {
        LocalDate dob = LocalDate.of(2001, 01, 02);
        ClientRegistrationDTO clientDTO = new ClientRegistrationDTO("name", dob, "test@test.com");

        doNothing().when(service).createClient(clientDTO);

        String jsonBody = this.mapper.writeValueAsString(clientDTO);
        MockHttpServletRequestBuilder request = post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void getClientWithIdOk() throws Exception {
        LocalDate dob = LocalDate.of(1999,06,26);
        ClientDTO expectedClient = new ClientDTO("Name", dob, "example@email.com", Collections.emptyList());

        when(service.getClientWithId(1L)).thenReturn(expectedClient);

        String expectedJsonBody = this.mapper.writeValueAsString(expectedClient);
        MockHttpServletRequestBuilder request = get("/client/1").contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJsonBody));
    }

    @Test
    void getClientWithIdNotFound() throws Exception {
        LocalDate dob = LocalDate.of(1999,06,26);
        ClientDTO expectedClient = new ClientDTO("Name", dob, "example@email.com", Collections.emptyList());

        when(service.getClientWithId(1L)).thenThrow(new ResourceNotFoundException("Client not found with id"));

        String expectedJsonBody = this.mapper.writeValueAsString(expectedClient);
        MockHttpServletRequestBuilder request = get("/client/1").contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
}