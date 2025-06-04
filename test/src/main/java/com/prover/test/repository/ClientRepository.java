package com.prover.test.repository;

import com.prover.test.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long>{
    List<Client> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
}
