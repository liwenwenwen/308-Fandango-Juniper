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
@Table(name = "MovieReviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieReviews.findAll", query = "SELECT m FROM MovieReviews m")
    , @NamedQuery(name = "MovieReviews.findById", query = "SELECT m FROM MovieReviews m WHERE m.id = :id")
    , @NamedQuery(name = "MovieReviews.findByTitle", query = "SELECT m FROM MovieReviews m WHERE m.title = :title")
    , @NamedQuery(name = "MovieReviews.findByDate", query = "SELECT m FROM MovieReviews m WHERE m.date = :date")
    , @NamedQuery(name = "MovieReviews.findByBody", query = "SELECT m FROM MovieReviews m WHERE m.body = :body")
    , @NamedQuery(name = "MovieReviews.findByUserName", query = "SELECT m FROM MovieReviews m WHERE m.userName = :userName")
    , @NamedQuery(name = "MovieReviews.findByUserIdMovieId", query = "SELECT m FROM MovieReviews m WHERE m.movieId.id =:movieId AND m.userId.id=:userId")
})
public class MovieReviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "date")
    private String date;
    @Column(name = "body")
    private String body;
    @Column(name = "userName")
    private String userName;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account userId;
    @JoinColumn(name = "movieId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movie movieId;

    public MovieReviews() {
    }

    public MovieReviews(Integer id) {
        this.id = id;
    }

    public MovieReviews(Integer id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (!(object instanceof MovieReviews)) {
            return false;
        }
        MovieReviews other = (MovieReviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MovieReviews[ id=" + id + " ]";
    }
    
}
