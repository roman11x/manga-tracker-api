package com.roman.mangaapi.web;

import com.roman.mangaapi.db.MangaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Simple controller to check if the application is running.
 */
@RestController
@RequestMapping("/api")
public class PingController {
    private final MangaRepository repo;
    public PingController(MangaRepository repo) {
        this.repo = repo;
    }

    /**
     * Checks if the application is running by returning a simple message.
     * @return map with a single key-value pair
     */
    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("message", "pong");
    }

    /**
     * a debug endpoint to check the database size
     * @return the total amount of manga in the database
     */
    @GetMapping("/debug/count")
    public Map<String, Integer> count() {
        return Map.of("total", repo.findAll().size());
    }
}
