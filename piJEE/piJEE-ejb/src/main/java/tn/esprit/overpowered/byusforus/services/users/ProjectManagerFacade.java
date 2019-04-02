/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 *
 * @author pc
 */
@Stateless
public class ProjectManagerFacade extends AbstractFacade<ProjectManager> implements ProjectManagerFacadeLocal, ProjectManagerFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectManagerFacade() {
        super(ProjectManager.class);
    }

    @Override
    public Long createJobOffer(JobOffer jobOffer, Long idPManager, String gmailPassword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long createPManager(ProjectManager pManager) {
        em.persist(pManager);
        return pManager.getId();
    }

    @Override
    public boolean affecterPMtoCompany(Long pManagerId, String compName) {
               ProjectManager pm = em.find(ProjectManager.class, pManagerId);
        CompanyProfile comp = null;
        try {
              comp = em.createQuery("select c from CompanyProfile c "
                + "where c.name "
                + "= :compname",CompanyProfile.class).setParameter("compname", compName).getSingleResult();
        pm.setCompanyProfile(comp);
        comp.getProjectManagers().add(pm);
        } catch (NoResultException e ) {
        }
            return comp != null ;
        
    }


    
}
