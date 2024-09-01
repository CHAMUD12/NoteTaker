package org.example.notetaker.service;

import org.example.notetaker.dao.NoteDAO;
import org.example.notetaker.dto.NoteDTO;
import org.example.notetaker.entity.NoteEntity;
import org.example.notetaker.util.AppUtil;
import org.example.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {
    @Autowired
    private NoteDAO noteDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        var noteEntity = mapping.convertToEntity(noteDTO);
        noteDAO.save(noteEntity);
        return "Saved successfully in Service layer";
    }

    @Override
    public void updateNote(String noteId, NoteDTO noteDTO) {

    }

    @Override
    public boolean deleteNote(String noteId) {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE-b38f55ef-e148-4e4b-8586-a3dcf4d012d3
        if (noteDAO.existsById(noteId)){
            noteDAO.deleteById(noteId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE-b38f55ef-e148-4e4b-8586-a3dcf4d012d3
        return mapping.convertToDTO(noteDAO.getReferenceById(noteId));
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/allnotes
//        List<NoteEntity> getAllNotes = noteDAO.findAll();
//        List<NoteDTO> noteDTOS = mapping.convertToDTO(getAllNotes);
//        return noteDTOS;
        return mapping.convertToDTO(noteDAO.findAll());
    }
}
