package dev.vitoraleluia.ontime.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        ClientDTO clientDTO = new ClientDTO("Example name", dob, "not-valid-email", "a password");
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
        ClientDTO clientDTO = new ClientDTO("name", dob, "test@test.com", "a password");

        doNothing().when(service).createClient(clientDTO);

        String jsonBody = this.mapper.writeValueAsString(clientDTO);
        MockHttpServletRequestBuilder request = post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}