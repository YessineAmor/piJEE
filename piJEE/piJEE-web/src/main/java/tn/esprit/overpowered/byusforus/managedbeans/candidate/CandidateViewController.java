/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.candidate;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote;
import util.authentication.Authenticator;

/**
 *
 * @author EliteBook
 */
@ManagedBean
@SessionScoped
public class CandidateViewController implements Serializable {
    private Candidate cdt ;
    private String lastName;
    private String email;
    private String recommendations;
    private String firstName;
    @EJB
    CandidateFacadeRemote cdtFacade ;
    
    public List<Candidate> getCandidates()
    {
        return cdtFacade.afficherCandidats();
        
    }
    
    public String cdtConnected()
    {
        cdt = cdtFacade.findCandidate(Authenticator.currentSession.getUser().getId());
        return "/views/candidate/profile?faces-redirect=true";
    }
    
    public void recommendCandidate()
    {
        cdtFacade.recommend(cdt.getId());
    }
    
    public void incrementVisits()
    {
        cdtFacade.incrementVisits(cdt.getId());
    }
    
    public void recommend()
    {
        cdtFacade.recommend(cdt.getId());
    }
    
    public Candidate getCdt() {
        return cdt;
    }

    public void setCdt(Candidate cdt) {
        this.cdt = cdt;
    }

    public CandidateFacadeRemote getCdtFacade() {
        return cdtFacade;
    }

    public void setCdtFacade(CandidateFacadeRemote cdtFacade) {
        this.cdtFacade = cdtFacade;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
}
