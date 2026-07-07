package com.roman.mangaapi.web.dto;

/**
 * Represents a request object to update the chapters read for a manga.
 */
public class UpdateChaptersRequest {
    public int chaptersRead;

    public UpdateChaptersRequest() {}

    public int getChaptersRead() {
        return chaptersRead;
    }
}
