package org.example.notetaker.controller;

import lombok.RequiredArgsConstructor;
import org.example.notetaker.exception.DataPersistFailedException;
import org.example.notetaker.exception.NoteNotFound;
import org.example.notetaker.service.NoteService;
import org.example.notetaker.dto.NoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> createNote(@RequestBody NoteDTO note){
        //Todo: Handle with BO
        if (note == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                noteService.saveNote(note);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)  // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    public ResponseEntity<Void> updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        try {
            noteService.updateNote(noteId, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Todo: DELETE a note
    @DeleteMapping(value ="/{noteId}" )
    //  http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/1990119
    //  http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb
    public ResponseEntity<String> deleteNote(@PathVariable ("noteId") String noteId) {
        return noteService.deleteNote(noteId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
