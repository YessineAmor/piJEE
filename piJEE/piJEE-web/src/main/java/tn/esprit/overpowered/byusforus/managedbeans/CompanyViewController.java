/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.services.users.CompanyProfileFacadeLocal;

/**
 *
 * @author EliteBook
 */
@ManagedBean
@SessionScoped
public class CompanyViewController implements Serializable {
    private CompanyProfile selectedCompany;
    
    @EJB
    private CompanyProfileFacadeLocal compFacade;
    
    public List<CompanyProfile> listComp()
    {
        return compFacade.findAll();
    }

    public CompanyProfile getSelectedCompanies() {
        return selectedCompany;
    }

    public void setSelectedCompanies(CompanyProfile selectedCompanies) {
        this.selectedCompany = selectedCompanies;
    }

    public CompanyProfileFacadeLocal getCompFacade() {
        return compFacade;
    }

    public void setCompFacade(CompanyProfileFacadeLocal compFacade) {
        this.compFacade = compFacade;
    }
    
}
