/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.posting;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import tn.esprit.overpowered.byusforus.entities.posting.Post;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
public class PostsRepository {
    public ArrayList<Post> getPosts(EntityManager em, User user) {
         ArrayList<Post> result = new ArrayList();
        result.addAll(em.createQuery(
                "SELECT p FROM Post p WHERE p.by = :u ", Post.class)
                .setParameter("u", user).getResultList());
        return result;
    }
}
