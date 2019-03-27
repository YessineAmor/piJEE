/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "PROJECT_MANAGER")
public class ProjectManager extends Employee implements Serializable {

    
}
