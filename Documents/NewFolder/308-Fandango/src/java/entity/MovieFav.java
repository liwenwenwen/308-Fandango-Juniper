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
@Table(name = "MovieFav")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieFav.findAll", query = "SELECT m FROM MovieFav m")
    , @NamedQuery(name = "MovieFav.findById", query = "SELECT m FROM MovieFav m WHERE m.id = :id")
    , @NamedQuery(name = "MovieFav.findByUserIdMovieId",query = "SELECT m FROM MovieFav m WHERE m.movieId.id =:movieId AND m.userId.id=:userId")
    , @NamedQuery(name = "MovieFav.findByUserId",query = "SELECT m FROM MovieFav m WHERE m.userId.id=:userId")
})
public class MovieFav implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private Account userId;
    @JoinColumn(name = "movieId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movie movieId;

    public MovieFav() {
    }

    public MovieFav(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getUserId() {
        return userId;
    }

    public void setUserId(Account userId) {
        this.userId = userId;
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
        if (!(object instanceof MovieFav)) {
            return false;
        }
        MovieFav other = (MovieFav) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MovieFav[ id=" + id + " ]";
    }
    
}
