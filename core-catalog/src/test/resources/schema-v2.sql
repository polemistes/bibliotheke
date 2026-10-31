-- SPDX-License-Identifier: GPL-3.0-or-later
-- Catalog schema_version 2 as the pipeline writes it (PLAN.md 5.3), dumped from pipeline/out/no.sqlite.
CREATE TABLE meta(key TEXT PRIMARY KEY, value TEXT);
CREATE TABLE works(
  id INTEGER PRIMARY KEY,
  page_title TEXT NOT NULL UNIQUE,
  display_title TEXT NOT NULL,
  sort_title TEXT NOT NULL,
  qid TEXT,
  work_qid TEXT,
  year INTEGER, year_precision INTEGER, year_source TEXT,
  language TEXT,
  type TEXT,
  structure TEXT NOT NULL,
  is_virtual INTEGER NOT NULL DEFAULT 0,
  entry_title TEXT,
  part_count INTEGER NOT NULL,
  text_count INTEGER NOT NULL,
  missing_part_count INTEGER NOT NULL DEFAULT 0,
  parts_in_catalog INTEGER NOT NULL DEFAULT 1,
  oversized INTEGER NOT NULL DEFAULT 0,
  bytes_total INTEGER,
  index_titles TEXT,
  quality TEXT NOT NULL,
  quality_source TEXT,
  quality_pages TEXT,
  complete INTEGER NOT NULL,
  incomplete_reason TEXT,
  missing_page_count INTEGER NOT NULL DEFAULT 0,
  coverage REAL,
  own_translation INTEGER NOT NULL DEFAULT 0,
  description TEXT,
  cover_url TEXT,
  rev_id INTEGER, touched TEXT,
  author_source TEXT, language_source TEXT, genre_source TEXT, type_source TEXT
);
CREATE TABLE parts(
  id INTEGER PRIMARY KEY,
  work_id INTEGER NOT NULL, ord INTEGER NOT NULL,
  depth INTEGER NOT NULL, parent_ord INTEGER,
  page_title TEXT NOT NULL,
  display_title TEXT,
  kind TEXT NOT NULL,
  searchable INTEGER NOT NULL DEFAULT 0,
  bytes INTEGER, scan_from INTEGER, scan_to INTEGER,
  index_ref INTEGER,
  quality TEXT, quality_pages TEXT, complete INTEGER,
  UNIQUE(work_id, ord));
CREATE TABLE aliases(alias_title TEXT PRIMARY KEY, work_id INTEGER NOT NULL, ord INTEGER);
CREATE TABLE authors(id INTEGER PRIMARY KEY, name TEXT NOT NULL, sort_name TEXT NOT NULL,
  qid TEXT, birth_year INTEGER, death_year INTEGER, author_page_title TEXT);
CREATE TABLE work_authors(work_id INTEGER, author_id INTEGER, role TEXT, position INTEGER,
  PRIMARY KEY(work_id, author_id, role));
CREATE TABLE genres(id INTEGER PRIMARY KEY, key TEXT UNIQUE);
CREATE TABLE work_genres(work_id INTEGER, genre_id INTEGER, PRIMARY KEY(work_id, genre_id));
CREATE TABLE excluded(page_title TEXT PRIMARY KEY, reason TEXT);
CREATE INDEX parts_title ON parts(page_title);
CREATE INDEX works_year ON works(year);
CREATE INDEX works_type ON works(type);
CREATE INDEX works_quality ON works(quality);
CREATE INDEX works_sort_title ON works(sort_title);
CREATE INDEX work_genres_genre ON work_genres(genre_id);
CREATE INDEX work_authors_author ON work_authors(author_id);
CREATE VIRTUAL TABLE parts_fts USING fts5(display_title, work_title, author_names, content='',
  tokenize='unicode61 remove_diacritics 2');
CREATE VIRTUAL TABLE works_fts USING fts5(display_title, author_names, content='',
  tokenize='unicode61 remove_diacritics 2');
