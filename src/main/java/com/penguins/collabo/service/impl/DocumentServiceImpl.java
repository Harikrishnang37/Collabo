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

    public boolean hasViewAccess(int docID, int userID){
        Document doc = documentRepository.findById(docID).get();
        return doc.getViewAccess().contains(userID) || doc.getUserid() == userID;
    }

    @Override
    public void addViewAccess(int docID, int userID) {
        Document doc = documentRepository.findById(docID).get();
        if(!doc.getViewAccess().contains(userID)){
            doc.getViewAccess().add(userID);
        }
        documentRepository.save(doc);

    }

    @Override
    public void removeViewAccess(int docID, int userID) {
        Document doc = documentRepository.findById(docID).get();
        if(doc.getViewAccess().contains(userID)){
            doc.getViewAccess().remove(doc.getViewAccess().indexOf(userID));
        }
        documentRepository.save(doc);
    }

    @Override
    public boolean hasEditAccess(int docID, int userID) {
        Document doc = documentRepository.findById(docID).get();
        return doc.getEditAccess().contains(userID) || doc.getUserid() == userID;
    }

    @Override
    public void addEditAccess(int docID, int userID) {
        Document doc = documentRepository.findById(docID).get();
        if(!doc.getEditAccess().contains(userID)){
            doc.getEditAccess().add(userID);
        }
        documentRepository.save(doc);
    }

    @Override
    public void removeEditAccess(int docID, int userID) {
        Document doc = documentRepository.findById(docID).get();
        if(doc.getEditAccess().contains(userID)){
            doc.getEditAccess().remove(doc.getEditAccess().indexOf(userID));
        }
    }

    @Override
    public void deleteDocument(int docID) {
        documentRepository.deleteById(docID);
    }

    @Override
    public void updateDoc(DocumentDto dto) {
        Document doc = mapToDocument(dto);
        documentRepository.save(doc);
    }

    public String getUsernameFromUserid(int userid){
        Document doc = documentRepository.findById(userid).get();
        return doc.getUsername();
    }



    private Document mapToDocument(DocumentDto dto) {
        Document doc = Document.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .userid(dto.getUserid())
                .username(dto.getUsername())
                .EditAccess(dto.getEditAccess())
                .ViewAccess(dto.getViewAccess())
                .build();

        return doc;
    }

    private DocumentDto mapToDocumentDto(Document document) {
        DocumentDto docDto = DocumentDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .content((document.getContent()))
                .userid(document.getUserid())
                .username(document.getUsername())
                .EditAccess(document.getEditAccess())
                .ViewAccess(document.getViewAccess())
                .build();

        return docDto;
    }
}
