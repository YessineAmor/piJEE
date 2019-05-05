package tn.esprit.overpowered.byusforus.managedbeans.reclamation;

import tn.esprit.overpowered.byusforus.entities.reclamation.Reclamation;
import tn.esprit.overpowered.byusforus.managedbeans.reclamation.util.JsfUtil;
import tn.esprit.overpowered.byusforus.managedbeans.reclamation.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
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
import tn.esprit.overpowered.byusforus.services.Reclamation.ReclamationLocal;

@ManagedBean
@javax.faces.bean.SessionScoped
public class ReclamationController implements Serializable {

    @EJB
    private ReclamationLocal ejbFacade;

    private List<Reclamation> items = null;
    private Reclamation selected;

    public ReclamationController() {
    }

    public Reclamation getSelected() {
        return selected;
    }

    public void setSelected(Reclamation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReclamationLocal getFacade() {
        return ejbFacade;
    }

    public Reclamation prepareCreate() {
        selected = new Reclamation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReclamationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReclamationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReclamationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reclamation> getItems() {
        if (items == null) {
            items = getFacade().All();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().DeleteReclamation1(selected);
                } else {
                    getFacade().DeleteReclamation1(selected);
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

    public Reclamation getReclamation(int id) {
        return getFacade().FindById(id);
    }

    public List<Reclamation> getItemsAvailableSelectMany() {
        return getFacade().All();
    }

    public List<Reclamation> getItemsAvailableSelectOne() {
        return getFacade().All();
    }

    @FacesConverter(forClass = Reclamation.class)
    public static class ReclamationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReclamationController controller = (ReclamationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reclamationController");
            return controller.getReclamation(getKey(value));
        }

        int getKey(String value) {
            int key;
            key = Integer.parseInt(value);
            return key;
        }

        String getStringKey(int value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reclamation) {
                Reclamation o = (Reclamation) object;
                return getStringKey(o.getIdReclamation());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reclamation.class.getName()});
                return null;
            }
        }

    }

}