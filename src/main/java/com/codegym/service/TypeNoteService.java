package com.codegym.service;

import com.codegym.model.TypeNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeNoteService  {
    void save(TypeNote typeNote);
    void remove(Long id);
    Page<TypeNote> findAll(Pageable pageable);
    TypeNote findById(Long id);
}
