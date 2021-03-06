package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesList;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final NoteService noteService;

    public HomeController(AuthenticationService authenticationService, UserMapper userMapper, NoteService noteService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.noteService = noteService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("note") Notes note, Model model) {
        User user = this.userMapper.getUsername(authentication.getName());
        note.setUserid(user.getUserId());
        model.addAttribute("ListOfNotes", this.noteService.getCreatedNotes(note.getUserid()));

        return "home";
    }



}
