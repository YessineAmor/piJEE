/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author EliteBook
 */
@Stateless
public class AuthenticationFacade implements AuthenticationFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public String login(String username, String password) throws NoSuchAlgorithmException {
        byte[] pwd = password.getBytes(StandardCharsets.UTF_8);
        TypedQuery<User> query
                = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query = query.setParameter("username", username);
        List<User> userList = query.getResultList();
        if (userList.isEmpty())
            return null;
        User user = userList.get(0);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
      
        
        byte[] hashBytes = new byte[pwd.length + user.getSalt().length];
        System.arraycopy(pwd, 0, hashBytes, 0, pwd.length);
        System.arraycopy(user.getSalt(), 0, hashBytes, pwd.length, user.getSalt().length);
        if (Arrays.equals(user.getPassword(), digest.digest(hashBytes))) {
            return genToken();
        } else {
            return null;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public int logout() {
        return 0;
    }

    @Override
    public int isLoggedIn(final String token) {
        return 0;
    }

    private String genToken() {
        return "succes";
    }

}
