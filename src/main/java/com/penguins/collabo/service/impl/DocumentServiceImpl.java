package com.penguins.collabo.service.impl;

import com.penguins.collabo.Repository.DocumentRepository;
import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.models.Document;
import com.penguins.collabo.service.DocumentService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<DocumentDto> findAllDocs(){
        List<Document> docs = documentRepository.findAll();
        return docs.stream().map((document) -> mapToDocumentDto(document)).collect(Collectors.toList());
    }

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public DocumentDto findDocById(int docID){
        Document doc = documentRepository.findById(docID).get();
        return mapToDocumentDto(doc);
    }

    @Override
    public void updateDoc(DocumentDto dto) {
        Document doc = mapToDocument(dto);
        documentRepository.save(doc);
    }

    private Document mapToDocument(DocumentDto dto) {
        Document doc = Document.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .userid(dto.getUserid())
                .build();

        return doc;
    }

    private DocumentDto mapToDocumentDto(Document document) {
        DocumentDto docDto = DocumentDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .content((document.getContent()))
                .userid(document.getUserid())
                .build();

        return docDto;
    }
}
