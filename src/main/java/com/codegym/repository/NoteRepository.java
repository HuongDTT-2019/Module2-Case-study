package com.codegym.repository;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository <Note,Long>{
    Page<Note> findAllByTypeNote(TypeNote typeNote, Pageable pageable);
    Page<Note> findAll(Pageable pageable);

    @Query(value = "select n from Note n where n.title like %?1%")
    Page<Note> findNoteByTitle(String title,Pageable pageable);
}
