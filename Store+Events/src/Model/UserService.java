/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Utility.BCrypt;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khaled
 */
public class UserService implements Iservice<User> {

    private Connection cnx;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<User> Users = new ArrayList<User>();

    public UserService() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void insert(User t) {
        try {
            String hashedpw = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(12));
            String requete = "insert into user (nom,prenom,email,pwd,ddn,sexe,tel,ci,type,username) values (?,?,?,?,?,?,?,?,?,?) ";
            pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, hashedpw);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date myDate = formatter.parse(t.getBirthDate());
                System.out.println(myDate);
                pst.setDate(5, new java.sql.Date(myDate.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pst.setString(6, t.getGender().name());
            pst.setString(7, t.getNumero());
            pst.setInt(8, t.getCin());
            pst.setString(9, t.getType().name());
            pst.setString(10, t.getUsername());
            pst.executeUpdate();
            System.out.println("User added");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String requete = "delete from user where id=" + id;
            pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("user deleted");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User t, int id) {
        try {
            String requete = "update user SET nom=?,prenom=?,email=?,pwd=?,ddn=?,sexe=?,tel=?,ci=?,type=? username=? WHERE id=" + t.getUserId();
            pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getPassword());
            pst.setString(5, t.getBirthDate());
            pst.setString(6, t.getGender().name());
            pst.setString(7, t.getNumero());
            pst.setInt(8, t.getCin());
            pst.setString(9, t.getType().name());
            pst.setString(10, t.getUsername());
            pst.executeUpdate();
            System.out.println("user updated");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> GetAll() {
        try {
            String requete = "select * from user";
            pst = cnx.prepareStatement(requete);
            rs = pst.executeQuery();
            while (rs.next()) {
                Users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                        , Gender.valueOf(rs.getString(7)), rs.getString(8), rs.getInt(9), TypeUser.valueOf(rs.getString(10)),rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Users;
    }
    public boolean login(String mail, String pwd) throws SQLException {
        if (!mail.isEmpty() && !pwd.isEmpty()) {
            String req = "SELECT pwd from user where email = '" + mail + "'";
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery(req);
            if (rs.next()) {
                String pw = rs.getString(1);
                return (BCrypt.checkpw(pwd, pw));
                /*if(pw.equals(pwd))
                    return true;
                else return false;*/
            } else
                return false;
        }
        else
        {
            return false;
        }
    }
    public User getUserByEmail(String mail) throws SQLException {
        String req = "select * from user where email='" + mail + "'";
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery(req);
        User u = new User();
        if (rs.next()) {
            u.setUserId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setBirthDate(rs.getString("ddn"));
            u.setGender(Gender.valueOf(rs.getString("sexe")));
            u.setPassword(rs.getString("pwd"));
            u.setEmail(rs.getString("email"));
            u.setType(TypeUser.valueOf(rs.getString("type")));
            u.setNumero(rs.getString("tel"));
            u.setCin(rs.getInt("ci"));
            u.setUsername(rs.getString("username"));
        }
        return u;
    }
    public String getUserMail(int id)
    {
        String email="";
        String query = "select * from user where id ="+id;
     try {
         pst=cnx.prepareStatement(query);
         rs=pst.executeQuery();
         while (rs.next())
         {
             email= rs.getString(4);
         }
     } catch (SQLException ex) {
         Logger.getLogger(Model.Service.UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
        return email;
    }
}