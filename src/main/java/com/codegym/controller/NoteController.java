package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TypeNoteService typeNoteService;

    @ModelAttribute("typeNote")
    public Page<TypeNote> typeNotes(Pageable pageable) {
        return typeNoteService.findAll(pageable);
    }

    @GetMapping("/create-note")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping("/create-note")
    public ModelAndView createNote(@Validated @ModelAttribute("note") Note note, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/note/create");
            return modelAndView;
        }
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("message", "Created new note");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView listNote(@PageableDefault(10) Pageable pageable) {
        Page<Note> notes = noteService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Note note = noteService.findById(id);
        if (note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/edit");
            modelAndView.addObject("note", note);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-note")
    public ModelAndView editNote(@ModelAttribute("note") Note note) {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note", note);
        modelAndView.addObject("message", "Updated note");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Note note = noteService.findById(id);
        if (note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/delete");
            modelAndView.addObject("note", note);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/error404");
        return modelAndView;
    }

    @PostMapping("/delete-note")
    public String deleteForm(@ModelAttribute("note") Note note) {
        noteService.remove(note.getId());
        return "redirect:/";
    }

    @GetMapping("/view-note/{id}")
    public ModelAndView viewNote(@PathVariable Long id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/detail");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @RequestMapping("/search-note")
    public ModelAndView searchForm(@RequestParam("typeNote") Long typeID, Pageable pageable) {

        TypeNote typeNote = typeNoteService.findById(typeID);
        Page<Note> notes = noteService.findAllByTypeNote(typeNote, pageable);
        ModelAndView modelAndView = new ModelAndView("/note/search");
        modelAndView.addObject("typeNote", typeNote);
        modelAndView.addObject("note", notes);
        return modelAndView;

    }
    @RequestMapping("/search-note-title")
    public ModelAndView searchFormTitle(@RequestParam("title") String title, Pageable pageable){
        Page<Note> notes = noteService.findNoteByTitle(title,pageable);
        ModelAndView modelAndView = new ModelAndView("/note/search-title");
        modelAndView.addObject("note",notes);
        return modelAndView;
    }

}
