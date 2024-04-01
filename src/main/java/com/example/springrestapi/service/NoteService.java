package com.example.springrestapi.service;

import com.example.springrestapi.domain.Note;
import com.example.springrestapi.domain.User;
import com.example.springrestapi.model.NoteDTO;
import com.example.springrestapi.repos.NoteRepository;
import com.example.springrestapi.repos.NoteSpecifications;
import com.example.springrestapi.repos.UserRepository;
import com.example.springrestapi.util.JwtTokenUtil;
import com.example.springrestapi.util.NotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private static final String USER_NOT_FOUND = "User not found with username: ";
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public NoteService(final NoteRepository noteRepository, final UserRepository userRepository,
                       final JwtTokenUtil jwtTokenUtil) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public List<NoteDTO> findAll(final String jwtToken) {
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND + username));

        Specification<Note> spec = NoteSpecifications.byUserId(user.getId());
        final List<Note> notes = noteRepository.findAll(spec);
        notes.sort(Comparator.comparing(note -> note.getUser().getId()));
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
        Note savedNote = noteRepository.save(note);
        return savedNote.getUser().getId();
    }

    public void update(final String jwtToken, final Long noteId, final NoteDTO noteDTO) {
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND + username));
        final Note noteToUpdate = noteRepository.findById(noteId)
                .orElseThrow(() -> new NotFoundException("Note not found with id: " + noteId));

        if (!noteToUpdate.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Note with id " + noteId + " does not belong to user with id " + user.getId());
        }
        mapToEntity(noteDTO, noteToUpdate);
        noteToUpdate.setId(noteId);
        noteToUpdate.setUser(user);
        noteRepository.save(noteToUpdate);
    }

    public void delete(final Long noteId) {
        noteRepository.deleteById(noteId);
    }

    private NoteDTO mapToDTO(final Note note, final NoteDTO noteDTO) {
        noteDTO.setUserId(note.getUser() != null ? note.getUser().getId() : null);
        noteDTO.setId(note.getId());
        noteDTO.setTitle(note.getTitle());
        noteDTO.setContent(note.getContent());
        return noteDTO;

    }

    private Note mapToEntity(final NoteDTO noteDTO, final Note note) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(currentUsername);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            note.setUser(user);
        } else {
            throw new UsernameNotFoundException(USER_NOT_FOUND + currentUsername);
        }

        note.setId(noteDTO.getId());
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());

        return note;
    }

}
