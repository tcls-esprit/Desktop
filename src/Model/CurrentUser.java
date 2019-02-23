/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.User;

/**
 * @author Khaled
 */
public class CurrentUser {

    public static int id;
    public static String nom, prenom, email, phone, sexe, date;
    public static TypeUser type;
    public static Gender gender;

    public CurrentUser() {


    }

    @Override
    public String toString() {
        return "CurrentUser{" + id + nom + prenom + email + phone + type + date + sexe + '}';
    }

    public CurrentUser(User u) {
        id = u.getUserId();
        nom = u.getNom();
        prenom = u.getPrenom();
        email = u.getEmail();
        phone = u.getNumero();
        date = u.getBirthDate();
        gender = u.getGender();
        type = u.getType();
    }
}
