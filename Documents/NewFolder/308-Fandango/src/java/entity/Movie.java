/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author liwenfan
 */
@Entity
@Table(name = "Movie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id")
    , @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title")
    , @NamedQuery(name = "Movie.findByDuration1", query = "SELECT m FROM Movie m WHERE m.duration1 = :duration1")
    , @NamedQuery(name = "Movie.findByDuration2", query = "SELECT m FROM Movie m WHERE m.duration2 = :duration2")
    , @NamedQuery(name = "Movie.findByCountry", query = "SELECT m FROM Movie m WHERE m.country = :country")
    , @NamedQuery(name = "Movie.findByCover", query = "SELECT m FROM Movie m WHERE m.cover = :cover")
    , @NamedQuery(name = "Movie.findByReleaseDate", query = "SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Movie.findByGenres1", query = "SELECT m FROM Movie m WHERE m.genres1 = :genres1")
    , @NamedQuery(name = "Movie.findByGenres2", query = "SELECT m FROM Movie m WHERE m.genres2 = :genres2")
    , @NamedQuery(name = "Movie.findByGenres3", query = "SELECT m FROM Movie m WHERE m.genres3 = :genres3")
    , @NamedQuery(name = "Movie.findByGenres4", query = "SELECT m FROM Movie m WHERE m.genres4 = :genres4")
    , @NamedQuery(name = "Movie.findByActor1", query = "SELECT m FROM Movie m WHERE m.actor1 = :actor1")
    , @NamedQuery(name = "Movie.findByActor2", query = "SELECT m FROM Movie m WHERE m.actor2 = :actor2")
    , @NamedQuery(name = "Movie.findByActor3", query = "SELECT m FROM Movie m WHERE m.actor3 = :actor3")
    , @NamedQuery(name = "Movie.findByActor4", query = "SELECT m FROM Movie m WHERE m.actor4 = :actor4")
    , @NamedQuery(name = "Movie.findByActor5", query = "SELECT m FROM Movie m WHERE m.actor5 = :actor5")
    , @NamedQuery(name = "Movie.findByActor6", query = "SELECT m FROM Movie m WHERE m.actor6 = :actor6")
    , @NamedQuery(name = "Movie.findByActor7", query = "SELECT m FROM Movie m WHERE m.actor7 = :actor7")
    , @NamedQuery(name = "Movie.findByActor8", query = "SELECT m FROM Movie m WHERE m.actor8 = :actor8")
    , @NamedQuery(name = "Movie.findByActor9", query = "SELECT m FROM Movie m WHERE m.actor9 = :actor9")
    , @NamedQuery(name = "Movie.findByActor10", query = "SELECT m FROM Movie m WHERE m.actor10 = :actor10")
    , @NamedQuery(name = "Movie.findByActor11", query = "SELECT m FROM Movie m WHERE m.actor11 = :actor11")
    , @NamedQuery(name = "Movie.findByActor12", query = "SELECT m FROM Movie m WHERE m.actor12 = :actor12")
    , @NamedQuery(name = "Movie.findByActor13", query = "SELECT m FROM Movie m WHERE m.actor13 = :actor13")
    , @NamedQuery(name = "Movie.findByActor14", query = "SELECT m FROM Movie m WHERE m.actor14 = :actor14")
    , @NamedQuery(name = "Movie.findByActor15", query = "SELECT m FROM Movie m WHERE m.actor15 = :actor15")
    , @NamedQuery(name = "Movie.findByImdbLink", query = "SELECT m FROM Movie m WHERE m.imdbLink = :imdbLink")
    , @NamedQuery(name = "Movie.findByLanguage", query = "SELECT m FROM Movie m WHERE m.language = :language")
    , @NamedQuery(name = "Movie.findByDirector", query = "SELECT m FROM Movie m WHERE m.director = :director")
    , @NamedQuery(name = "Movie.findByContentRating", query = "SELECT m FROM Movie m WHERE m.contentRating = :contentRating")
    , @NamedQuery(name = "Movie.findByImdbScore", query = "SELECT m FROM Movie m WHERE m.imdbScore = :imdbScore")})
