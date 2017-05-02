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
@Table(name = "Theaters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Theaters.findAll", query = "SELECT t FROM Theaters t")
    , @NamedQuery(name = "Theaters.findById", query = "SELECT t FROM Theaters t WHERE t.id = :id")
    , @NamedQuery(name = "Theaters.findByName", query = "SELECT t FROM Theaters t WHERE t.name = :name")
    , @NamedQuery(name = "Theaters.findByAddrStreet", query = "SELECT t FROM Theaters t WHERE t.addrStreet = :addrStreet")
    , @NamedQuery(name = "Theaters.findByAddrCity", query = "SELECT t FROM Theaters t WHERE t.addrCity = :addrCity")
    , @NamedQuery(name = "Theaters.findByAddrState", query = "SELECT t FROM Theaters t WHERE t.addrState = :addrState")
    , @NamedQuery(name = "Theaters.findByAddrZipcode", query = "SELECT t FROM Theaters t WHERE t.addrZipcode = :addrZipcode")
    , @NamedQuery(name = "Theaters.findByIconImage", query = "SELECT t FROM Theaters t WHERE t.iconImage = :iconImage")
    , @NamedQuery(name = "Theaters.findByPhoneNum", query = "SELECT t FROM Theaters t WHERE t.phoneNum = :phoneNum")})
public class Theaters implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theaterId")
    private Collection<TheaterFav> theaterFavCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "addr_street")
    private String addrStreet;
    @Basic(optional = false)
    @Column(name = "addr_city")
    private String addrCity;
    @Basic(optional = false)
    @Column(name = "addr_state")
    private String addrState;
    @Column(name = "addr_zipcode")
    private String addrZipcode;
    @Column(name = "iconImage")
    private String iconImage;
    @Column(name = "phoneNum")
    private String phoneNum;

    public Theaters() {
    }

    public Theaters(Integer id) {
        this.id = id;
    }

    public Theaters(Integer id, String addrStreet, String addrCity, String addrState) {
        this.id = id;
        this.addrStreet = addrStreet;
        this.addrCity = addrCity;
        this.addrState = addrState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrState() {
        return addrState;
    }

    public void setAddrState(String addrState) {
        this.addrState = addrState;
    }

    public String getAddrZipcode() {
        return addrZipcode;
    }

    public void setAddrZipcode(String addrZipcode) {
        this.addrZipcode = addrZipcode;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
        if (!(object instanceof Theaters)) {
            return false;
        }
        Theaters other = (Theaters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Theaters[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TheaterFav> getTheaterFavCollection() {
        return theaterFavCollection;
    }

    public void setTheaterFavCollection(Collection<TheaterFav> theaterFavCollection) {
        this.theaterFavCollection = theaterFavCollection;
    }
    
}
