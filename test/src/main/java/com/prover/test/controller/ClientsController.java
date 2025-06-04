package com.prover.test.controller;

import com.prover.test.model.Client;
import com.prover.test.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Component("clientsController")
@RequestMapping("/clients")
public class ClientsController {
    private final ClientService service;

    public ClientsController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Client> list(@RequestParam(required = false) String name) {
        return (name == null || name.isEmpty())
                ? service.listAll()
                : service.findByName(name);
    }

    @PostMapping
    public Client save(@Valid @RequestBody Client client) {
        return service.save(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return service.update(id, client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            service.saveAllFromCsv(file);
            return ResponseEntity.ok("Clients successfully imported!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error importing CSV: " + e.getMessage());
        }
    }

}
