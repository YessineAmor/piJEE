/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Yassine
 */
@Entity
public class QuizTry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuizTry;
    @ManyToOne
    private Quiz quiz;
    private float percentage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @OneToMany
    List<Answer> answers;
    public QuizTry() {
    }
    
    

    public Long getIdQuizTry() {
        return idQuizTry;
    }

    public void setIdQuizTry(Long idQuizTry) {
        this.idQuizTry = idQuizTry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuizTry != null ? idQuizTry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idQuizTry fields are not set
        if (!(object instanceof QuizTry)) {
            return false;
        }
        QuizTry other = (QuizTry) object;
        if ((this.idQuizTry == null && other.idQuizTry != null) || (this.idQuizTry != null && !this.idQuizTry.equals(other.idQuizTry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.QuizTry[ id=" + idQuizTry + " ]";
    }
    
}
