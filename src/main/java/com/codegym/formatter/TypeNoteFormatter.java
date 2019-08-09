package com.codegym.formatter;
import com.codegym.model.TypeNote;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TypeNoteFormatter implements Formatter<TypeNote> {

    private TypeNoteService typeNoteService;

    @Autowired
    public TypeNoteFormatter(TypeNoteService typeNoteService){
         this.typeNoteService = typeNoteService;
    }
    @Override
    public TypeNote parse(String text, Locale locale) throws ParseException {
        return typeNoteService.findById(Long.parseLong(text));
    }

    @Override
    public String print(TypeNote object, Locale locale) {
        return "["+object.getId()+","+object.getName()+"]";
    }
}
