package com.example.springrestapi.repos;

import com.example.springrestapi.domain.Note;
import org.springframework.data.jpa.domain.Specification;

public class NoteSpecifications {

    public static Specification<Note> byUserId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };
    }
}