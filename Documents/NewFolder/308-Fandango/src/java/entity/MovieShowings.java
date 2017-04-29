/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author liwenfan
 */
@Entity
@Table(name = "MovieShowings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieShowings.findAll", query = "SELECT m FROM MovieShowings m")
    , @NamedQuery(name = "MovieShowings.findById", query = "SELECT m FROM MovieShowings m WHERE m.id = :id")
    , @NamedQuery(name = "MovieShowings.findByUnitPrice", query = "SELECT m FROM MovieShowings m WHERE m.unitPrice = :unitPrice")
    , @NamedQuery(name = "MovieShowings.findByPeriodStart", query = "SELECT m FROM MovieShowings m WHERE m.periodStart = :periodStart")
    , @NamedQuery(name = "MovieShowings.findByPeriodEnd", query = "SELECT m FROM MovieShowings m WHERE m.periodEnd = :periodEnd")
    , @NamedQuery(name = "MovieShowings.findByMovieIdTheaterId", query = "SELECT m FROM MovieShowings m WHERE m.smovieId.id=:movieId AND m.stheaterId.id=:theaterId")
    , @NamedQuery(name = "MovieShowings.findByTwoIDs", query = "SELECT m FROM MovieShowings m WHERE m.id >=:min AND m.id <=:max")})
public class MovieShowings implements Serializable {

    @OneToMany(mappedBy = "showingId")
    private Collection<MovieSchedules> movieSchedulesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "unitPrice")
    private Long unitPrice;
    @Basic(optional = false)
    @Column(name = "periodStart")
    @Temporal(TemporalType.DATE)
    private Date periodStart;
    @Column(name = "periodEnd")
    @Temporal(TemporalType.DATE)
    private Date periodEnd;
    @JoinColumn(name = "s_movieId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movie smovieId;
    @JoinColumn(name = "s_theaterId", referencedColumnName = "id")
    @ManyToOne
    private Theaters stheaterId;

    public MovieShowings() {
    }

    public MovieShowings(Integer id) {
        this.id = id;
    }

    public MovieShowings(Integer id, Date periodStart) {
        this.id = id;
        this.periodStart = periodStart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Movie getSmovieId() {
        return smovieId;
    }

    public void setSmovieId(Movie smovieId) {
        this.smovieId = smovieId;
    }

    public Theaters getStheaterId() {
        return stheaterId;
    }

    public void setStheaterId(Theaters stheaterId) {
        this.stheaterId = stheaterId;
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
        if (!(object instanceof MovieShowings)) {
            return false;
        }
        MovieShowings other = (MovieShowings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MovieShowings[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<MovieSchedules> getMovieSchedulesCollection() {
        return movieSchedulesCollection;
    }

    public void setMovieSchedulesCollection(Collection<MovieSchedules> movieSchedulesCollection) {
        this.movieSchedulesCollection = movieSchedulesCollection;
    }
    
}
