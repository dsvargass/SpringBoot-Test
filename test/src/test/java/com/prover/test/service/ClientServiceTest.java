package com.prover.test.service;

import com.prover.test.model.Client;
import com.prover.test.repository.ClientRepository;
import com.prover.test.repository.CsvImportLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientRepository clientRepo;
    private CsvImportLogRepository logRepo;
    private ClientService service;

    @BeforeEach
    void setUp() {
        clientRepo = mock(ClientRepository.class);
        logRepo = mock(CsvImportLogRepository.class);
        service = new ClientService(clientRepo, logRepo);
    }

    @Test
    void testListAll() {
        when(clientRepo.findAll()).thenReturn(Collections.singletonList(new Client()));
        List<Client> result = service.listAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindByName() {
        when(clientRepo.findByNameContainingIgnoreCase("joao")).thenReturn(List.of(new Client()));
        List<Client> result = service.findByName("joao");
        assertFalse(result.isEmpty());
    }

    @Test
    void testSave() {
        Client c = new Client();
        when(clientRepo.save(c)).thenReturn(c);
        Client saved = service.save(c);
        assertNotNull(saved);
    }

    @Test
    void testUpdate() {
        Client c = new Client();
        c.setId(1L);
        c.setName("Nome Original");
        c.setEmail("original@email.com");
        c.setPhone("1111");

        Client updatedData = new Client();
        updatedData.setName("Novo Nome");
        updatedData.setEmail("email@teste.com");
        updatedData.setPhone("9999");

        when(clientRepo.findById(1L)).thenReturn(Optional.of(c));
        when(clientRepo.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Client updated = service.update(1L, updatedData);

        assertEquals("Novo Nome", updated.getName());
        assertEquals("email@teste.com", updated.getEmail());
        assertEquals("9999", updated.getPhone());
    }

    @Test
    void testDelete() {
        service.delete(1L);
        verify(clientRepo, times(1)).deleteById(1L);
    }

    @Test
    void testSaveAllFromCsv() throws Exception {
        String csvContent = "name,email,phone\nJoao,joao@email.com,12345";
        MockMultipartFile file = new MockMultipartFile("file", "clients.csv", "text/csv", csvContent.getBytes());
        when(clientRepo.existsByEmail("joao@email.com")).thenReturn(false);

        service.saveAllFromCsv(file);

        verify(clientRepo, times(1)).save(any(Client.class));
        verify(logRepo, times(1)).save(any());
    }
}
