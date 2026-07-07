package com.roman.mangaapi.web;

import com.roman.mangaapi.model.Manga;
import com.roman.mangaapi.model.Status;
import com.roman.mangaapi.service.MangaService;
import com.roman.mangaapi.web.dto.MangaResponse;
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
    public List<MangaResponse> getAllManga(@RequestParam(required = false) Status status) {
        List<Manga> mangaList = status == null ? service.getAllManga() : service.findByStatus(status);
        return mangaList
                .stream()
                .map(MangaResponse::from)
                .toList();
    }

    /**
     * Returns a single manga by its MyAnimeList ID.
     * @param id MyAnimeList ID of the manga
     * @return a MangaResponse object
     */
    @GetMapping("{id}")
    public MangaResponse getMangaById(@PathVariable int id) {
        return MangaResponse.from(service.findByMalId(id).orElseThrow());
    }
}
