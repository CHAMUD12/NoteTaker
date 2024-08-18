package org.example.notetaker.controller;

import org.example.notetaker.dto.Note;
import org.example.notetaker.util.AppUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    //Todo: SAVE a note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveNote(@RequestBody Note note){
        //Todo: Handle with BO
        note.setId(AppUtil.genarateid());
        System.out.println(note);
        return ResponseEntity.ok("Note saved successfully");    //
    }
}
