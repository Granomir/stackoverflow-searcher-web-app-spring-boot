package com.patrushev.stackoverflowsearcherwebappspringboot.model;

import java.util.Objects;

public class QuestionResult {
    private String date;
    private String title;
    private String author;
    private String reference;
    private boolean solved;

    public QuestionResult() {
    }

    public QuestionResult(String date, String title, String author, String reference, boolean Solved) {
        this.date = date;
        this.title = title;
        this.author = author;
        this.reference = reference;
        this.solved = Solved;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionResult that = (QuestionResult) o;
        return solved == that.solved &&
                Objects.equals(date, that.date) &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, title, author, reference, solved);
    }

    @Override
    public String toString() {
        return "QuestionResult{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", reference='" + reference + '\'' +
                ", solved=" + solved +
                '}';
    }
}
