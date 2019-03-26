/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.candidat;

import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EliteBook
 */
@Entity
@Table
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String position;
    
    private String orginization;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    private Date endDate;

    @ManyToOne
    private Candidate candidateExp;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidate getCandidateExp() {
        return candidateExp;
    }

    public void setCandidateExp(Candidate candidateExp) {
        this.candidateExp = candidateExp;
    }

    
    
}