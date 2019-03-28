/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author EliteBook
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal, UserFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User getUserByUsername(EntityManager em, String username) {
        TypedQuery<User> query
                = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query = query.setParameter("username", username);
        List<User> userList = query.getResultList();
        if (userList.isEmpty()) {
            return null;
        } else
            return userList.get(0);
    }

}
