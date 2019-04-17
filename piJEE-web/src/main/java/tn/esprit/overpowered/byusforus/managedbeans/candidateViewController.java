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
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote;

/**
 *
 * @author EliteBook
 */
@ManagedBean
@SessionScoped
public class candidateViewController implements Serializable {
    private Candidate cdt ;
    @EJB
    CandidateFacadeRemote cdtFacade ;
    
    public List<Candidate> getCandidates()
    {
        return cdtFacade.afficherCandidats();
    }
    
}
