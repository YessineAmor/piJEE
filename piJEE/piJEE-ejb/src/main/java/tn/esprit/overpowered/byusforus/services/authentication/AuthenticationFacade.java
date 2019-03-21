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
        User user = query.getSingleResult();
        if (user == null) {
            return null;
        }
        /*MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] pwdSalt = new byte[passwordBytes.length + user.getSalt().length];
        System.arraycopy(passwordBytes, 0, pwdSalt, 0, passwordBytes.length);
        System.arraycopy(user.getSalt(), 0, pwdSalt, passwordBytes.length, user.getSalt().length);
        byte[] encodedhash = digest.digest(
               passwordBytes);*/
        for (byte b : password.getBytes())
            System.out.print(" " + b);
        System.out.println("\n");
        System.out.println(user.getPassword().length);
        for (byte c : user.getPassword())
            System.out.print(c);
        System.out.println("end");
        if (Arrays.equals(user.getPassword(), password.getBytes(StandardCharsets.UTF_8))) {
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
