/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.messaging;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Stateful
public class Messaging implements MessagingRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")z
    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void sendMessage(Message m) {
        em.persist(m);
    }
    
    @Override
    public ArrayList<Message> getMessages(User u, LocalDateTime t) {
        MessageRepository msgR = new MessageRepository();
        return msgR.getNewestMessages(em, u, t);
    }
    
    @Override
    public ArrayList<Message> getMyMessages(User u) { 
        MessageRepository msgR = new MessageRepository();
        return msgR.getAllMessages(em, u);

    }
}
