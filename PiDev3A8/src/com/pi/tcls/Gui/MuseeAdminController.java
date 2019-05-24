/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import com.jfoenix.controls.JFXButton;
import com.pi.connectionBD.ConnectionBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class MuseeAdminController implements Initializable {

    @FXML
    private JFXButton demandesVisites;
    @FXML
    private JFXButton gestGuides;
    @FXML
    private JFXButton rapport;
    @FXML
    private JFXButton back;
    @FXML
    private ImageView musee;
    
    
    private ConnectionBD con;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private AnchorPane Panel;
    /**
     * Initializes the controller class.
     */
    public MuseeAdminController(){
       con=ConnectionBD.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    @FXML
     public void VisiteDemande (ActionEvent evt){
       
        
       try {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("AdminVisites.fxml"));
        Panel.getChildren().setAll(ap);
        
 
        } catch (IOException ex) {
            Logger.getLogger(MuseeUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    @FXML
      public void gestionGuide (ActionEvent evt){
       
      try {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("AdminGuide.fxml"));
        Panel.getChildren().setAll(ap);
        
 
        } catch (IOException ex) {
            Logger.getLogger(MuseeUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    @FXML
      public void toExcel(ActionEvent evt) throws SQLException, FileNotFoundException, IOException{
                        ste = con.getCnx().createStatement();
                        ResultSet rs = ste.executeQuery("Select * from visite");

                        HSSFWorkbook wb = new HSSFWorkbook();
                        HSSFSheet sheet = wb.createSheet("Excel Sheet");
                        HSSFRow rowhead = sheet.createRow((short) 0);
                        rowhead.createCell((short) 0).setCellValue("date");
                        rowhead.createCell((short) 1).setCellValue("heure debut");
                        rowhead.createCell((short) 2).setCellValue("heure fin");
                        rowhead.createCell((short) 3).setCellValue("prix");
                        

                        int index = 1;
                        while (rs.next()) {

                                HSSFRow row = sheet.createRow((short) index);
                                row.createCell((short) 0).setCellValue(rs.getDate(2).toString());
                                row.createCell((short) 1).setCellValue(rs.getTime(3).toString());
                                row.createCell((short) 2).setCellValue(rs.getTime(4).toString());
                                row.createCell((short) 3).setCellValue(rs.getInt(5));
                                
                                index++;
                        }
                        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Bilel\\Desktop\\RapportVisite.xls");
        try {
            wb.write(fileOut);
        } catch (IOException ex) {
            Logger.getLogger(MuseeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        fileOut.close();
                        System.out.println("Data is saved in excel file.");
                        rs.close();
                        
                
}

    
}

      
