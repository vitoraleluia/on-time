package dev.vitoraleluia.ontime.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Basic CRUD controller to manage clients
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody @NonNull @Valid ClientRegistrationDTO clientDTO) {
        service.createClient(clientDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientWithId(@PathVariable("id") @NonNull @Positive Long id) {
        return service.getClientWithId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteClient(@PathVariable("id") @NonNull @Positive Long id){
        return service.deleteClient(id);
    }

}
