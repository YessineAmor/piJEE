/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.candidat.CurriculumVitae;

/**
 *
 * @author EliteBook
 */
@Stateless
public class CurriculumVitaeFacade extends AbstractFacade<CurriculumVitae> implements CurriculumVitaeFacadeLocal,CurriculumVitaeFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CurriculumVitaeFacade() {
        super(CurriculumVitae.class);
    }
    
}
