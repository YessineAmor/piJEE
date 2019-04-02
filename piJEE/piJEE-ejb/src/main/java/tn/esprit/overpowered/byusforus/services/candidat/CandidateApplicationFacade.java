/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.util.JobApplicationState;

/**
 *
 * @author Yassine
 */
@Stateless
public class CandidateApplicationFacade extends AbstractFacade<CandidateApplication> implements CandidateApplicationFacadeLocal, CandidateApplicationFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateApplicationFacade() {
        super(CandidateApplication.class);
    }

    @Override
    public CandidateApplication getApplicationByCandidateId(Long candidateId, Long jobOfferId) {
        CandidateApplication cdtApp = em.createQuery(
                "SELECT ca FROM CandidateApplication ca WHERE "
                + "ca.candidate.id  = :cid and ca.jobOffer.id = :jib", CandidateApplication.class)
                .setParameter("cid", 1L)
                .setParameter("jib", 1L)
                .getSingleResult();
        return cdtApp;
    }

    @Override
    public void updateCandidateApplication(int id, String additionalInfo, JobApplicationState appState) {
        int cdtApp = em.createQuery(
                "update CandidateApplication ca set ca.additionalInfo = :adinfo , ca.jobApplicationState = :jas WHERE ca.id = :caid")
                .setParameter("adinfo", additionalInfo)
                .setParameter("jas", appState)
                .setParameter("caid", id)
                .executeUpdate();
    }

    @Override
    public List<CandidateApplication> getCandidateApplicationByJobOFfer(Long jobOfferId) {
        List<CandidateApplication> cdtApp = em.createQuery(
                "SELECT ca FROM CandidateApplication ca WHERE "
                + " ca.jobOffer.id = :jib", CandidateApplication.class)
                .setParameter("jib", jobOfferId)
                .getResultList();
        return cdtApp;
    }

}
