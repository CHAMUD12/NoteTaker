
package org.example.notetaker.service.impl;

import org.example.notetaker.customObj.NoteErrorResponse;
import org.example.notetaker.customObj.NoteResponse;
import org.example.notetaker.dao.NoteDAO;
import org.example.notetaker.dto.NoteDTO;
import org.example.notetaker.entity.NoteEntity;
import org.example.notetaker.exception.DataPersistFailedException;
import org.example.notetaker.exception.NoteNotFound;
import org.example.notetaker.service.NoteService;
import org.example.notetaker.util.AppUtil;
import org.example.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {
    @Autowired
    private NoteDAO noteDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        var noteEntity = mapping.convertToEntity(noteDTO);
        var saveNoted = noteDAO.save(noteEntity);
        if (saveNoted == null){
            throw new DataPersistFailedException("Cannot save note");
        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE-c67069d1-8e79-4d33-919a-f7a02820ac78
        Optional<NoteEntity> tmpNoteEntity= noteDAO.findById(noteId);
        if(!tmpNoteEntity.isPresent()){
            throw new NoteNotFound("Note not found");
        }else {
            tmpNoteEntity.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
            tmpNoteEntity.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
            tmpNoteEntity.get().setCreateDate(incomeNoteDTO.getCreateDate());
            tmpNoteEntity.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
        }
    }

    @Override
    public void deleteNote(String noteId) {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE-b38f55ef-e148-4e4b-8586-a3dcf4d012d3
        Optional<NoteEntity> findId = noteDAO.findById(noteId);
        if(!findId.isPresent()){
            throw new NoteNotFound("Note not found");
        }else {
            noteDAO.deleteById(noteId);
        }
    }

    @Override
    public NoteResponse getSelectedNote(String noteId) {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/NOTE-b38f55ef-e148-4e4b-8586-a3dcf4d012d3
        if(noteDAO.existsById(noteId)){
            return mapping.convertToDTO(noteDAO.getReferenceById(noteId));
        }else {
            return new NoteErrorResponse(0,"Note not found");
        }    }

    @Override
    public List<NoteDTO> getAllNotes() {
        // http://localhost:8080/NoteTaker_war_exploded/api/v1/notes/allnotes
//        List<NoteEntity> getAllNotes = noteDAO.findAll();
//        List<NoteDTO> noteDTOS = mapping.convertToDTO(getAllNotes);
//        return noteDTOS;
        return mapping.convertToDTO(noteDAO.findAll());
    }
}
