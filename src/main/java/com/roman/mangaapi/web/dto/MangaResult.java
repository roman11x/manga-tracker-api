package com.roman.mangaapi.web.dto;

import com.roman.mangaapi.model.Manga;
import com.roman.mangaapi.model.Status;

/**
 * Represents a response object for a manga-related API or data source.
 * This class serves as a data model to encapsulate information
 * about a manga, including its attributes and related metadata.
 * It can be used to convey manga-related data between different
 * layers of an application.
 */
public class MangaResult {
    private final int malId;
    private final String title;
    private final int chaptersRead;
    private final int totalChapters;
    private final Status status;
    private final String addedAt;
    private final String genres;
    private final String demographic;

    public MangaResult(int malId, String title, int chaptersRead, int totalChapters, Status status, String addedAt, String genres, String demographic) {
        this.malId = malId;
        this.title = title;
        this.chaptersRead = chaptersRead;
        this.totalChapters = totalChapters;
        this.status = status;
        this.addedAt = addedAt;
        this.genres = genres;
        this.demographic = demographic;
    }

    /**
     * Creates a MangaResponse from a Manga object.
     * @param manga Manga object
     * @return MangaResponse
     */
    public static MangaResult from(Manga manga) {
        return new MangaResult(manga.getMalid(), manga.getTitle(), manga.getChaptersRead(), manga.getTotalChapters(), manga.getStatus(), manga.getAddedAt(), manga.getGenres(), manga.getDemographic());
    }

    // getters
    public int getMalId() {
        return malId;
    }

    public String getDemographic() {
        return demographic;
    }

    public String getGenres() {
        return genres;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public Status getStatus() {
        return status;
    }

    public int getTotalChapters() {
        return totalChapters;
    }

    public int getChaptersRead() {
        return chaptersRead;
    }

    public String getTitle() {
        return title;
    }
}
