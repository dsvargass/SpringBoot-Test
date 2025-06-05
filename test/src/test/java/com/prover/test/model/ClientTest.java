package com.prover.test.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void shouldSetAndGetAllFieldsCorrectly() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Diego");
        client.setEmail("diego@email.com");
        client.setPhone("12345");

        assertAll(
                () -> assertEquals(1L, client.getId()),
                () -> assertEquals("Diego", client.getName()),
                () -> assertEquals("diego@email.com", client.getEmail()),
                () -> assertEquals("12345", client.getPhone())
        );
    }
}
