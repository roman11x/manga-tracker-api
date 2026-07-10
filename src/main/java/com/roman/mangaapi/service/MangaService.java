package com.roman.mangaapi.service;

import com.roman.mangaapi.db.MangaRepository;
import com.roman.mangaapi.model.Manga;
import com.roman.mangaapi.model.Status;
import com.roman.mangaapi.web.dto.CreateMangaRequest;
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

    /**
     * Creates a new manga record in the database.
     */
    public Manga create(String title, int malId, int totalChapters, String genres, String demographic) {
        var manga = new Manga(malId, title, totalChapters, Status.PLAN_TO_READ);
        manga.setGenres(genres);
        manga.setDemographic(demographic);
        repo.insert(manga);
        return manga;
    }

    /**
     * Updates the chapters read for a manga in the database.
     * @param malId the MyAnimeList ID of the manga
     * @param chapters the number of chapters read
     * @return the updated Manga object
     */
    public Manga updateChapters(int malId, int chapters) {
        repo.updateChaptersRead(malId, chapters);
        return findByMalId(malId).orElseThrow();
    }

    /**
     * Updates the total chapter count for a manga in the database.
     * @param malId the MyAnimeList ID of the manga
     * @param status the new status of the manga
     * @return the updated Manga object
     */
    public Manga updateStatus(int malId, Status status) {
        repo.updateStatus(malId, status);
        return findByMalId(malId).orElseThrow();
    }

    /**
     * Deletes a manga record from the database.
     * @param malId the MyAnimeList ID of the manga to delete
     */
    public void delete(int malId) {
        repo.delete(malId);
    }

}
