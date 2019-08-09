package com.codegym.service.impl;

import com.codegym.model.TypeNote;
import com.codegym.repository.TypeNoteRepository;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TypeNoteServiceImpl implements TypeNoteService {

    @Autowired
    private TypeNoteRepository typeNoteRepository;
    @Override
    public void save(TypeNote typeNote) {
        typeNoteRepository.save(typeNote);
    }

    @Override
    public void remove(Long id) {
        typeNoteRepository.delete(id);
    }

    @Override
    public Page<TypeNote> findAll(Pageable pageable) {
        return typeNoteRepository.findAll(pageable);
    }

    @Override
    public TypeNote findById(Long id) {
        return typeNoteRepository.findOne(id);
    }
}
