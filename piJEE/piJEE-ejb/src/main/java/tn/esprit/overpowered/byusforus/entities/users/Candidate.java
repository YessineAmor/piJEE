/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import tn.esprit.overpowered.byusforus.entities.candidat.Certificate;
import tn.esprit.overpowered.byusforus.entities.candidat.Cursus;
import tn.esprit.overpowered.byusforus.entities.candidat.Experience;


/**
 *
 * @author EliteBook
 */
@Entity
@DiscriminatorValue(value = "CANDIDATE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CANDIDATE_TYPE")
public class Candidate extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private int recommendations;
    
    @OneToMany(mappedBy="candidate")
    private List<Experience> experiences;
    /*
    @OneToMany(mappedBy = "candidateSub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompanyProfile> subscriptions;
    */
    @ElementCollection(targetClass=String.class)
    private List<String> activities;

    @OneToMany(mappedBy = "candidateCertif", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Certificate> certificates;

    private String curriculumVitaes;

    @OneToMany(mappedBy = "candidateCursus")
    private List<Cursus> cursus;

    @ManyToMany
    private Set<Candidate> contacts;

    private int visits;

    public int getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
    /*
    public List<CompanyProfile> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<CompanyProfile> subscriptions) {
        this.subscriptions = subscriptions;
    }
    */
    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public String getCurriculumVitaes() {
        return curriculumVitaes;
    }

    public void setCurriculumVitaes(String curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }

    public List<Cursus> getCursus() {
        return cursus;
    }

    public void setCursus(List<Cursus> cursus) {
        this.cursus = cursus;
    }
    

    public Set<Candidate> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Candidate> contacts) {
        this.contacts = contacts;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

}