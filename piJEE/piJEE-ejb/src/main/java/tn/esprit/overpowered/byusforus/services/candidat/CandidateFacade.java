/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.candidat.Experience;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;

/**
 *
 * @author EliteBook
 */
@Stateless
public class CandidateFacade extends AbstractFacade<Candidate>
        implements CandidateFacadeLocal, CandidateFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateFacade() {
        super(Candidate.class);
    }

    @Override
    public List<Candidate> afficherCandidats() {
        List<Candidate> Lcandidats = em.createQuery("select p from Candidat p", Candidate.class).getResultList();
        return Lcandidats;
    }

    @Override
    public Candidate searchByName(String name) {
        Candidate cdt = (Candidate) em.createQuery(
                "SELECT c FROM Candidate c WHERE c.name LIKE :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();
        return cdt;
    }

    @Override
    public Candidate searchByLastname(String lastname) {
        Candidate cdt = (Candidate) em.createQuery(
                "SELECT c FROM Candidate c WHERE c.lastname LIKE :lastname")
                .setParameter("name", lastname)
                .setMaxResults(1)
                .getResultList();
        return cdt;
    }

    @Override
    public List<Candidate> searchByPosition(String position) {
        List<Candidate> cdts = this.findAll();
        for(Candidate cdt: cdts)
        {
            for(Experience exp:cdt.getExperiences())
            {
                if(exp.getPosition().equals(position))
                    cdts.add(cdt);
            }
        }
        return cdts;
        
    }

    @Override
    public Long addContact(Long candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyProfile searchCompany(String companyName) {
        CompanyProfile comp = (CompanyProfile) em.createQuery(
                "SELECT c FROM CompanyProfile c WHERE c.name LIKE :companyName")
                .setParameter("name", companyName)
                .setMaxResults(1)
                .getResultList();
        return comp;
    }

    @Override
    public Long subscribe(Long companyId, Long candidateId) {
        CompanyProfile comp = em.find(CompanyProfile.class, companyId);
        Candidate cdt = em.find(Candidate.class, candidateId);
        comp.getFollowers().add(cdt);
        cdt.getSubscriptions().add(comp);
        return comp.getId();
    }

    @Override
    public void affecterExperienceCandidate(Long expId, Long candidateId) {
      Experience exp = em.find(Experience.class, expId);
      Candidate emp = em.find(Candidate.class, candidateId);
      emp.getExperiences().add(exp);
      exp.setCandidate(emp);
        
    }

    @Override
    public List<JobOffer> customJobOfferList(Long candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
