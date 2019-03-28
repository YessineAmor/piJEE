/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.util;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author pc
 */
@Embeddable
public class OfferTimesheetId implements Serializable {
    
    	private Long idOffer;
	private Long idEmployee;

    public Long getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Long idOffer) {
        this.idOffer = idOffer;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idOffer);
        hash = 31 * hash + Objects.hashCode(this.idEmployee);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfferTimesheetId other = (OfferTimesheetId) obj;
        if (!Objects.equals(this.idOffer, other.idOffer)) {
            return false;
        }
        if (!Objects.equals(this.idEmployee, other.idEmployee)) {
            return false;
        }
        return true;
    }
        
        
}
