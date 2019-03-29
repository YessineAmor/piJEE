/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "EMPLOYEE")
public class Employee extends Candidate implements Serializable {


    @ManyToOne
    @JoinTable(name = "COMPANY_EMPLOYEES")
    CompanyProfile company;
    
    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
        
        
    }

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
    }
}
