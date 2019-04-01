/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;

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
    public void updateCandidateApplication(CandidateApplication cApp) {
        int cdtApp = em.createQuery(
                "update CandidateApplication ca set additionalInfo = :adinfo and jobApplicationState = :jas WHERE ca.id = :caid")
                .setParameter("adinfo", cApp.getAdditionalInfo())
                .setParameter("jas", cApp.getJobApplicationState().toString())
                .setParameter("caid", cApp.getId())
                .executeUpdate();
    }

}
