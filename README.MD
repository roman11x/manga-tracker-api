# manga-tracker-api

A REST API layer over [manga-tracker](https://github.com/roman11x/manga-tracker) — the
same reading data, the same SQLite database, now reachable over HTTP instead of only
through the terminal. Built with Spring Boot on top of the TUI's existing model and
data-access classes.

---

## Overview

manga-tracker started as a Lanterna terminal app. This project exposes the same manga
collection — add, update, browse, search — as a JSON REST API, so any client (a phone,
a script, a future frontend) can read and write the same tracked data the TUI does.

- Full CRUD over tracked manga: create, read (all / by status / by id), update (chapters
  read, status), delete
- Live search against the [Jikan API](https://jikan.moe/) (MyAnimeList data), same
  endpoint the TUI's search screen calls
- Layered architecture — controller / service / repository — with request and response
  DTOs so the wire format is deliberate, not just the database schema leaking out
- Centralized error handling: a missing manga returns a clean `404` with a JSON body,
  not a stack trace
- Connection-pooled SQLite access via `DataSource` + HikariCP — safe under concurrent
  requests, unlike a single held `Connection`
- Tested with `@WebMvcTest` + `MockMvc`

---

## API Reference

| Method | Path | Description | Response |
|---|---|---|---|
| `GET` | `/api/manga` | All tracked manga | `200`, JSON array |
| `GET` | `/api/manga?status=READING` | Filtered by status | `200`, JSON array |
| `GET` | `/api/manga/{malId}` | One manga by MyAnimeList ID | `200`, or `404` if missing |
| `POST` | `/api/manga` | Add a manga | `201`, the created manga |
| `PATCH` | `/api/manga/{malId}/chapters` | Update chapters read | `200`, the updated manga |
| `PATCH` | `/api/manga/{malId}/status` | Update reading status | `200`, the updated manga |
| `DELETE` | `/api/manga/{malId}` | Remove a manga | `204`, or `404` if missing |
| `GET` | `/api/search?q=...` | Search Jikan/MyAnimeList | `200`, JSON array of results |

**Example — fetch one manga:**

```bash
curl http://localhost:8080/api/manga/21
```

```json
{
  "malId": 21,
  "title": "Dorohedoro",
  "chaptersRead": 45,
  "totalChapters": 170,
  "status": "READING",
  "addedAt": "2025-05-19 12:00:00"
}
```

**Example — add a manga:**

```bash
curl -X POST http://localhost:8080/api/manga \
  -H "Content-Type: application/json" \
  -d '{"malId": 21, "title": "Dorohedoro", "totalChapters": 170}'
```

---

## Requirements

- Java 25
- Maven

---

## Running locally

```bash
git clone https://github.com/roman11x/manga-tracker-api.git
cd manga-tracker-api
mvn spring-boot:run
```

The API starts on `http://localhost:8080`. `schema.sql` creates the SQLite database
automatically on first run if `manga-tracker.db` doesn't already exist in the project
root.

To reuse your existing manga-tracker TUI data instead of starting empty, copy your
`manga-tracker.db` into the project root before starting the app.

---

## Testing

```bash
mvn test
```

Runs the `@WebMvcTest` controller test suite — the web layer against mocked service
dependencies, no database required.

---

## Tech Stack

- **Spring Boot 4.1** — REST layer, dependency injection, embedded Tomcat
- **Java 25**
- **SQLite** via [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) + Spring JDBC —
  local persistence, `DataSource`-backed (not JPA)
- **HikariCP** — connection pooling
- **Jackson** — JSON (de)serialization
- **Jikan API v4** — manga metadata and search, no API key required
- **JUnit 5 / MockMvc** — controller-layer testing

---

## Architecture

```
web/          → controllers, DTOs, exception handling — the only layer that speaks HTTP
service/      → business rules (e.g. new manga always start as PLAN_TO_READ)
db/           → MangaRepository — SQLite access via DataSource, shared logic with the TUI
model/        → Manga, Status — the domain objects, shared logic with the TUI
```

`model` and `db` were carried over from the TUI largely unchanged; the web and service
layers are new. The two projects currently maintain separate copies of the shared
classes — a deliberate tradeoff for learning Spring in isolation, planned to be resolved
by a shared `core` module later (see Roadmap).

---

## Roadmap

- **Pocket homelab deployment** — running this API natively under Termux on Android, so
  the same JSON API is reachable from a phone instead of only a dev machine
- **Multi-module restructure** — one parent Maven project with `core` (shared
  `model`/`db`), `tui`, and `api` modules, eliminating the current code duplication
  between this repo and manga-tracker

---

## Related

- [manga-tracker](https://github.com/roman11x/manga-tracker) — the original terminal
  app this API is built on top of

---

## License

MIT — see [LICENSE](LICENSE)