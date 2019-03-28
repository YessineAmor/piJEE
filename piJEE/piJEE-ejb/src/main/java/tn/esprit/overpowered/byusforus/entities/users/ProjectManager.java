/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "PROJECT_MANAGER")
public class ProjectManager extends Employee implements Serializable {

    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
