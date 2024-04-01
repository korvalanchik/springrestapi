package com.example.springrestapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReferencedException extends RuntimeException {

    public ReferencedException() {
        super();
    }

    public ReferencedException(final com.example.springrestapi.util.ReferencedWarning referencedWarning) {
        super(referencedWarning.toMessage());
    }

}
