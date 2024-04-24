package com.penguins.collabo.controllers;

import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.service.AccessRequestService;
import com.penguins.collabo.service.DocumentService;
import com.penguins.collabo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DocumentChangeController {
    private  DocumentService documentService;
    private  UserService userService;
    private  AccessRequestService accessRequestService;

    public DocumentChangeController(DocumentService documentService, UserService userService, AccessRequestService accessRequestService) {
        this.documentService = documentService;
        this.userService = userService;
        this.accessRequestService = accessRequestService;
    }

    @PostMapping("/{userid}/{docId}/edit")
    public String updateDoc(@PathVariable("docId") int docId, @PathVariable("userid") int userId, @ModelAttribute("doc") DocumentDto doc){
        documentService.updateDoc(doc);
        return "redirect:/"+userId+"/docs";
    }

    @GetMapping("/{userid}/{docID}/edit")
    public String editDoc(@PathVariable("docID") int docID, @PathVariable("userid") int userID , Model model) {

        if(documentService.hasEditAccess(docID, userID)) {
            DocumentDto doc = documentService.findDocById(docID);
            model.addAttribute("doc",doc);
            return "docs-edit";
        }
        else if(documentService.hasViewAccess(docID,userID)){
            DocumentDto doc = documentService.findDocById(docID);
            model.addAttribute("doc",doc);
            model.addAttribute("docId", docID);
            model.addAttribute("userId",userID);
            return "docs-view";
        }
        else{
            model.addAttribute("docId", docID);
            model.addAttribute("userId",userID);
            return "docs-accessDenied";
        }
    }
}
