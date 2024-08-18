package org.example.notetaker.controller;

import org.example.notetaker.dto.NoteDTO;
import org.example.notetaker.util.AppUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    //Todo: SAVE a note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveNote(@RequestBody NoteDTO noteDTO){
        //Todo: Handle with BO
        noteDTO.setId(AppUtil.genarateid());
        System.out.println(noteDTO);
        return ResponseEntity.ok("Note saved successfully");    //
    }

    //Todo: GetAll a note
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        return null;
    }

    //Todo: SEARCH a note
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        System.out.println(noteId);
        return null;
    }
}
