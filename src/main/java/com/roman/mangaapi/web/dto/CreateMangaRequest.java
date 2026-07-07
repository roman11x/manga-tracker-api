package com.roman.mangaapi.web.dto;

/**
 * Represents a request object to encapsulate the minimal data required
 * for creating or updating a manga entry.
 * This class includes the MyAnimeList ID, the manga title, and the total
 * number of chapters. It is typically used for input scenarios such as
 * creating a new manga record or modifying an existing one.
 * We do not include the status because new manga are always considered to be READING
 */
public class CreateMangaRequest {
    public int malId;
    public String title;
    public int totalChapters;
    public String demographic;
    public String genres;

    public CreateMangaRequest() {
    }
    public int getMalId() {
        return malId;
    }
    public String getTitle() {
        return title;
    }
    public int getTotalChapters() {
        return totalChapters;
    }
    public String getDemographic() {
        return demographic;
    }
    public String getGenres() {
        return genres;
    }
}
