package com.prover.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prover.test.model.Client;
import com.prover.test.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    void testList() throws Exception {
        when(clientService.listAll()).thenReturn(List.of(new Client()));
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchByName() throws Exception {
        when(clientService.findByName("joao")).thenReturn(List.of(new Client()));
        mockMvc.perform(get("/clients?name=joao"))
                .andExpect(status().isOk());
    }

    @Test
    void testSave() throws Exception {
        Client c = new Client();
        c.setName("João");
        c.setEmail("joao@email.com");
        String json = mapper.writeValueAsString(c);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Client c = new Client();
        c.setName("João");
        c.setEmail("joao@email.com");
        String json = mapper.writeValueAsString(c);

        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUploadCsv() throws Exception {
        String csv = "name,email,phone\nDiego,diego@email.com,1234";
        MockMultipartFile file = new MockMultipartFile("file", "file.csv", "text/csv", csv.getBytes());

        mockMvc.perform(multipart("/clients/upload").file(file))
                .andExpect(status().isOk());
    }

    @Test
    void testListWithEmptyNameReturnsAllClients() throws Exception {
        when(clientService.listAll()).thenReturn(List.of(new Client()));
        mockMvc.perform(get("/clients?name="))
                .andExpect(status().isOk());
    }
}