public class Movie implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smovieId")
    private Collection<MovieShowings> movieShowingsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<MovieReviews> movieReviewsCollection;

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "duration1")
    private String duration1;
    @Column(name = "duration2")
    private String duration2;
    @Column(name = "country")
    private String country;
    @Column(name = "cover")
    private String cover;
    @Column(name = "releaseDate")
    private String releaseDate;
    @Column(name = "genres1")
    private String genres1;
    @Column(name = "genres2")
    private String genres2;
    @Column(name = "genres3")
    private String genres3;
    @Column(name = "genres4")
    private String genres4;
    @Column(name = "actor1")
    private String actor1;
    @Column(name = "actor2")
    private String actor2;
    @Column(name = "actor3")
    private String actor3;
    @Column(name = "actor4")
    private String actor4;
    @Column(name = "actor5")
    private String actor5;
    @Column(name = "actor6")
    private String actor6;
    @Column(name = "actor7")
    private String actor7;
    @Column(name = "actor8")
    private String actor8;
    @Column(name = "actor9")
    private String actor9;
    @Column(name = "actor10")
    private String actor10;
    @Column(name = "actor11")
    private String actor11;
    @Column(name = "actor12")
    private String actor12;
    @Column(name = "actor13")
    private String actor13;
    @Column(name = "actor14")
    private String actor14;
    @Column(name = "actor15")
    private String actor15;
    @Column(name = "imdbLink")
    private String imdbLink;
    @Column(name = "language")
    private String language;
    @Column(name = "director")
    private String director;
    @Column(name = "contentRating")
    private String contentRating;
    @Column(name = "imdbScore")
    private String imdbScore;

    public Movie() {
    }

    public Movie(Integer id) {
        this.id = id;
    }

    public Movie(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration1() {
        return duration1;
    }

    public void setDuration1(String duration1) {
        this.duration1 = duration1;
    }

    public String getDuration2() {
        return duration2;
    }

    public void setDuration2(String duration2) {
        this.duration2 = duration2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenres1() {
        return genres1;
    }

    public void setGenres1(String genres1) {
        this.genres1 = genres1;
    }

    public String getGenres2() {
        return genres2;
    }

    public void setGenres2(String genres2) {
        this.genres2 = genres2;
    }

    public String getGenres3() {
        return genres3;
    }

    public void setGenres3(String genres3) {
        this.genres3 = genres3;
    }

    public String getGenres4() {
        return genres4;
    }

    public void setGenres4(String genres4) {
        this.genres4 = genres4;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    public String getActor4() {
        return actor4;
    }

    public void setActor4(String actor4) {
        this.actor4 = actor4;
    }

    public String getActor5() {
        return actor5;
    }

    public void setActor5(String actor5) {
        this.actor5 = actor5;
    }

    public String getActor6() {
        return actor6;
    }

    public void setActor6(String actor6) {
        this.actor6 = actor6;
    }

    public String getActor7() {
        return actor7;
    }

    public void setActor7(String actor7) {
        this.actor7 = actor7;
    }

    public String getActor8() {
        return actor8;
    }

    public void setActor8(String actor8) {
        this.actor8 = actor8;
    }

    public String getActor9() {
        return actor9;
    }

    public void setActor9(String actor9) {
        this.actor9 = actor9;
    }

    public String getActor10() {
        return actor10;
    }

    public void setActor10(String actor10) {
        this.actor10 = actor10;
    }

    public String getActor11() {
        return actor11;
    }

    public void setActor11(String actor11) {
        this.actor11 = actor11;
    }

    public String getActor12() {
        return actor12;
    }

    public void setActor12(String actor12) {
        this.actor12 = actor12;
    }

    public String getActor13() {
        return actor13;
    }

    public void setActor13(String actor13) {
        this.actor13 = actor13;
    }

    public String getActor14() {
        return actor14;
    }

    public void setActor14(String actor14) {
        this.actor14 = actor14;
    }

    public String getActor15() {
        return actor15;
    }

    public void setActor15(String actor15) {
        this.actor15 = actor15;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(String imdbScore) {
        this.imdbScore = imdbScore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Movie[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<MovieReviews> getMovieReviewsCollection() {
        return movieReviewsCollection;
    }

    public void setMovieReviewsCollection(Collection<MovieReviews> movieReviewsCollection) {
        this.movieReviewsCollection = movieReviewsCollection;
    }

    @XmlTransient
    public Collection<MovieShowings> getMovieShowingsCollection() {
        return movieShowingsCollection;
    }

    public void setMovieShowingsCollection(Collection<MovieShowings> movieShowingsCollection) {
        this.movieShowingsCollection = movieShowingsCollection;
    }

    
}
