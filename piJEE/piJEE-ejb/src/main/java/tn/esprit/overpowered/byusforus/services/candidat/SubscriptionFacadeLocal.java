/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.candidat.Subscription;

/**
 *
 * @author EliteBook
 */
@Local
public interface SubscriptionFacadeLocal {

    void create(Subscription subscription);

    void edit(Subscription subscription);

    void remove(Subscription subscription);

    Subscription find(Object id);

    List<Subscription> findAll();

    List<Subscription> findRange(int[] range);

    int count();
    
}
