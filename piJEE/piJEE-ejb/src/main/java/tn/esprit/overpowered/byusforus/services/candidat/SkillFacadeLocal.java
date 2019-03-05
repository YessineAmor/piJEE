/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.candidat.Skill;

/**
 *
 * @author EliteBook
 */
@Local
public interface SkillFacadeLocal {

    void create(Skill skill);

    void edit(Skill skill);

    void remove(Skill skill);

    Skill find(Object id);

    List<Skill> findAll();

    List<Skill> findRange(int[] range);

    int count();
    
}
