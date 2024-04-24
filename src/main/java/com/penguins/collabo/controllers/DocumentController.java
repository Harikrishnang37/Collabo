package com.penguins.collabo.controllers;


import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.models.AccessRequest;
import com.penguins.collabo.models.Document;
import com.penguins.collabo.models.UserEntity;
import com.penguins.collabo.service.AccessRequestService;
import com.penguins.collabo.service.DocumentService;
import com.penguins.collabo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DocumentController {
    private  DocumentService documentService;
    private  UserService userService;
    private  AccessRequestService accessRequestService;

    public DocumentController(DocumentService documentService, UserService userService, AccessRequestService accessRequestService) {
        this.documentService = documentService;
        this.userService = userService;
        this.accessRequestService = accessRequestService;
    }

    @GetMapping("/{userid}/docs")
    public String docsList(@PathVariable("userid") int userid, Model model) {
        List<DocumentDto> docs = documentService.findAllDocs();
        List<AccessRequest> accessRequests = accessRequestService.getAccessRequestsByReceiverUserId(userid);
        model.addAttribute("docs",docs);
        model.addAttribute("accessRequests",accessRequests);
        model.addAttribute("accRequestsLength",accessRequests.size());
        model.addAttribute("userid",userid);
        model.addAttribute("username", userService.findUserById(userid).getUsername());
        return "docs-list";
    }

    @GetMapping("{userid}/docs/new")
    public String createDocForm(@PathVariable("userid") int userid, Model model) {
        Document doc = new Document();
        doc.setUserid(userid);
        doc.setUsername(userService.findUserById(userid).getUsername());
        model.addAttribute("doc", doc);
        model.addAttribute("userid",userid);

         return "docs-create";
    }

    @PostMapping("/{userid}/docs/new")
    public String saveDocument(@PathVariable("userid") int userid ,@ModelAttribute("doc") Document document, Model model) {
        document.setUsername(userService.findUserById(userid).getUsername());
        documentService.saveDocument(document);
        return "redirect:/"+userid+"/docs";
    }

    @GetMapping("/deleteDoc/{docId}")
    public String deleteDoc(@PathVariable("docId") int docId, Model model) {
        int redirect_userId = documentService.findDocById(docId).getUserid();
        documentService.deleteDocument(docId);

        return "redirect:/"+redirect_userId+"/docs";
    }

}
