package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesList;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NotesMapper notesMapper;

    public NoteService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public void createNote(Notes note){
        NotesList notesList = new NotesList();
        notesList.setNoteTitle(note.getNoteTitle());
        notesList.setNoteDescription(note.getNoteDescription());
        notesList.setUserid(note.getUserid());
        int value = this.notesMapper.addNote(notesList);

    }

    public List<NotesList> getCreatedNotes(Integer userid){
        return this.notesMapper.getNotes(userid);
    }

    public int updateNote(Notes note) {
        return notesMapper.updateNote(new Notes(note.getNoteid(), note.getNoteTitle(), note.getNoteDescription(), note.getUserid()));
    }
}
