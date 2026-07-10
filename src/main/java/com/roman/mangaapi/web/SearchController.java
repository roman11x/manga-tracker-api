package com.roman.mangaapi.web;

import com.roman.mangaapi.api.JikanClient;
import com.roman.mangaapi.api.MangaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
   private final JikanClient client;

   SearchController(JikanClient client) {
        this.client = client;
    }
    @GetMapping
    public List<MangaResult> search(@RequestParam String query) {
       return client.searchManga(query).join();
    }
}
