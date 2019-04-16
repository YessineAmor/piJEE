/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.candidat;

import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author EliteBook
 */
@Entity
@Table
public class Certificate implements Serializable {

    private static final long serialVersionUID = 9L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    
    private String organization;
    
    private String issueDate;
    
    private String expirationDate;
    
    private boolean isExpirable;

    @ManyToOne
    private Candidate candidateCertif;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidate getCandidateCertif() {
        return candidateCertif;
    }

    public void setCandidateCertif(Candidate candidateCertif) {
        this.candidateCertif = candidateCertif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isIsExpirable() {
        return isExpirable;
    }

    public void setIsExpirable(boolean isExpirable) {
        this.isExpirable = isExpirable;
    }
    
    
    
}