package com.penguins.collabo.service;

import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.models.Document;

import java.util.List;

public interface DocumentService {
    List<DocumentDto> findAllDocs();

    Document saveDocument(Document document);
    DocumentDto findDocById(int id);

    void updateDoc(DocumentDto doc);
}
