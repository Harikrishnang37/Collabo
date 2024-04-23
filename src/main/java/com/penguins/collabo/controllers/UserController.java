package com.penguins.collabo.controllers;

import com.penguins.collabo.models.UserEntity;
import com.penguins.collabo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String LoginPage(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String Authenticate(Model model, @ModelAttribute("user") UserEntity loginUser) {

        if(userService.authenticate(loginUser.getUsername(), loginUser.getPassword())) {
            UserEntity user = userService.findUserByUsername(loginUser.getUsername());
            return "redirect:/"+user.getId()+"/docs";
        }
        else{
            return "Incorrect-credentials";
        }
    }

    @GetMapping("/signUp")
    public String SignUpPage(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signUp")
    public String SignUp(Model model, @ModelAttribute("user") UserEntity newUser) {

        if(userService.findUserByUsername(newUser.getUsername()) != null){
            return "user-exists";
        }
        else{
            userService.saveUser(newUser);
            int newUid = userService.findUserByUsername(newUser.getUsername()).getId();
            return "redirect:/"+ newUid+"/docs";
        }
    }






}