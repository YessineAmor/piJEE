/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.candidat.Experience;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;

/**
 *
 * @author EliteBook
 */
@Remote
public interface CandidateFacadeRemote {

    void create(Candidate candidate);

    void edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    List<Candidate> findAll();

    List<Candidate> findRange(int[] range);

    int count();
    
    public Candidate searchByName(String name);
    
    public Candidate searchByLastname(String lastname);
    
    public List<Candidate> searchByPosition(String position);
    
    public Long addContact(Long candidateId);
    
    public CompanyProfile searchCompany(String companyName);
    
    public Long subscribe(Long companyId, Long candidateId);
    
    public void affecterExperienceCandidate(Long expId,  Long candidateId);
    
    public List<JobOffer> customJobOfferList(Long candidateId);
            
    
    
    
   
}
