package com.roman.mangaapi.web;

import com.roman.mangaapi.api.JikanClient;
import com.roman.mangaapi.api.MangaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * REST controller for handling search requests.
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {
   private final JikanClient client;

   SearchController(JikanClient client) {
        this.client = client;
    }

    /**
     * Searches for manga based on the provided query string.
     * @param q the search query string
     * @return a list of MangaResult objects containing the search results
     */
    @GetMapping
    public CompletableFuture<List<MangaResult>> search(@RequestParam String q) {
       return client.searchManga(q);
    }
}
