package com.codegym.service;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    void save(Note note);
    void remove(Long id);
    Page<Note> findAll(Pageable pageable);
    Note findById(Long id);
    Page<Note> findAllByTypeNote(TypeNote typeNote, Pageable pageable);
}
