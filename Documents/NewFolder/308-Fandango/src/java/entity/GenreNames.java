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
@Table(name = "GenreNames")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenreNames.findAll", query = "SELECT g FROM GenreNames g")
    , @NamedQuery(name = "GenreNames.findById", query = "SELECT g FROM GenreNames g WHERE g.id = :id")
    , @NamedQuery(name = "GenreNames.findByGenreName", query = "SELECT g FROM GenreNames g WHERE g.genreName = :genreName")
    , @NamedQuery(name = "GenreNames.findByFrequency", query = "SELECT g FROM GenreNames g WHERE g.frequency = :frequency")})
public class GenreNames implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genreId")
    private Collection<Genres> genresCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "genreName")
    private String genreName;
    @Column(name = "Frequency")
    private Integer frequency;

    public GenreNames() {
    }

    public GenreNames(Integer id) {
        this.id = id;
    }

    public GenreNames(Integer id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
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
        if (!(object instanceof GenreNames)) {
            return false;
        }
        GenreNames other = (GenreNames) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GenreNames[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Genres> getGenresCollection() {
        return genresCollection;
    }

    public void setGenresCollection(Collection<Genres> genresCollection) {
        this.genresCollection = genresCollection;
    }
    
}
