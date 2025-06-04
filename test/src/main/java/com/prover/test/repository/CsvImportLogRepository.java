package com.prover.test.repository;

import com.prover.test.model.CsvImportLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvImportLogRepository extends JpaRepository<CsvImportLog, Long> {
}
