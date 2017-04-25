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
@Table(name = "Orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id")
    , @NamedQuery(name = "Orders.findByEmail", query = "SELECT o FROM Orders o WHERE o.email = :email")
    , @NamedQuery(name = "Orders.findByCreateDate", query = "SELECT o FROM Orders o WHERE o.createDate = :createDate")
    , @NamedQuery(name = "Orders.findByNumTickets", query = "SELECT o FROM Orders o WHERE o.numTickets = :numTickets")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "createDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "numTickets")
    private int numTickets;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private Account userId;
    @JoinColumn(name = "scheduleId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MovieSchedules scheduleId;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, String email, Date createDate, int numTickets) {
        this.id = id;
        this.email = email;
        this.createDate = createDate;
        this.numTickets = numTickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public Account getUserId() {
        return userId;
    }

    public void setUserId(Account userId) {
        this.userId = userId;
    }

    public MovieSchedules getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(MovieSchedules scheduleId) {
        this.scheduleId = scheduleId;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Orders[ id=" + id + " ]";
    }
    
}
