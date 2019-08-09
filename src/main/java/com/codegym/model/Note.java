package com.codegym.model;


import javax.persistence.*;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeNote typeNote;
    public Note() {
    }
    public Note(Long id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content, TypeNote typeNote) {
        this.title = title;
        this.content = content;
        this.typeNote = typeNote;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeNote getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(TypeNote typeNote) {
        this.typeNote = typeNote;
    }
}
