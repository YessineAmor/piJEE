/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.candidat;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author EliteBook
 */
@Entity
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private String introduction;
    @Id
    private int id;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
    
    @OneToMany(mappedBy = "candidateExp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Experience> experiences;
    
    @OneToMany(mappedBy = "candidateSub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;
    
    @OneToMany(mappedBy = "candidateActivity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Activity> activities;
    
    @OneToMany(mappedBy = "candidateCertif", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Certificate> certificates;
    
    @OneToMany(mappedBy = "candidateCV", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CurriculumVitae> curriculumVitaes;
    
    @OneToMany(mappedBy = "candidateCursus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cursus> cursus;
    
    @OneToMany(mappedBy = "candidateSkill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skills;
    
    @OneToMany(mappedBy = "candidateContact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts;
    
    @OneToMany(mappedBy = "candidateVisit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visit> visits;

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<CurriculumVitae> getCurriculumVitaes() {
        return curriculumVitaes;
    }

    public void setCurriculumVitaes(List<CurriculumVitae> curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }

    public List<Cursus> getCursus() {
        return cursus;
    }

    public void setCursus(List<Cursus> cursus) {
        this.cursus = cursus;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }


    
    
    
    
    
    
    
    
    
    
    
}
