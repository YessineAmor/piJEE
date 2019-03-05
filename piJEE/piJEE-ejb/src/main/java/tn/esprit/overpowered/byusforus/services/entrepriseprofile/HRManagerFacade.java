/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.HRManager;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;

/**
 *
 * @author pc
 */
@Stateless
public class HRManagerFacade extends AbstractFacade<HRManager> implements HRManagerFacadeLocal, HRManagerFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HRManagerFacade() {
        super(HRManager.class);
    }
    
}