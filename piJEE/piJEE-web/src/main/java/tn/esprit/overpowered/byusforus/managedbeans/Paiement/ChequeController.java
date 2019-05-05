package tn.esprit.overpowered.byusforus.managedbeans.Paiement;

import tn.esprit.overpowered.byusforus.entities.Paiement.Cheque;
import tn.esprit.overpowered.byusforus.managedbeans.Paiement.util.JsfUtil;
import tn.esprit.overpowered.byusforus.managedbeans.Paiement.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tn.esprit.overpowered.byusforus.services.Paiement.PaimentLocal;


@ManagedBean
@javax.faces.bean.SessionScoped
public class ChequeController implements Serializable {

    @EJB
    private PaimentLocal ejbFacade;
    private List<Cheque> items = null;
    private Cheque selected;

    public ChequeController() {
    }

    public Cheque getSelected() {
        return selected;
    }

    public void setSelected(Cheque selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaimentLocal getFacade() {
        return ejbFacade;
    }

    public Cheque prepareCreate() {
        selected = new Cheque();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ChequeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ChequeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ChequeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cheque> getItems() {
        if (items == null) {
            items = getFacade().FindCheque();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().editCheque(selected);
                } else {
                    getFacade().editCheque(selected);
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

    public Cheque getCheque(java.lang.Integer id) {
        return getFacade().findByIdCheque(id);
    }

    public List<Cheque> getItemsAvailableSelectMany() {
        return getFacade().FindCheque();
    }

    public List<Cheque> getItemsAvailableSelectOne() {
        return getFacade().FindCheque();
    }

    @FacesConverter(forClass = Cheque.class)
    public static class ChequeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChequeController controller = (ChequeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "chequeController");
            return controller.getCheque(getKey(value));
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
            if (object instanceof Cheque) {
                Cheque o = (Cheque) object;
                return getStringKey(o.getIdCheque());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cheque.class.getName()});
                return null;
            }
        }

    }

}
