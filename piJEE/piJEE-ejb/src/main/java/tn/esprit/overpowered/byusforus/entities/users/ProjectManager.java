/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "PROJECT_MANAGER")
public class ProjectManager extends Employee implements Serializable {

    private static final long serialVersionUID = 33L;

    @ManyToOne
    private CompanyProfile companyProfile;

    @OneToMany(mappedBy = "pManager", cascade = {PERSIST, MERGE})
    private Set<JobOffer> managerOffers;

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public Set<JobOffer> getManagerOffers() {
        return managerOffers;
    }

    public void setManagerOffers(Set<JobOffer> managerOffers) {
        this.managerOffers = managerOffers;
    }
    
    

    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
