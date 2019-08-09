package com.codegym.controller;

import com.codegym.model.TypeNote;
import com.codegym.service.TypeNoteService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TypeNoteController {
        @Autowired
        private TypeNoteService typeNoteService;
        @GetMapping("/create-type")
        public ModelAndView showCreateForm(){
            ModelAndView modelAndView = new ModelAndView("/type/create");
            modelAndView.addObject("type",new TypeNote());
            return modelAndView;
        }
        @PostMapping("/create-type")
    public ModelAndView saveType(@ModelAttribute("type") TypeNote typeNote){
            typeNoteService.save(typeNote);
            ModelAndView modelAndView = new ModelAndView("/type/create");
            modelAndView.addObject("type",new TypeNote());
            modelAndView.addObject("message","Created a new type");
            return modelAndView;
        }
        @GetMapping("/type")
    public ModelAndView listType(@PageableDefault(10) Pageable pageable){
            Page<TypeNote> typeNotes = typeNoteService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("/type/list");
            modelAndView.addObject("type",typeNotes);
            return modelAndView;
        }
        @GetMapping("/edit-type/{id}")
    public ModelAndView showEditType(@PathVariable Long id){
          TypeNote typeNote = typeNoteService.findById(id);
          if (typeNote != null){
              ModelAndView modelAndView = new ModelAndView("/type/edit");
              modelAndView.addObject("type",typeNote);
              return modelAndView;
          }
          ModelAndView modelAndView = new ModelAndView("/error404");
          return modelAndView;
        }
       @PostMapping("/edit-type")
    public ModelAndView editType(@ModelAttribute("type") TypeNote typeNote){
           typeNoteService.save(typeNote);
           ModelAndView modelAndView = new ModelAndView("/type/edit");
           modelAndView.addObject("type", typeNote);
           modelAndView.addObject("message","Updated type");
           return modelAndView;
       }
       @GetMapping("/delete-type/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
            TypeNote typeNote = typeNoteService.findById(id);
            if (typeNote!=null){
                ModelAndView modelAndView =new ModelAndView("/type/delete");
                modelAndView.addObject("type",typeNote);
                return modelAndView;
            }
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
       }
       @PostMapping("/delete-type")
    public String deleteForm(@ModelAttribute("type") TypeNote typeNote){
            typeNoteService.remove(typeNote.getId());
            return "redirect:type";
       }
}
