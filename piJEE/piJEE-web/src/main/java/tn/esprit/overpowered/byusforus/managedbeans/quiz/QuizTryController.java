package tn.esprit.overpowered.byusforus.managedbeans.quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import tn.esprit.overpowered.byusforus.entities.quiz.QuizTry;
import tn.esprit.overpowered.byusforus.managedbeans.util.JsfUtil;
import tn.esprit.overpowered.byusforus.managedbeans.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tn.esprit.overpowered.byusforus.entities.quiz.Answer;
import tn.esprit.overpowered.byusforus.entities.quiz.Choice;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.services.quiz.ChoiceFacadeLocal;
import tn.esprit.overpowered.byusforus.services.quiz.QuizTryFacadeLocal;

@ManagedBean
@javax.faces.bean.SessionScoped
public class QuizTryController implements Serializable {

    @EJB
    private ChoiceFacadeLocal choiceFacade;

    private static final long serialVersionUID = 1L;

    @EJB
    private QuizTryFacadeLocal ejbFacade;
    private List<QuizTry> items = null;
    private QuizTry selected;

    private String[] selections;

    public ChoiceFacadeLocal getChoiceFacade() {
        return choiceFacade;
    }

    public void setChoiceFacade(ChoiceFacadeLocal choiceFacade) {
        this.choiceFacade = choiceFacade;
    }

    public QuizTryFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(QuizTryFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String[] getSelections() {
        return selections;
    }

    public void setSelections(String[] selections) throws IOException {
        File f = new File("87azeaz.txt");
        f.createNewFile();
        this.selections = selections;
    }

    public void listen() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        File f = new File("87azeaz.txt");
        f.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            writer.write(params.get("choiceQuestion1"));
            writer.write(params.get("choiceQuestion2"));
        }
    }

    public void submitTry(Quiz quiz) {
        QuizTry qt = new QuizTry();
        qt.setFinishDate(new Date());
        qt.setQuiz(quiz);
        // Get choice by id
        ArrayList<Answer> answerList = new ArrayList<>();
        for (String id : selections) {
            Choice c = choiceFacade.find(Long.parseLong(id));
            Answer answer = new Answer();
            answer.setAnswer(c);
            answerList.add(answer);
        }
        qt.setAnswers(answerList);
        ejbFacade.create(qt);
    }

    public QuizTryController() {
    }

    public QuizTry getSelected() {
        return selected;
    }

    public void setSelected(QuizTry selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private QuizTryFacadeLocal getFacade() {
        return ejbFacade;
    }

    public QuizTry prepareCreate() {
        selected = new QuizTry();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuizTryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("QuizTryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("QuizTryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<QuizTry> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public QuizTry getQuizTry(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<QuizTry> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<QuizTry> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = QuizTry.class)
    public static class QuizTryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            QuizTryController controller = (QuizTryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "quizTryController");
            return controller.getQuizTry(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof QuizTry) {
                QuizTry o = (QuizTry) object;
                return getStringKey(o.getIdQuizTry());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), QuizTry.class.getName()});
                return null;
            }
        }

    }

}
