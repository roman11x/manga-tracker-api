CREATE TABLE IF NOT EXISTS manga (
                                     mal_id INTEGER PRIMARY KEY,
                                     title TEXT NOT NULL,
                                     chapters_read INTEGER NOT NULL DEFAULT 0 CHECK (chapters_read >= 0),
    total_chapters INTEGER NOT NULL DEFAULT 0 CHECK (total_chapters >= 0),
    status TEXT NOT NULL DEFAULT 'PLAN_TO_READ',
    cover_path TEXT,
    added_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX IF NOT EXISTS idx_manga_status ON manga(status);