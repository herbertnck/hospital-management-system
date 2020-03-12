/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.userinterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import hospital.management.alertmessage.Message;
import hospital.management.dbconnection.connection;
import java.io.IOException;
//import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Herbert
 */
public class PharmacyLoginController implements Initializable {
    @FXML
    private AnchorPane pnpane;
    
    @FXML
    private JFXTextField Pharmacistname;

    @FXML
    private JFXPasswordField Pharmacistpassword;
    
    @FXML
    private JFXButton Login;
    
    Message msg = new Message();

    private PreparedStatement pst;
    Connection con;
    connection conClass = new connection();
    
    public void createlogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
      con = conClass.getConnection();
//      System.out.println(con);
      System.out.println("Connected to Database");
      String str = "SELECT *FROM pharmacist where FirstName=? and Password=?";
      pst = con.prepareStatement(str);
      
      pst.setString(1, Pharmacistname.getText());
      pst.setString(2, Pharmacistpassword.getText());
      
      ResultSet rs = pst.executeQuery();
      int count=0;
      
      while(rs.next())
      {
          count = count+1;
      }
      if(count==1)
      {
          System.out.println("Login Successful");
        Login.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Pharmacy.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
           msg.setMessage("LOGIN SUCCESSFUL");

      }
      else
      {
          System.out.println("Login Failed");
          msg.setMessage("LOGIN FAILED");

      }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }        
}
