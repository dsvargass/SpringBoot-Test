package com.prover.test.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CsvImportLogTest {

    @Test
    void shouldCreateLogAndAccessFields() {
        CsvImportLog log = new CsvImportLog();
        assertNotNull(log.getImportedAt());

        log.setId(10L);
        log.setFilePath("/caminho/arquivo.csv");
        LocalDateTime now = LocalDateTime.now();
        log.setImportedAt(now);

        assertEquals(10L, log.getId());
        assertEquals("/caminho/arquivo.csv", log.getFilePath());
        assertEquals(now, log.getImportedAt());

        String result = log.toString();
        assertTrue(result.contains("10"));
        assertTrue(result.contains("/caminho/arquivo.csv"));
        assertTrue(result.contains(now.toString()));
    }
}
