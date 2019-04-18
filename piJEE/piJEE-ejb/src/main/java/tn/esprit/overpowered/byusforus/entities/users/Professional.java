/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
public abstract class Professional extends User {
     
    private String introduction;

    @ElementCollection
    private List<Long> recommendedIdList;
    
    private int recommendations;
    
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    
    
    @ElementCollection(targetClass = String.class)
    private List<String> experiences;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscriptions", joinColumns
            = {
                @JoinColumn(name = "candidate_id")}, inverseJoinColumns
            = {
                @JoinColumn(name = "company_id")})
    private List<CompanyProfile> subscribedCompanies;

    @ElementCollection(targetClass = String.class)

    private List<String> activities;

   @ElementCollection(targetClass = String.class)
    private List<String> certificates;

    private String curriculumVitaes;

    @ElementCollection(targetClass = String.class)
    private List<String> cursus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="contacts",
                joinColumns={           @JoinColumn(name="current_id")},
                inverseJoinColumns={    @JoinColumn(name="contact_id")})
    private List<Candidate> contacts = new ArrayList<Candidate>();;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<JobOffer> registeredOffers;
 
    @ElementCollection(targetClass = Skill.class)
    @JoinTable(name = "T_CANDIDATE_Skills")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Set<Skill> skills;

    private int visits;

    public Professional() {
        this.skills = new HashSet<>();
        this.recommendedIdList = new ArrayList<>();
    }

    public int getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }


    public List<CompanyProfile> getSubscribedCompanies() {
        return subscribedCompanies;
    }

    public void setSubscribedCompanies(List<CompanyProfile> subscribedCompanies) {
        this.subscribedCompanies = subscribedCompanies;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public String getCurriculumVitaes() {
        return curriculumVitaes;
    }

    public void setCurriculumVitaes(String curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }


    public List<Candidate> getContacts() {
        return contacts;
    }

    public void setContacts(List<Candidate> contacts) {
        this.contacts = contacts;
    }

    public List<JobOffer> getRegisteredOffers() {
        return registeredOffers;
    }

    public void setRegisteredOffers(List<JobOffer> registeredOffers) {
        this.registeredOffers = registeredOffers;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public List<Long> getRecommendedIdList() {
        return recommendedIdList;
    }

    public void setRecommendedIdList(List<Long> recommendedIdList) {
        this.recommendedIdList = recommendedIdList;
    }

    public List<String> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<String> experiences) {
        this.experiences = experiences;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public List<String> getCursus() {
        return cursus;
    }

    public void setCursus(List<String> cursus) {
        this.cursus = cursus;
    }
    
}
