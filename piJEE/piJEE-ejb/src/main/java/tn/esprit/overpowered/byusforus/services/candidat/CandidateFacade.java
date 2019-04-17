/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.lang3.RandomStringUtils;
import tn.esprit.overpowered.byusforus.entities.candidat.Cursus;
import tn.esprit.overpowered.byusforus.entities.candidat.Experience;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.util.MailSender;

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
    public String createCandidate(Candidate candidate) {
        em.persist(candidate);
        return candidate.getUsername();
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
                + "c.firstName  LIKE CONCAT('%',:name,'%')", Candidate.class)
                .setParameter("name", name)
                .getResultList();
        return cdt;
    }

    @Override
    public List<Candidate> searchByLastname(String lastname) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE "
                + "c.lastName  LIKE CONCAT('%',:lastName,'%')", Candidate.class)
                .setParameter("lastName", lastname)
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
    public CompanyProfile searchCompany(String companyName) {
        CompanyProfile comp = (CompanyProfile) em.createQuery(
                "SELECT c FROM CompanyProfile c WHERE c.name LIKE :companyName")
                .setParameter("name", companyName)
                .setMaxResults(1)
                .getResultList();
        return comp;
    }


    @Override
    public void affecterExperienceCandidate(Long expId, Long candidateId) {
        Experience exp = em.find(Experience.class, expId);
        Candidate emp = em.find(Candidate.class, candidateId);
        if (exp != null && emp != null) {
            emp.getExperiences().add(exp);
            exp.setProfessional(emp);
        } else {
            System.out.println("Either candidate or Experience doent exist !");
        }
    }

    @Override
    public List<JobOffer> customJobOfferList(Long candidateId) {
        List<JobOffer> jobList = em.createQuery("SELECT j from JobOffer j",JobOffer.class).getResultList();
        Candidate cdt = em.find(Candidate.class, candidateId);
        List<Experience> exp = cdt.getExperiences();
        Experience testExp = exp.get(0);
        List<JobOffer> customJobs = new ArrayList<>();
        for (JobOffer j: jobList)
        {
            if(j.getTitle().toLowerCase().contains(testExp.getPosition().toLowerCase()))
            {
                customJobs.add(j);
            }
        }
        return customJobs ;
    }

    @Override
    public List<CompanyProfile> subscriptionList(Long candidateId) {
        Candidate cdt = this.find(candidateId);
        return cdt.getSubscribedCompanies();
    }

    //ACCOUNT CONFIRMATION CREATION
    @Override
    public String accountCreationConfirmation(String email) {
        /*       int length = 5;
    boolean useLetters = true;
    boolean useNumbers = false;
    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);*/
        int code = 10000 + new Random().nextInt(90000);
        System.out.println("this is the code " + code);
        try {
            MailSender.sendMail("smtp.gmail.com", "587", "toussaint.kebou@gmail.com",
                    "toussaint.kebou@gmail.com", "Laurel@@2019", email,
                    "Account creation Confirmation Mail",
                    "If you are receiving this Email then you are one step away from"
                    + " joining the BYUSFORUS group thanks you for your trust"
                    + " Confirm registration with following code "
                    + "<b>" + code + "</b>"
                    + "  Enjoy your stay on our platform");

            return Integer.toString(code);
        } catch (MessagingException ex) {
            Logger.getLogger(CandidateFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mailing failure");
        }

        return "mailing system down";

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
        cur.setProfessionalCursus(cdt);
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

    @Override
    public String recommend(Long candidateId) {
        Candidate cdt = em.find(Candidate.class, candidateId);
        cdt.setRecommendations(cdt.getRecommendations() + 1);
        return "OK";

    }

    @Override
    public List<Candidate> findAllCandidate() {
        List<Candidate> cdtList = em.createQuery("select c from "
                + "Candidate c", Candidate.class).getResultList();
        return cdtList;
    }

    @Override
    public int incrementVisits(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        cdt.setVisits(cdt.getVisits() + 1);
        return cdt.getVisits();
    }

    @Override
    public List<Candidate> searchByEmail(String email) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE c.email LIKE "
                + "CONCAT('%',:email,'%')", Candidate.class)
                .setParameter("email", email)
                .getResultList();
        return cdt;
    }

    @Override
    public String addContact(Long currentCdtId, Long contactId) {
        Candidate currentCdt = em.find(Candidate.class, currentCdtId);
        Candidate contact = em.find(Candidate.class, contactId);
        if (!currentCdt.getContacts().contains(contact)) {
            currentCdt.getContacts().add(contact);
            contact.getContacts().add(currentCdt);
            return "Contact Added";
        } else {
            return "Already Friends";
        }
    }

    /*
    @Override
    public boolean checkContacts(Long cdtId, Candidate cdt) {
        Candidate cdtt = em.find(Candidate.class, cdtId);
        return cdtt.getContacts().contains(cdt);
    }
     */

    @Override
    public List<Candidate> friendsList(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        List<Candidate> listCdt = cdt.getContacts();
        List<Candidate> contactsList = new ArrayList<>();
        for (Candidate cdtt : listCdt) {
            contactsList.add(em.find(Candidate.class, cdtt.getId()));
        }
        return contactsList;

        /*List<Long> idList = em.createQuery("SELECT c.contacts_id from"
                + " contacts c where c.Candidate_id = :cdtId",Long.class)
                .setParameter("cdtId", cdtId)
                .getResultList();
        List<Candidate> contacts = new ArrayList<>() ;
        for(Long id: idList)
        {
            contacts.add(em.find(Candidate.class, id));
        }
        return contacts;
         */
    }

    @Override
    public Experience getCandidateExperience(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        List<Experience> expTemp = cdt.getExperiences();
        if(expTemp.isEmpty())
        {
            return null;
        }
        else 
        {
        Long expId = expTemp.get(0).getId();
        return em.find(Experience.class, expId);
        }

    }

    @Override
    public Cursus getCandidateCursus(Long cdtId) {
         Candidate cdt = em.find(Candidate.class, cdtId);
        List<Cursus> expTemp = cdt.getCursus();
        if(expTemp.isEmpty())
        {
            return null;
        }
        else
        {
        Long expId = expTemp.get(0).getId();
        return em.find(Cursus.class, expId);
        }
        
    }

    @Override
    public String subscribe(Long companyId, Long candidateId) {
         Candidate currentCdt = em.find(Candidate.class, candidateId);
        CompanyProfile comp = em.find(CompanyProfile.class, companyId);
        if (!currentCdt.getSubscribedCompanies().contains(comp)) {
            currentCdt.getSubscribedCompanies().add(comp);
            comp.getSubscribers().add(currentCdt);
            return "Contact Added";
        } else {
            return "Already Friends";
        }
    }

}
