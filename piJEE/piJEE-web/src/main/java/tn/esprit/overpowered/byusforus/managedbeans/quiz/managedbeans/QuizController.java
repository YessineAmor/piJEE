/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.quiz.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.services.quiz.QuizFacadeLocal;

/**
 *
 * @author Yassine
 */
@ManagedBean
@SessionScoped
public class QuizController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private QuizFacadeLocal quizFacade;

    /**
     * Creates a new instance of QuizController
     */
    public QuizController() {
    }

    public QuizFacadeLocal getQuizFacade() {
        return quizFacade;
    }

    public void setQuizFacade(QuizFacadeLocal quizFacade) {
        this.quizFacade = quizFacade;
    }

    public List<Quiz> getAllQuizzes() {
        return quizFacade.findAll();
    }

    public String getFirstQuizName() {
        return "sdfds";
    }

}
