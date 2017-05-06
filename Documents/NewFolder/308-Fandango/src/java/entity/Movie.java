/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import static source.Constants.CHECKOUT_TIME_FORMAT;

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
    , @NamedQuery(name = "Movie.findByDuration", query = "SELECT m FROM Movie m WHERE m.duration = :duration")
    , @NamedQuery(name = "Movie.findByCover", query = "SELECT m FROM Movie m WHERE m.cover = :cover")
    , @NamedQuery(name = "Movie.findByReleaseDate", query = "SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Movie.findByContentRating", query = "SELECT m FROM Movie m WHERE m.contentRating = :contentRating")
    , @NamedQuery(name = "Movie.findBySynopsis", query = "SELECT m FROM Movie m WHERE m.synopsis = :synopsis")
    , @NamedQuery(name = "Movie.findByTwoDates", query = "SELECT m FROM Movie m WHERE m.releaseDate >=:from AND m.releaseDate <=:to order by m.releaseDate desc")
    , @NamedQuery(name = "Movie.findByTwoDatesNew", query = "SELECT m FROM Movie m WHERE m.releaseDate >=:from AND m.releaseDate <=:to order by m.releaseDate asc")
    , @NamedQuery(name = "Movie.findByTitleDuration", query = "SELECT m FROM Movie m WHERE m.title = :title AND m.duration = :duration")
    })
public class Movie implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<Genres> genresCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private String duration;
    @Column(name = "cover")
    private String cover;
    @Column(name = "releaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "contentRating")
    private String contentRating;
    @Column(name = "synopsis")
    private String synopsis;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
    public Collection<Genres> getGenresCollection() {
        return genresCollection;
    }

    public void setGenresCollection(Collection<Genres> genresCollection) {
        this.genresCollection = genresCollection;
    }
    
}
