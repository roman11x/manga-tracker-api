package com.roman.mangaapi.service;

import com.roman.mangaapi.db.MangaRepository;
import com.roman.mangaapi.model.Manga;
import com.roman.mangaapi.model.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The MangaService class provides business logic for managing manga-related operations.
 * It is a Spring service component, which enables automatic detection and dependency injection
 * within the application context to handle services for manga entities.
 */
@Service
public class MangaService {
    private final MangaRepository repo;

    public MangaService(MangaRepository repo) {
        this.repo = repo;
    }
    /*
    * Returns a list of all manga in the database.
     */
    public List<Manga> getAllManga() {
        return repo.findAll();
    }
    /*
    * Returns a list of manga with the given status.
     */
    public List<Manga> findByStatus(Status status) {
        return repo.findByStatus(status);
    }
    /*
    * Finds a manga by its MyAnimeList ID.
     */
    public Optional<Manga> findByMalId(int malId) {
        return repo.findByMalId(malId);
    }
}
