/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.util;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import tn.esprit.overpowered.byusforus.entities.quiz.QuestionType;

/**
 *
 * @author Yassine
 */
@ManagedBean
@javax.faces.bean.SessionScoped
public class Enums implements Serializable {

    /**
     * Creates a new instance of Enums
     */
    public Enums() {

    }

    public QuestionType[] getQuestionTypes() {
        return QuestionType.values();
    }

}
