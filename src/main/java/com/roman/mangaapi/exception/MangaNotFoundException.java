package com.roman.mangaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when a manga is not found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MangaNotFoundException extends  RuntimeException {
    public MangaNotFoundException(int malId) { super("Manga with id " + malId + " not found");}
}
