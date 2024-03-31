package com.example.springrestapi.rest;

import com.example.springrestapi.model.NoteDTO;
import com.example.springrestapi.service.NoteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/notes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteResource {

    private final NoteService noteService;

    public NoteResource(final NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{noteid}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable(name = "noteid") final Long noteId) {
        return ResponseEntity.ok(noteService.get(noteId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createNote(@RequestBody @Valid final NoteDTO noteDTO) {
        final Long createdUserId = noteService.create(noteDTO);
        return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
    }

    @PutMapping("/{noteid}")
    public ResponseEntity<Long> updateNote(@PathVariable(name = "noteid") final Long noteId,
            @RequestBody @Valid final NoteDTO noteDTO) {
        noteService.update(noteId, noteDTO);
        return ResponseEntity.ok(noteId);
    }

    @DeleteMapping("/{noteid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteNote(@PathVariable(name = "noteid") final Long noteId) {
        noteService.delete(noteId);
        return ResponseEntity.noContent().build();
    }

}
