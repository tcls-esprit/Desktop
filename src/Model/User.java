/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Gender;
import Entities.TypeUser;
import java.util.Date;

/**
 *
 * @author asus
 */
public class User {
    private int userId ; 
    private String nom ; 
    private String prenom ; 
    private String email ; 
    private String password ; 
    private Date birthDate ; 
    private Gender Gender ; 
    private int numero ; 
    private int cin ; 
    private TypeUser Type ; 
    private String username ; 

    public User(int userId, String nom, String prenom, String email, String password, Date birthDate, Gender Gender, int numero, int cin, TypeUser Type,String username) {
        this.userId = userId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.Gender = Gender;
        this.numero = numero;
        this.cin = cin;
        this.Type = Type;
        this.username = username ; 
    }
     public User() {} 
    public User(String nom, String prenom, String email, String password, Date birthDate, Gender Gender, int numero, int cin, TypeUser Type, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.Gender = Gender;
        this.numero = numero;
        this.cin = cin;
        this.Type = Type;
        this.username = username;
    }

    public User( String nom, String prenom, String email, String password, Date birthDate, TypeUser Type,String username) {
        this.userId = userId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.Type = Type;
        this.username = username ; 
    }

    @Override
    public String toString() {
        return "User{"+"username="+username + "userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", birthDate=" + birthDate + ", Gender=" + Gender + ", numero=" + numero + ", cin=" + cin + ", Type=" + Type + '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return Gender;
    }

    public void setGender(Gender Gender) {
        this.Gender = Gender;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public TypeUser getType() {
        return Type;
    }

    public void setType(TypeUser Type) {
        this.Type = Type;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
    






}
