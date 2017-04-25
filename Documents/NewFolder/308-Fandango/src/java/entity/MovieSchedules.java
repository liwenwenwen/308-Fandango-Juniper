/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liwenfan
 */
@Entity
@Table(name = "MovieSchedules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieSchedules.findAll", query = "SELECT m FROM MovieSchedules m")
    , @NamedQuery(name = "MovieSchedules.findById", query = "SELECT m FROM MovieSchedules m WHERE m.id = :id")
    , @NamedQuery(name = "MovieSchedules.findByTime", query = "SELECT m FROM MovieSchedules m WHERE m.time = :time")
    , @NamedQuery(name = "MovieSchedules.findByNumTicketsLeft", query = "SELECT m FROM MovieSchedules m WHERE m.numTicketsLeft = :numTicketsLeft")
    , @NamedQuery(name = "MovieSchedules.findByDate", query = "SELECT m FROM MovieSchedules m WHERE m.date = :date")
    , @NamedQuery(name = "MovieSchedules.findByMovieShwoingIdDate", query = "SELECT m FROM MovieSchedules m WHERE m.showingId.id=:showingId AND m.date=:date")})
public class MovieSchedules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "time")
    private String time;
    @Basic(optional = false)
    @Column(name = "numTicketsLeft")
    private int numTicketsLeft;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "showingId", referencedColumnName = "id")
    @ManyToOne
    private MovieShowings showingId;

    public MovieSchedules() {
    }

    public MovieSchedules(Integer id) {
        this.id = id;
    }

    public MovieSchedules(Integer id, String time, int numTicketsLeft) {
        this.id = id;
        this.time = time;
        this.numTicketsLeft = numTicketsLeft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumTicketsLeft() {
        return numTicketsLeft;
    }

    public void setNumTicketsLeft(int numTicketsLeft) {
        this.numTicketsLeft = numTicketsLeft;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MovieShowings getShowingId() {
        return showingId;
    }

    public void setShowingId(MovieShowings showingId) {
        this.showingId = showingId;
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
        if (!(object instanceof MovieSchedules)) {
            return false;
        }
        MovieSchedules other = (MovieSchedules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MovieSchedules[ id=" + id + " ]";
    }
    
}
