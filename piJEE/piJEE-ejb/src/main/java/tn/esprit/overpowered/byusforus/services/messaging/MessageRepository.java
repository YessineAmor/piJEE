/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.messaging;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.authentication.Auth2FA;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
public class MessageRepository {

    public ArrayList<Message> getNewestMessages(EntityManager em, User u, LocalDateTime t) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.sentTime > :time and m.from = :u", Message.class);
        query.setParameter("time", t);
        query.setParameter("from", u);
        ArrayList<Message> messageList = (ArrayList<Message>) query.getResultList();
        return messageList;
    }

    public ArrayList<Message> getAllMessages(EntityManager em, User u) {
        return (ArrayList< Message>) em.createQuery(
                "SELECT * FROM Message m WHERE m.from = :u", Message.class)
                .setParameter("u", u).getResultList();

    }
}
