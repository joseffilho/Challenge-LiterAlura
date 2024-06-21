package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Resultado {

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<Autor> authors;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("download_count")
    private String download_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Autor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Autor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getDownload_count() {
        return download_count;
    }

    public void setDownload_count(String download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", languages=" + languages +
                ", download_count='" + download_count;
    }
}
