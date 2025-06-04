package com.prover.test.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CsvImportLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime importedAt;

    public CsvImportLog() {
        this.importedAt = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getImportedAt() {
        return importedAt;
    }

    public void setImportedAt(LocalDateTime importedAt) {
        this.importedAt = importedAt;
    }

    @Override
    public String toString() {
        return "CsvImportLog{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", importedAt=" + importedAt +
                '}';
    }
}