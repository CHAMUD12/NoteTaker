package org.example.notetaker.service;

import org.example.notetaker.customObj.NoteResponse;
import org.example.notetaker.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
