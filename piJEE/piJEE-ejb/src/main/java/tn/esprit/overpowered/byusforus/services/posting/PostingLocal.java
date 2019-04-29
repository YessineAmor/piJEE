/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.posting;

import java.util.ArrayList;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import tn.esprit.overpowered.byusforus.entities.posting.Post;

/**
 *
 * @author aminos
 */

@Local
public interface PostingLocal {
     public EntityManager getEntityManager();
     public void createPost(Post p, Long a);
     public void updatePost(Long p);
     public ArrayList<Post> getPosts(Long u);
     public void deletePost(Long p);
     public Post getPost(Long p);
}
