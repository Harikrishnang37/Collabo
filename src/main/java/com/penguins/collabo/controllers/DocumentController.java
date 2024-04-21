package com.penguins.collabo.controllers;


import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.models.Document;
import com.penguins.collabo.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DocumentController {
    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/docs")
    public String docsList(Model model) {
        List<DocumentDto> docs = documentService.findAllDocs();
        model.addAttribute("docs",docs);
        return "docs-list";
    }

    @GetMapping("docs/new")
    public String createDocForm(Model model) {
        Document doc = new Document();
         model.addAttribute("doc",doc);
         return "docs-create";
    }

    @PostMapping("/docs/new")
    public String saveDocument(@ModelAttribute("doc") Document document) {
        documentService.saveDocument(document);
        return "redirect:/docs";
    }

    @GetMapping("/docs/{docID}/edit")
    public String editDoc(@PathVariable("docID") int docID, Model model) {
        DocumentDto doc = documentService.findDocById(docID);
        model.addAttribute("doc",doc);
        return "docs-edit";

    }

    @PostMapping("/docs/{docId}/edit")
    public String updateDoc(@PathVariable("docId") String docId, @ModelAttribute("doc") DocumentDto doc){
        documentService.updateDoc(doc);
        return "redirect:/docs";
    }


}
