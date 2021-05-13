package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignUpPage(@ModelAttribute("user") User user, Model model){
        return "signup";
    }

    @PostMapping
    public String createNewUserAccount(@ModelAttribute("user") User user, Model model){

        String signUpMessage = null;
        if(userService.checkIfUsernameAlreadyExists(user.getUsername()) != null){
            signUpMessage = "Username already exists!";
        }

        if(signUpMessage == null){
           int rowsAdded = userService.createUser(user);
           if(rowsAdded == 0){
                signUpMessage = "There was an error signing you up. Please try again.";
           }
        }
        if(signUpMessage == null){
            model.addAttribute("SignUpSuccess", true);
        }
        else{
            model.addAttribute("SignUpError", signUpMessage);
        }
        return "signup";
    }

}
