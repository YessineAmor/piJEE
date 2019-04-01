/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.quiz.Choice;
import tn.esprit.overpowered.byusforus.entities.quiz.Question;
import tn.esprit.overpowered.byusforus.entities.quiz.QuestionType;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeLocal;
import tn.esprit.overpowered.byusforus.services.quiz.QuizFacadeLocal;

/**
 *
 * @author Yassine
 */
@Singleton
@Startup
public class DBPopulator {

    @EJB
    private QuizFacadeLocal quizFacade;
    @EJB
    private JobOfferFacadeLocal jobOfferFacade;

    public DBPopulator() {
    }

    @PostConstruct
    public void createData() {
//        System.out.println("Filling database..");
        if (quizFacade.find(1) == null) {

            List<Question> questionsList = new ArrayList<>();
            List<Choice> question1ChoicesList = new ArrayList<>();
            question1ChoicesList.add(new Choice("static", Boolean.FALSE, 0));
            question1ChoicesList.add(new Choice("Boolean", Boolean.TRUE, 0));
            question1ChoicesList.add(new Choice("void", Boolean.FALSE, 0));
            question1ChoicesList.add(new Choice("private", Boolean.FALSE, 0));
            Question question1 = new Question("Which of the following is not a keyword in java?", 3, QuestionType.SINGLE_ANSWER, question1ChoicesList);

            List<Choice> question2ChoicesList = new ArrayList<>();
            question2ChoicesList.add(new Choice("True", Boolean.TRUE, 0));
            question2ChoicesList.add(new Choice("False", Boolean.FALSE, 0));
            Question question2 = new Question("Can we have multiple classes in same java file?", 4, QuestionType.SINGLE_ANSWER, question2ChoicesList);

            List<Choice> question3ChoicesList = new ArrayList<>();
            question3ChoicesList.add(new Choice("0.0d", Boolean.FALSE, 0));
            question3ChoicesList.add(new Choice("0.0f", Boolean.TRUE, 0));
            question3ChoicesList.add(new Choice("Null", Boolean.FALSE, 0));
            question3ChoicesList.add(new Choice("Not defined", Boolean.FALSE, 0));
            Question question3 = new Question("What is the default value of float variable?", 2, QuestionType.SINGLE_ANSWER, question3ChoicesList);

            List<Choice> question4ChoicesList = new ArrayList<>();
            question4ChoicesList.add(new Choice("true", Boolean.FALSE, 0));
            question4ChoicesList.add(new Choice("false", Boolean.TRUE, 0));
            question4ChoicesList.add(new Choice("null", Boolean.FALSE, 0));
            question4ChoicesList.add(new Choice("Not defined", Boolean.FALSE, 0));
            Question question4 = new Question("What is the default value of Boolean variable?", 3, QuestionType.SINGLE_ANSWER, question4ChoicesList);

            questionsList.add(question1);
            questionsList.add(question2);
            questionsList.add(question3);
            questionsList.add(question4);
            // Quiz Info
            String quizName = "Technical Skills Evaluation";
            String quizDetails = "This quiz aims to test the candidate's technical skills in the following fields: JAVA PROGRAMMING LANGUAGE";
            float percentageToPass = 80f;
            Quiz quiz = new Quiz();
            quiz.setDetails(quizDetails);
            quiz.setName(quizName);
            quiz.setPercentageToPass(percentageToPass);
            quiz.setQuestions(questionsList);
            quiz.setDuration(40);

            quizFacade.create(quiz);

        }

//        if (jobOfferFacade.find(1) == null) {
//            CompanyProfile company = new CompanyProfile();
//            company.setName("Facebook");
//            JobOffer jobOffer = new JobOffer(new Long(1));
//            jobOffer.setTitle("Développeur JAVA");
//            Set<Skill> skillSet = new HashSet<>();
//            jobOffer.setSkills(skillSet);
//            jobOffer.setPeopleNeeded(3);
//            jobOffer.setExpertiseLevel(ExpertiseLevel.JUNIOR);
//            jobOffer.setDescription("The candidate will help us work on JavaEE projects.");
//            jobOffer.setCity("Tunis");
//            jobOffer.setCompany(company);
//            jobOfferFacade.create(jobOffer);
//        }
    }

}
