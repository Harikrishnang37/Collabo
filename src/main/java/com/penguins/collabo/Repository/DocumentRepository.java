package com.penguins.collabo.Repository;

import com.penguins.collabo.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findById(int id);
}
