package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "note_type")
public class TypeNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToMany(targetEntity = TypeNote.class)
    @JsonIgnore
    private List<Note> notes;

    @Override
    public String toString(){
        return String.format("TypeNote[id=%d,name=%s,description=%s]",id,name,description);
    }
    public TypeNote() {
    }

    public TypeNote(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypeNote(String name, String description, List<Note> notes) {
        this.name = name;
        this.description = description;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
