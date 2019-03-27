/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "COMPANY_ADMIN")
public class CompanyAdmin extends Employee implements Serializable {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COMP_ADMIN_ID")
    private CompanyProfile companyProfile;

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }
    
    

}
