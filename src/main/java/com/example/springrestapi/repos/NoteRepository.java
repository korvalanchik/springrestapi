package com.example.springrestapi.repos;

import com.example.springrestapi.domain.Note;
import com.example.springrestapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findFirstByUser(User user);

}
