package com.prover.test.service;

import com.prover.test.model.Client;
import com.prover.test.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.prover.test.model.CsvImportLog;
import com.prover.test.repository.CsvImportLogRepository;
import java.io.File;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final CsvImportLogRepository logRepository;

    public ClientService(ClientRepository repository, CsvImportLogRepository logRepository) {
        this.repository = repository;
        this.logRepository = logRepository;
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

    public List<Client> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Client save(Client client) {
        return repository.save(client);
    }

    public Client update(Long id, Client client) {
        client.setId(id);
        return repository.save(client);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void saveAllFromCsv(MultipartFile file) throws Exception {
        String uploadDir = new File(System.getProperty("user.dir")).getParent();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        String originalName = file.getOriginalFilename();
        String extension = originalName != null && originalName.contains(".") ? originalName.substring(originalName.lastIndexOf(".")) : "";
        String baseName = originalName != null ? originalName.replace(extension, "") : "upload";
        String newFileName = baseName + "_" + timestamp + extension;

        File savedFile = new File(uploadDir, newFileName);
        file.transferTo(savedFile);

        // Parse CSV from the saved file
        Reader reader = new InputStreamReader(new FileInputStream(savedFile));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord record : csvParser) {
            String email = record.get("email");

            if (!repository.existsByEmail(email)) {
                Client client = new Client();
                client.setName(record.get("name"));
                client.setEmail(record.get("email"));
                client.setPhone(record.get("phone"));
                repository.save(client);
            } else {
                System.out.println("⚠️ Email already exists, skipping: " + email);
            }
        }

        // Save log
        CsvImportLog log = new CsvImportLog();
        log.setFilePath(savedFile.getAbsolutePath());
        logRepository.save(log);
    }
}
