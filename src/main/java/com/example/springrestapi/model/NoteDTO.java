package com.example.springrestapi.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NoteDTO {

    @NotNull
    private Long id;
    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 255)
    private String content;

    private Long user;

}
