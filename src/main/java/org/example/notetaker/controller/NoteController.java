package org.example.notetaker.controller;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.bo.NoteBO;
import org.example.notetaker.dto.NoteDTO;
import org.example.notetaker.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    @Autowired
    private final NoteBO noteBO;

    //Todo: SAVE a note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO note){
        //Todo: Handle with BO
        var saveData = noteBO.saveNote(note);
        return ResponseEntity.ok(saveData);
    }

    //Todo: GetAll a note
    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        System.out.println("Get all notes");
        return null;
    }

    //Todo: SEARCH a note
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        System.out.println(noteId);
        return new NoteDTO(
                "NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb",
                "REST services",
                "Explain the REST",
                "P1",
                "20240818"
        );
    }

    //Todo: UPDATE a note
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        System.out.println(noteId);
        System.out.println(note+ " Updated");
    }

    //Todo: DELETE a note
    @DeleteMapping(value ="/{noteId}" )
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        System.out.println(noteId + " Deleted");
    }
}
