package com.penguins.collabo.service;

import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.models.Document;

import java.util.List;

public interface DocumentService {
    List<DocumentDto> findAllDocs();

    Document saveDocument(Document document);
    DocumentDto findDocById(int id);

    void updateDoc(DocumentDto doc);

    String getUsernameFromUserid(int userid);

    boolean hasViewAccess(int docID, int userID);
    void addViewAccess(int docID, int userID);
    void removeViewAccess(int docID, int userID);

    boolean hasEditAccess(int docID, int userID);
    void addEditAccess(int docID, int userID);
    void removeEditAccess(int docID, int userID);

    void deleteDocument(int docID);
}
