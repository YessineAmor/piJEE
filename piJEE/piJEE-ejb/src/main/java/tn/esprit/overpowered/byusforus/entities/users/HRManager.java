/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "HUMAN_RESOURCES_MANAGER")
public class HRManager extends Employee implements Serializable {

    @ManyToMany
    private List<JobOffer> jobOffers;

    
    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
