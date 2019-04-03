/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.candidat.CurriculumVitae;
import tn.esprit.overpowered.byusforus.entities.candidat.Cursus;
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
    
    public List<Candidate> searchByName(String name);
    
    public List<Candidate> searchByLastname(String lastname);
    
    public List<Candidate> searchByPosition(String position);
    
    public String addContact(Long currendCdtId, Long contactId);
    
    public CompanyProfile searchCompany(String companyName);
    
    public String subscribe(Long companyId, Long candidateId);
    
    public List<JobOffer> customJobOfferList(Long candidateId);
    
    public List<CompanyProfile> subscriptionList(Long candidateId);
    
    public String accountCreationConfirmation(String email);
    
    public String createCandidate(Candidate candidate);
    
    public String recommend(Long candidateId);
    
    //Cursus
    
    public Long createCursus(Cursus cursus);
    
    public void deleteCursus(Long cursusId);
    
    public Long updateCursus(Cursus cursus);
    
    public Cursus findCursus(Long cursusId);
    
    public void affecterCursusCandidate(Long candidateId, Long cursusId);
    
    //CurriculumVitae
    /*
    public Long createCurriculumVitae(CurriculumVitae curriculumVitae);
    
    public void deleteCurriculumVitae(Long curriculumVitaeId);
    
    public Long updateCurriculumVitae(CurriculumVitae curriculumVitae);
    
    public Long findCurriculumVitae(Long curriculumVitaeId);
    */
    //Experience
    
    public Long createExperience(Experience experience);
    
    public void deleteExperience(Long experienceId);
    
    public Long updateExperience(Experience experience);
    
    public Experience findExperience(Long experienceId);
    
    public void affecterExperienceCandidate(Long expId,  Long candidateId);
    
    //Candidate List display
    
    //public ObservableList<Candidate> getObservableCandidate();
    
    
    public List<Candidate> findAllCandidate();
    
    public int incrementVisits(Long cdtId);
    
    public List<Candidate> searchByEmail(String email);
    
    public List<Candidate> friendsList(Long cdtId);
  //  public boolean checkContacts(Long cdtId, Candidate cdt );
    public Experience getCandidateExperience(Long cdtId);
    
    public Cursus getCandidateCursus(Long cdtId);
 
    

    
    
}
