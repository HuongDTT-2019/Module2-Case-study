package com.codegym.controller;

import com.codegym.model.TypeNote;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
@CrossOrigin
@RestController
public class RestTypeNoteController {
    @Autowired
    TypeNoteService typeNoteService;

    //------------------------------List TypeNote----------------------------
    @RequestMapping(value = "/types/", method = RequestMethod.GET)
    public ResponseEntity<Page<TypeNote>> listTypeNote(Pageable pageable) {
        Page<TypeNote> typeNotes = typeNoteService.findAll(pageable);
        if (typeNotes.getSize() == 0) {
            return new ResponseEntity<Page<TypeNote>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<TypeNote>>(typeNotes, HttpStatus.OK);
    }

    //------------------------------Create TypeNote----------------------
    @RequestMapping(value = "/create-type", method = RequestMethod.POST)
    public ResponseEntity<Void> createType(@RequestBody TypeNote typeNote, UriComponentsBuilder ucBuilder) {
        typeNoteService.save(typeNote);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/type/{id}").buildAndExpand(typeNote.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }

    //-----------------------------Update TypeNote-----------------------
    @RequestMapping(value = "/update-type/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TypeNote> updateType(@PathVariable("id") long id, @RequestBody TypeNote typeNote) {
        System.out.println("Update type" + id);
        TypeNote currentType = typeNoteService.findById(id);
        if (currentType == null) {
            System.out.println("Type with id" + id + "not found");
        } else {
            currentType.setName(typeNote.getName());
            currentType.setDescription(typeNote.getDescription());
        }
        typeNoteService.save(currentType);
        return new ResponseEntity<TypeNote>(currentType, HttpStatus.OK);
    }

    //--------------------------Delete TypeNote------------------------------
    @RequestMapping(value = "/delete-type/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TypeNote> deleteType(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting typeNote with id " + id);
        TypeNote typeNote = typeNoteService.findById(id);
        if (typeNote == null) {
            System.out.println("Unable to delete. typeNote with id " + id + " not found");
            return new ResponseEntity<TypeNote>(HttpStatus.NOT_FOUND);
        }
        typeNoteService.remove(id);
        return new ResponseEntity < TypeNote > (typeNote,HttpStatus.OK);
    }
}
