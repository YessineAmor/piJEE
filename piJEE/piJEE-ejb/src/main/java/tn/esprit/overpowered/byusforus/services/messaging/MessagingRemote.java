/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.messaging;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.ejb.Local;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Remote
public interface MessagingRemote {
    
    public void sendMessage(Message m);
    public ArrayList<Message> getMessages(User u, LocalDateTime t);
    public ArrayList<Message> getMyMessages(User u);
}
