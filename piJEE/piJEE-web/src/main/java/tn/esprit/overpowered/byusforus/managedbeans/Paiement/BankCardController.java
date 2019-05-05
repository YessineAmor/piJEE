package tn.esprit.overpowered.byusforus.managedbeans.Paiement;
/*
import tn.esprit.overpowered.byusforus.entities.Paiement.BankCard;
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
public class BankCardController implements Serializable {

    @EJB
    private PaimentLocal ejbFacade;
    private List<BankCard> items = null;
    private BankCard selected;

    public BankCardController() {
    }

    public BankCard getSelected() {
        return selected;
    }

    public void setSelected(BankCard selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaimentLocal getFacade() {
        return ejbFacade;
    }

    public BankCard prepareCreate() {
        selected = new BankCard();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BankCardCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BankCardUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BankCardDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BankCard> getItems() {
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

    public BankCard getBankCard(UNDEFINED_PK_TYPE id) {
        return getFacade().find(id);
    }

    public List<BankCard> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BankCard> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BankCard.class)
    public static class BankCardControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BankCardController controller = (BankCardController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bankCardController");
            return controller.getBankCard(getKey(value));
        }

        UNDEFINED_PK_TYPE getKey(String value) {
            UNDEFINED_PK_TYPE key;
            // TODO: no getter methods were found for primary key in your entity class
            //    tn.esprit.overpowered.byusforus.entities.Paiement.BankCard 
            // and therefore Converter.getKey() method could not be pre-generated.
            return key;
        }

        String getStringKey(UNDEFINED_PK_TYPE value) {
            StringBuilder sb = new StringBuilder();
            // TODO: no getter methods were found for primary key in your entity class
            //    tn.esprit.overpowered.byusforus.entities.Paiement.BankCard 
            // and therefore Converter.getKey() method could not be pre-generated.
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof BankCard) {
                BankCard o = (BankCard) object;
                return getStringKey(o.UNDEFINED_PK_GETTER());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BankCard.class.getName()});
                return null;
            }
        }

    }

} 
*/
