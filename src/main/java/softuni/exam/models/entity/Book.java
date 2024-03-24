package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column (nullable = false)
    private String author;

    @Column (nullable = false)
    private boolean available;

    @Column (nullable = false, columnDefinition = "TEXT")
    private String description;
    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private Genre genre;
    @Column (nullable = false)
    private double rating;
    @Column (nullable = false, unique = true)
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
