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
@Table(name = "TheaterFav")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TheaterFav.findAll", query = "SELECT t FROM TheaterFav t")
    , @NamedQuery(name = "TheaterFav.findById", query = "SELECT t FROM TheaterFav t WHERE t.id = :id")
    , @NamedQuery(name = "TheaterFav.findByUserId", query = "SELECT t FROM TheaterFav t WHERE t.userId.id = :userId")
    , @NamedQuery(name = "TheaterFav.findByUserIdTheaterId", query = "SELECT t FROM TheaterFav t WHERE t.userId.id = :userId AND t.theaterId.id = :theaterId")})
public class TheaterFav implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "theaterId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Theaters theaterId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private Account userId;

    public TheaterFav() {
    }

    public TheaterFav(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Theaters getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Theaters theaterId) {
        this.theaterId = theaterId;
    }

    public Account getUserId() {
        return userId;
    }

    public void setUserId(Account userId) {
        this.userId = userId;
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
        if (!(object instanceof TheaterFav)) {
            return false;
        }
        TheaterFav other = (TheaterFav) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TheaterFav[ id=" + id + " ]";
    }
    
}
