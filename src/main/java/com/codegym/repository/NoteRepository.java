package com.codegym.repository;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository <Note,Long>{
    Page<Note> findAllByTypeNote(TypeNote typeNote, Pageable pageable);
    Page<Note> findAll(Pageable pageable);
}
