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
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final NoteService noteService;

    public NotesController(AuthenticationService authenticationService, UserMapper userMapper, NoteService noteService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.noteService = noteService;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating NoteService Bean");
    }


    @PostMapping
    public String createNote(Authentication authentication, @ModelAttribute("note") Notes note, Model model){
        User user = this.userMapper.getUsername(authentication.getName());
        note.setUserid(user.getUserId());
        this.noteService.createNote(note);
        model.addAttribute("ListOfNotes", this.noteService.getCreatedNotes(note.getUserid()));
        return "home";
    }

    @PutMapping
    public String updateNote(Authentication authentication, @ModelAttribute("note") Notes note, Model model){
        User user = this.userMapper.getUsername(authentication.getName());
        note.setUserid(user.getUserId());
        this.noteService.updateNote(note);
        return "redirect:/home";
    }

    @DeleteMapping
    public String deleteNote(Authentication authentication, @ModelAttribute("note") Notes note, Model model){
        User user = this.userMapper.getUsername(authentication.getName());
        note.setUserid(user.getUserId());
        this.noteService.deleteNote(note);
        return "redirect:/home";
    }
}

