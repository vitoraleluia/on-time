package dev.vitoraleluia.ontime.client;

import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public void createClient(@RequestBody @NonNull @Valid ClientDTO clientDTO) {
        service.createClient(clientDTO);
    }

}
