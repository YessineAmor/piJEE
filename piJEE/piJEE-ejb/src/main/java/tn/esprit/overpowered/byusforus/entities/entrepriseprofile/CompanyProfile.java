/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.entrepriseprofile;

import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.CompanyAdmin;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author pc
 */
@Entity
public class CompanyProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "CP_ID")
    private Long id;

    private String picName;
    private int numViews;
    private String summary;
    private String sectorOfActivity;
    private String website;
    private String companySize;
    private int dateOfCreation;
    
    @OneToOne
    @JoinColumn(name = "FK_CA_ID")
    private CompanyAdmin companyAdmin;

    @OneToMany
    @JoinTable(name = "C_CP_JB",joinColumns={@JoinColumn(name = "CP_ID")},inverseJoinColumns={@JoinColumn(name = "JO_ID")})
    private List<JobOffer> listOfOffers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSectorOfActivity() {
        return sectorOfActivity;
    }

    public void setSectorOfActivity(String sectorOfActivity) {
        this.sectorOfActivity = sectorOfActivity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public int getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(int dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
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
        if (!(object instanceof CompanyProfile)) {
            return false;
        }
        CompanyProfile other = (CompanyProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.CompanyProfile[ id=" + id + " ]";
    }

}
