package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
@CrossOrigin
@RestController
public class RestNoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TypeNoteService typeNoteService;
//    ---------------------------------List Note-----------------------------
    @RequestMapping(value = "/note/", method = RequestMethod.GET)
    public ResponseEntity<Page<Note>> listNote(Pageable pageable) {
        Page<Note> notes = noteService.findAll(pageable);
        if (notes.getSize() == 0) {
            return new ResponseEntity<Page<Note>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Note>>(notes, HttpStatus.OK);
    }
//    -----------------------------------Create Note-------------------------------------------
    @RequestMapping(value = "/create-note",method = RequestMethod.POST)
    public ResponseEntity<Void> createNote(@RequestBody Note note, UriComponentsBuilder ucBuilder) {
        noteService.save(note);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/note/{id}").buildAndExpand(note.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
//--------------------------------Update Note---------------
@RequestMapping(value = "/note/{id}", method = RequestMethod.PUT)
public ResponseEntity<Note> updateNote(@PathVariable("id") long id, @RequestBody Note note) {
    System.out.println("Updating Note " + id);

    Note currentNote = noteService.findById(id);

    if (currentNote == null) {
        System.out.println("Note with id " + id + " not found");
        return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
    }

    currentNote.setTitle(note.getTitle());
    currentNote.setContent(note.getContent());
    currentNote.setTypeNote(note.getTypeNote());

    noteService.save(currentNote);
    return new ResponseEntity<Note>(currentNote, HttpStatus.OK);
}
//-------------------------------------Delete Note-------------------------------------------
@RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
public ResponseEntity<Note> deleteNote(@PathVariable("id") long id) {
    System.out.println("Fetching & Deleting Note with id " + id);

    Note note = noteService.findById(id);
    if (note == null) {
        System.out.println("Unable to delete. Note with id " + id + " not found");
        return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
    }

    noteService.remove(id);
    return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
}
//--------------------------------------------Search Note---------------------------------------
    @RequestMapping(value = "/search/",method = RequestMethod.GET)
    public ResponseEntity<Page<Note>> findByTypeNote(@RequestParam("type") Long id,Pageable pageable){
        TypeNote typeNote = typeNoteService.findById(id);
        Page<Note> notes = noteService.findAllByTypeNote(typeNote,pageable);
        return new ResponseEntity<Page<Note>>(notes, HttpStatus.OK);
    }
}
