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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liwenfan
 */
@Entity
@Table(name = "Payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p")
    , @NamedQuery(name = "Payments.findById", query = "SELECT p FROM Payments p WHERE p.id = :id")
    , @NamedQuery(name = "Payments.findByCardNum", query = "SELECT p FROM Payments p WHERE p.cardNum = :cardNum")
    , @NamedQuery(name = "Payments.findByExpirationMonth", query = "SELECT p FROM Payments p WHERE p.expirationMonth = :expirationMonth")
    , @NamedQuery(name = "Payments.findByExpirationYear", query = "SELECT p FROM Payments p WHERE p.expirationYear = :expirationYear")
    , @NamedQuery(name = "Payments.findByFirstName", query = "SELECT p FROM Payments p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Payments.findByLastName", query = "SELECT p FROM Payments p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Payments.findByBillingZip", query = "SELECT p FROM Payments p WHERE p.billingZip = :billingZip")})
public class Payments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cardNum")
    private String cardNum;
    @Basic(optional = false)
    @Column(name = "expirationMonth")
    private String expirationMonth;
    @Column(name = "expirationYear")
    private String expirationYear;
    @Basic(optional = false)
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "billingZip")
    private String billingZip;
    @OneToOne(mappedBy = "paymentId")
    private Account account;

    public Payments() {
    }

    public Payments(Integer id) {
        this.id = id;
    }

    public Payments(Integer id, String cardNum, String expirationMonth, String firstName) {
        this.id = id;
        this.cardNum = cardNum;
        this.expirationMonth = expirationMonth;
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Payments[ id=" + id + " ]";
    }
    
}
