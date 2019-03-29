/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.posting.Comment;
import tn.esprit.overpowered.byusforus.entities.posting.Post;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
@DiscriminatorValue(value = "USER")
public class User implements Serializable{

    @OneToMany
    Set<Post> posts;
    
    @OneToMany
    Set<Comment> comments;
    
    @OneToMany
    Set<Message> messages;
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
     @Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
             + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
             + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21"
             + "\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b"
             + "\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
             + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|"
             + "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|"
             + "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-"
             + "\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
             message="{invalid.email}")
    private String email;
    
    @Column(unique=true)
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) throws NoSuchAlgorithmException {
        this.salt = new byte[32];
        new Random().nextBytes(salt);
        byte[] hashBytes = new byte[password.length + this.salt.length];
        System.arraycopy(password, 0, hashBytes, 0, password.length);
        System.arraycopy(this.salt, 0, hashBytes, password.length, this.salt.length);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        this.password = digest.digest(hashBytes);
    }
    
    @Column(columnDefinition="BINARY(32) NOT NULL")
    private byte[] salt;
    
    @Column(columnDefinition="BINARY(32) NOT NULL")
    private byte[] password;
    

}
