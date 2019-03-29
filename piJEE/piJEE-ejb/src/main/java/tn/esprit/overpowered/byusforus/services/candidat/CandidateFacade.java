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
import javax.persistence.Query;
import tn.esprit.overpowered.byusforus.entities.candidat.Cursus;
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
        List<Candidate> Lcandidats = em.createQuery("select p from Candidate p",
                Candidate.class).getResultList();
        return Lcandidats;
    }

    @Override
    public List<Candidate> searchByName(String name) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE "
                + "c.firstName  LIKE CONCAT('%',:name,'%')",Candidate.class)
                .setParameter("name", name)
                .getResultList();
        return cdt;
    }

    @Override
    public List<Candidate> searchByLastname(String lastname) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE c.lastName LIKE "
                + "CONCAT('%',:lastname,'%')",Candidate.class)
                .setParameter("name", lastname)
                .getResultList();
        return cdt;
    }

    @Override
    public List<Candidate> searchByPosition(String position) {
        List<Candidate> cdts = this.findAll();
        for (Candidate cdt : cdts) {
            for (Experience exp : cdt.getExperiences()) {
                if (exp.getPosition().equals(position)) {
                    cdts.add(cdt);
                }
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
        List<CompanyProfile> companies = this.subscriptionList(candidateId);
        CompanyProfile comp = em.find(CompanyProfile.class, companyId);
        Candidate cdt = em.find(Candidate.class, candidateId);
        if (!companies.contains(comp)) {
            comp.getSubscribers().add(cdt);
            cdt.getSubscribedCompanies().add(comp);
            return comp.getId();
        } else {
            return -1L;
        }

    }

    @Override
    public void affecterExperienceCandidate(Long expId, Long candidateId) {
        Experience exp = em.find(Experience.class, expId);
        Candidate emp = em.find(Candidate.class, candidateId);
        if (exp != null && emp != null) {
            emp.getExperiences().add(exp);
            exp.setCandidate(emp);
        }
        else
            System.out.println("Either candidate or Experience doent exist !");
    }

    @Override
    public List<JobOffer> customJobOfferList(Long candidateId) {
       Candidate cdt = this.find(candidateId);
        return cdt.getRegisteredOffers();
    }

    @Override
    public List<CompanyProfile> subscriptionList(Long candidateId) {
        Candidate cdt = this.find(candidateId);
        return cdt.getSubscribedCompanies();
    }

    @Override
    public Long createCandidate(Candidate candidate) {
        em.persist(candidate);
        return candidate.getId();

    }

    @Override
    public Long recommend(Long candidateId) {
        Candidate cdt = em.find(Candidate.class, candidateId);
        cdt.setRecommendations(cdt.getRecommendations() + 1);
        return cdt.getId();
    }

    @Override
    public Long createCursus(Cursus cursus) {
        em.persist(cursus);
        return cursus.getId();
    }

    @Override
    public void deleteCursus(Long cursusId) {
        Cursus act = em.find(Cursus.class, cursusId);
        em.remove(getEntityManager().merge(act));
    }

    @Override
    public Long updateCursus(Cursus cursus) {
        getEntityManager().merge(cursus);
        return cursus.getId();
    }

    @Override
    public Cursus findCursus(Long cursusId) {
        return em.find(Cursus.class, cursusId);
    }

    @Override
    public void affecterCursusCandidate(Long candidateId, Long cursusId) {
        Candidate cdt = em.find(Candidate.class, candidateId);
        Cursus cur = em.find(Cursus.class, cursusId);
        cdt.getCursus().add(cur);
        cur.setCandidateCursus(cdt);
    }

    @Override
    public Long createExperience(Experience experience) {
        em.persist(experience);
        return experience.getId();
    }

    @Override
    public void deleteExperience(Long experienceId) {
        Experience act = em.find(Experience.class, experienceId);
        em.remove(getEntityManager().merge(act));
    }

    @Override
    public Long updateExperience(Experience experience) {
        getEntityManager().merge(experience);
        return experience.getId();
    }

    @Override
    public Experience findExperience(Long experienceId) {
        return em.find(Experience.class, experienceId);
    }

}
