/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liwenfan
 */
@Entity
@Table(name = "Genres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genres.findAll", query = "SELECT g FROM Genres g")
    , @NamedQuery(name = "Genres.findById", query = "SELECT g FROM Genres g WHERE g.id = :id")
    , @NamedQuery(name = "Genres.findByMovieId", query = "SELECT g FROM Genres g WHERE g.movieId.id = :movieId")
    , @NamedQuery(name = "Genres.findByGenreName", query = "SELECT g FROM Genres g WHERE g.genreId.genreName = :genreName AND g.movieId.releaseDate >=:from AND g.movieId.releaseDate <=:to order by g.movieId.releaseDate desc")})
public class Genres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "genreId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GenreNames genreId;
    @JoinColumn(name = "movieId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movie movieId;

    public Genres() {
    }

    public Genres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GenreNames getGenreId() {
        return genreId;
    }

    public void setGenreId(GenreNames genreId) {
        this.genreId = genreId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
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
        if (!(object instanceof Genres)) {
            return false;
        }
        Genres other = (Genres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Genres[ id=" + id + " ]";
    }
    
}
