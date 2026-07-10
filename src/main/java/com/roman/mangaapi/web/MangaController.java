package com.roman.mangaapi.web;

import com.roman.mangaapi.model.Manga;
import com.roman.mangaapi.model.Status;
import com.roman.mangaapi.service.MangaService;
import com.roman.mangaapi.web.dto.CreateMangaRequest;
import com.roman.mangaapi.web.dto.MangaResult;
import com.roman.mangaapi.web.dto.UpdateChaptersRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling manga-related API requests.
 * This controller processes requests from the `/api/manga` endpoint
 * and communicates with the MangaService to handle the underlying
 * business logic for manga operations.
 */
@RestController
@RequestMapping("/api/manga")
public class MangaController {
    private final MangaService service;
    public MangaController(MangaService service) {
        this.service = service;
    }

    /**
     * Returns a list of all manga in the database.
     * @param status if specified, only manga with the given status will be returned
     * @return a list of MangaResponse objects
     */
    @GetMapping()
    public List<MangaResult> getAllManga(@RequestParam(required = false) Status status) {
        List<Manga> mangaList = status == null ? service.getAllManga() : service.findByStatus(status);
        return mangaList
                .stream()
                .map(MangaResult::from)
                .toList();
    }

    /**
     * Returns a single manga by its MyAnimeList ID.
     * @param id MyAnimeList ID of the manga
     * @return a MangaResponse object
     */
    @GetMapping("{id}")
    public MangaResult getMangaById(@PathVariable int id) {
        return MangaResult.from(service.findByMalId(id).orElseThrow());
    }

    /**
     * Creates a new manga record in the database.
     * @param request the request body containing the manga details
     * @return a MangaResponse object representing the created manga
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MangaResult create(@RequestBody CreateMangaRequest request) {
        Manga created = service.create(request.getTitle(),request.getMalId(),request.getTotalChapters(),request.getDemographic(),request.getGenres());
        return MangaResult.from(created);
    }

    /**
     * Updates the chapters read for a manga in the database.
     * @param malId the MyAnimeList ID of the manga
     * @param request the request body containing the new chapter count
     * @return a MangaResponse object representing the updated manga
     */
    @PatchMapping("{malId}/chapters")
    public MangaResult updateChapters(@PathVariable int malId, @RequestBody UpdateChaptersRequest request) {
        return MangaResult.from(service.updateChapters(malId, request.chaptersRead));
    }

    /**
     * Updates the status of a manga in the database.
     * @param malId the MyAnimeList ID of the manga
     * @param status the new status of the manga
     * @return a MangaResponse object representing the updated manga
     */
    @PatchMapping("{malId}/status")
    public MangaResult updateStatus(@PathVariable int malId, @RequestParam Status status) {
        return MangaResult.from(service.updateStatus(malId, status));
    }

    /**
     * Deletes a manga record from the database.
     * @param malId the MyAnimeList ID of the manga to delete
     */
    @DeleteMapping("{malId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int malId) {
        service.delete(malId);
    }
}
