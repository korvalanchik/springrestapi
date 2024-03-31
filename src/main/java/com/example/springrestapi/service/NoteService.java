package com.example.springrestapi.service;

import com.example.springrestapi.domain.Note;
import com.example.springrestapi.domain.User;
import com.example.springrestapi.model.NoteDTO;
import com.example.springrestapi.repos.NoteRepository;
import com.example.springrestapi.repos.UserRepository;
import com.example.springrestapi.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(final NoteRepository noteRepository, final UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<NoteDTO> findAll() {
        final List<Note> notes = noteRepository.findAll(Sort.by("userId"));
        return notes.stream()
                .map(note -> mapToDTO(note, new NoteDTO()))
                .toList();
    }

    public NoteDTO get(final Long noteId) {
        return noteRepository.findById(noteId)
                .map(note -> mapToDTO(note, new NoteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final NoteDTO noteDTO) {
        final Note note = new Note();
        mapToEntity(noteDTO, note);
        return noteRepository.save(note).getUsersId();
    }

    public void update(final Long noteId, final NoteDTO noteDTO) {
        final Note note = noteRepository.findById(noteId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(noteDTO, note);
        noteRepository.save(note);
    }

    public void delete(final Long noteId) {
        noteRepository.deleteById(noteId);
    }

    private NoteDTO mapToDTO(final Note note, final NoteDTO noteDTO) {
        noteDTO.setUserId(note.getUsersId());
        noteDTO.setTitle(note.getTitle());
        noteDTO.setContent(note.getContent());
        noteDTO.setUser(note.getUser() == null ? null : note.getUsersId());
        return noteDTO;
    }

    private Note mapToEntity(final NoteDTO noteDTO, final Note note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Retrieve the current username
        com.example.springrestapi.domain.User user = userRepository.findByUsername(currentUsername);
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setUserId(user.getId());
        return note;
    }

}
