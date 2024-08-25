package org.example.notetaker.service;

import org.example.notetaker.dto.NoteDTO;
import org.example.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public final class NoteServiceIMPL implements NoteService {
    List<NoteDTO> saveNoteTmp = new ArrayList<>();
    public NoteServiceIMPL() {
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of SE","SE Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb","Priciples of CS","CS Desc","P2","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a069-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of NW","NW Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a70-2ebc-41b2-9de6-4e9ddbbba65b","Priciples of UI","UI Desc","P2","20240825"));
        System.out.println(saveNoteTmp);
    }
    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        System.out.println(noteDTO);
        return "Saved successfully in Service layer";
    }

    @Override
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
        ListIterator<NoteDTO> updatedList = saveNoteTmp.listIterator();
        while (updatedList.hasNext()) {
            NoteDTO noteDTO = updatedList.next();
            if (noteId.equals(noteDTO.getNoteId())) {
                incomeNoteDTO.setNoteId(noteDTO.getNoteId());
                updatedList.set(incomeNoteDTO);
                break;
            }
        }
    }

    @Override
    public boolean deleteNote(String noteId) {
        return false;
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        for (NoteDTO noteDTO : saveNoteTmp) {
            if (noteDTO.getNoteId().equals(noteId)) {
                return noteDTO;
            }
        }
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        System.out.println(saveNoteTmp.toString());
        return saveNoteTmp;
    }
}
