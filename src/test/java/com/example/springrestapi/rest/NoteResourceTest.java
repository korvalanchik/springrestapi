package com.example.springrestapi.rest;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.example.springrestapi.model.NoteDTO;
import com.example.springrestapi.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class NoteResourceTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteResource noteResource;

    @Test
    public void getAllNotes_ReturnsListOfNotes() {
        // Arrange
        List<NoteDTO> notes = new ArrayList<>();
        notes.add(new NoteDTO());
        when(noteService.findAll(anyString())).thenReturn(notes);

        // Act
        ResponseEntity<List<NoteDTO>> response = noteResource.getAllNotes("Bearer token");

        // Assert
        verify(noteService).findAll("token");
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().size() == 1; // Assuming only one note is returned
    }

    @Test
    public void getNote_ReturnsNote() {
        // Arrange
        NoteDTO note = new NoteDTO();
        when(noteService.get(anyLong())).thenReturn(note);

        // Act
        ResponseEntity<NoteDTO> response = noteResource.getNote(1L);

        // Assert
        verify(noteService).get(1L);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
    }

    @Test
    public void createNote_ReturnsCreatedNoteId() {
        // Arrange
        NoteDTO noteDTO = new NoteDTO();
        when(noteService.create(any(NoteDTO.class))).thenReturn(1L);

        // Act
        ResponseEntity<Long> response = noteResource.createNote(noteDTO);

        // Assert
        verify(noteService).create(noteDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() != null && response.getBody() == 1L;
    }

    @Test
    public void updateNote_ReturnsUpdatedNoteId() {
        // Arrange
        NoteDTO noteDTO = new NoteDTO();
        doNothing().when(noteService).update(anyString(), anyLong(), any(NoteDTO.class));

        // Act
        ResponseEntity<Long> response = noteResource.updateNote(1L, noteDTO, "Bearer token");

        // Assert
        verify(noteService).update("token", 1L, noteDTO);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null && response.getBody() == 1L;
    }

    @Test
    public void deleteNote_ReturnsNoContent() {
        // Act
        ResponseEntity<Void> response = noteResource.deleteNote(1L);

        // Assert
        verify(noteService).delete(1L);
        assert response.getStatusCode() == HttpStatus.OK;
    }
}
