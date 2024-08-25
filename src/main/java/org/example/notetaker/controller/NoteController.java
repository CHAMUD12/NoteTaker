package org.example.notetaker.controller;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.service.NoteService;
import org.example.notetaker.dto.NoteDTO;
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
    private final NoteService noteService;

    //Todo: SAVE a note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)   // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes
    public ResponseEntity<String> createNote(@RequestBody NoteDTO note){
        //Todo: Handle with BO
        var saveData = noteService.saveNote(note);
        return ResponseEntity.ok(saveData);
    }

    //Todo: GetAll a note
    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/allnotes
    // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }

    //Todo: SEARCH a note
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        return noteService.getSelectedNote(noteId);
    }

    //Todo: UPDATE a note
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)  // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        noteService.updateNote(noteId, note);
        System.out.println(note + "updated");
    }

    //Todo: DELETE a note
    @DeleteMapping(value ="/{noteId}" )
    //  http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/1990119
    //  http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        noteService.deleteNote(noteId);
        System.out.println(noteId + " Deleted");
    }
}
