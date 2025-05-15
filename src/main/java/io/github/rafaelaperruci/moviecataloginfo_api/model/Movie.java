package io.github.rafaelaperruci.moviecataloginfo_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private String id;
    private String title;

    @Column(name = "overview_resume")
    private String overviewResume;

    @Column(name = "release_date")
    private String releaseDate;
    private Integer rating;


    public Movie() {}
    public Movie(String title, String id, String overviewResume, String releaseDate, Integer rating) {
        this.title = title;
        this.id = id;
        this.overviewResume = overviewResume;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverviewResume() {
        return overviewResume;
    }

    public void setOverviewResume(String overviewResume) {
        this.overviewResume = overviewResume;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
