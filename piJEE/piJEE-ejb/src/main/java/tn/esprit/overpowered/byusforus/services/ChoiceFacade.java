/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.Choice;

/**
 *
 * @author Yassine
 */
@Stateless
public class ChoiceFacade extends AbstractFacade<Choice> implements ChoiceFacadeLocal,ChoiceFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChoiceFacade() {
        super(Choice.class);
    }

    @Override
    public void create(Choice entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
