package com.roman.mangaapi.web;

import com.roman.mangaapi.db.DatabaseException;
import com.roman.mangaapi.exception.MangaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Global exception handler for API exceptions.
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * Handles MangaNotFoundException and returns a 404 status code.
     * @param e the exception to handle
     * @return a map containing an error message
     */
    @ExceptionHandler(MangaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleMangaNotFoundException(MangaNotFoundException e) {
        return Map.of("error", e.getMessage());
    }

    /**
     * Handles DatabaseException and returns a 500 status code.
     * @param e the exception to handle
     * @return a map containing an error message
     */
    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleDatabaseException(DatabaseException e) {
        return Map.of("error", "A database error occurred");
    }
}
