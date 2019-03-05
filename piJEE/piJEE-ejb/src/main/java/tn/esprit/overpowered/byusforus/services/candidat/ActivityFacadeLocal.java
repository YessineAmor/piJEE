/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.candidat.Activity;

/**
 *
 * @author EliteBook
 */
@Local
public interface ActivityFacadeLocal {

    void create(Activity activity);

    void edit(Activity activity);

    void remove(Activity activity);

    Activity find(Object id);

    List<Activity> findAll();

    List<Activity> findRange(int[] range);

    int count();
    
}
