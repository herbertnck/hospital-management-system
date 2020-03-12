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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class RegistrationLoginController implements Initializable {
    @FXML
    private AnchorPane rnpane;
    
    @FXML
    private JFXTextField Username;
    
    @FXML
    private JFXPasswordField Userpassword;
    
    @FXML
    private JFXButton Login;
    
    Message msg = new Message();

    private PreparedStatement pst;
    Connection con;
    connection conClass = new connection();
    
    public void createlogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
        String username = Username.getText();
        String password = Userpassword.getText();
       
         if((username.contains("her")) && (password.contains("11")))
        {  
            System.out.println("Login Successful");
        Login.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        //stage.setResizable(false);
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
