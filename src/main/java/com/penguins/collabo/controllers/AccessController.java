package com.penguins.collabo.controllers;

import com.penguins.collabo.dto.DocumentDto;
import com.penguins.collabo.models.AccessRequest;
import com.penguins.collabo.models.UserEntity;
import com.penguins.collabo.service.AccessRequestService;
import com.penguins.collabo.service.DocumentService;
import com.penguins.collabo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccessController {
    private DocumentService documentService;
    private UserService userService;
    private AccessRequestService accessRequestService;

    public AccessController(DocumentService documentService, UserService userService, AccessRequestService accessRequestService) {
        this.documentService = documentService;
        this.userService = userService;
        this.accessRequestService = accessRequestService;
    }

    @GetMapping("/addViewer/{docId}/{userid}")
    public String addViewer(@PathVariable("docId") int docId, @PathVariable("userid") int userid, Model model) {
        documentService.addViewAccess(docId,userid);

        return "redirect:/"+userid+"/docs";
    }

    @GetMapping("/removeViewer/{docId}/{userId}")
    public String removeViewer(@PathVariable("docId") int docId, @PathVariable("userId") int userId, Model model) {
        documentService.removeViewAccess(docId,userId);

        return "redirect:/"+userId+"/docs";
    }

    @GetMapping("/addEditor/{docId}/{userId}")
    public String addEditor(@PathVariable("docId") int docId, @PathVariable("userId") int userId, Model model) {
        documentService.addEditAccess(docId,userId);

        return "redirect:/"+userId+"/docs";
    }

    @GetMapping("/removeEditor/{docId}/{userId}")
    public String removeEditor(@PathVariable("docId") int docId, @PathVariable("userId") int userId, Model model) {
        documentService.removeEditAccess(docId,userId);


        return "redirect:/"+userId+"/docs";
    }

    @GetMapping("/acceptReq/{docId}/{fromUserId}")
    public String AcceptReq(@PathVariable("docId") int docId, @PathVariable("fromUserId") int fromUserId, Model model) {

        AccessRequest ar = accessRequestService.getAccessRequestByTuple(docId,fromUserId);
        if(ar.getType().equals("Edit")){
            documentService.addEditAccess(docId,fromUserId);
        }
        else if(ar.getType().equals("View")){
            documentService.addViewAccess(docId,fromUserId);
        }

        accessRequestService.deleteAccessRequest(ar);

        return "redirect:/"+ar.getToUserId()+"/docs";
    }

    @GetMapping("/declineReq/{docId}/{fromUserId}")
    public String DeclineReq(@PathVariable("docId") int docId, @PathVariable("fromUserId") int fromUserId, Model model) {

        AccessRequest ar = accessRequestService.getAccessRequestByTuple(docId,fromUserId);

        accessRequestService.deleteAccessRequest(ar);

        return "redirect:/"+ar.getToUserId()+"/docs";
    }

    @GetMapping("/createReq/{docID}/{userId}/{type}")
    public String createReq(@PathVariable("docID") int docId, @PathVariable("userId") int userId, @PathVariable("type") String type,  Model model){

        DocumentDto doc = documentService.findDocById(docId);
        UserEntity fromUser = userService.findUserById(userId);
        AccessRequest ar = new AccessRequest(userId,doc.getUserid(),docId,doc.getTitle(),fromUser.getUsername(),type);
        accessRequestService.saveAccessRequest(ar);

        return "redirect:/"+userId+"/docs";
    }
}
