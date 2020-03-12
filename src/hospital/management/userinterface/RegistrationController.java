/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.userinterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hospital.management.alertmessage.Message;
import hospital.management.dbconnection.connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Herbert
 */
public class RegistrationController implements Initializable {
    @FXML
    private AnchorPane registration;
    @FXML
    private JFXTextField FirstName;
    @FXML
    private JFXTextField SecondName;
    @FXML
    private JFXTextField DateofBirth;
    @FXML
    private JFXTextField Phonenumber;
    @FXML
    private JFXTextField IDNumber;
    @FXML
    private JFXTextField PatientID;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton Reset;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXComboBox<String> gendertype;

    ObservableList<String> showtype = FXCollections.observableArrayList(
        "Male", "Female");
    
    Message msg = new Message();

    @FXML
     public void initialize(URL location, ResourceBundle resources) {
    gendertype.setItems(showtype);
    gendertype.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> gendertype.setItems(showtype));
     }
    
     
    @FXML
    void cleardata(ActionEvent event) {
        FirstName.clear();
        SecondName.clear();
        DateofBirth.clear();
        Phonenumber.clear();
        IDNumber.clear();
        PatientID.clear();
    }
    
    private PreparedStatement ps;
    PreparedStatement pst;
    connection con;
    connection conClass = new connection();
    connection conObj = new connection();
    
    
     @FXML
    void savedata(ActionEvent event) throws SQLException, ClassNotFoundException {

        Connection connect = null;
    Statement s = null;
 
    try {
    Class.forName("com.mysql.jdbc.Driver");
 
    connect = DriverManager.getConnection(""
    + "jdbc:mysql://localhost/hospitaldb"
    + "?user=root&password=");
 
    s = connect.createStatement();
 
// SQL Insert
    String sql = "INSERT INTO patient "
            + "(FirstName,SecondName,DOB,Gender,Phonenumber,IDNumber,PatientID) "
            + "VALUES ('" 
            + FirstName.getText() + "','"
            + SecondName.getText() + "','"
            + DateofBirth.getText() + "','"
            + gendertype.getValue() + "','"
            + Phonenumber.getText() + "','"
            + IDNumber.getText() + "','"
            + PatientID.getText() +"') ";
    s.execute(sql);
            msg.setMessage("DATA SAVED");

    } catch (Exception e) {
    // TODO Auto-generated catch block
    JOptionPane.showMessageDialog(null, e.getMessage());
    e.printStackTrace();
        msg.setMessage("DATA NOT SAVED");

    }
 
    try {
    if (s != null) {
    s.close();
    connect.close();
     }
    } 
    catch (SQLException e) {
    // TODO Auto-generated catch block
    System.out.println(e.getMessage());
    e.printStackTrace();
     }

    System.out.println("Data Inserted");
    }
    
    
    
    @FXML
    void searchdata(ActionEvent event) throws ClassNotFoundException{
    PreparedStatement pst;
    connection con;
    connection conClass = new connection();
    connection conObj = new connection();
       Connection connect = null;
    Statement s = null;
    
    try {
    Class.forName("com.mysql.jdbc.Driver");

    connect = DriverManager.getConnection(""
    + "jdbc:mysql://localhost/hospitaldb"
    + "?user=root&password=");
 
    s = connect.createStatement();
	String sql = "SELECT * FROM patient WHERE PatientID = '" + PatientID.getText() + "'";
	patient patient;
        System.out.println("patientdb connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    patient = new patient();
		    patient.FirstName = rs.getString("FirstName");
		    patient.SecondName = rs.getString("SecondName");
                    patient.DateofBirth = rs.getString("DOB");
		    patient.gendertype = rs.getString("Gender");
                    patient.Phonenumber = rs.getString("Phonenumber");
                    patient.IDNumber = rs.getString("IDNumber");
                    patient.PatientID = rs.getString("PatientID");
                    
		   FirstName.setText(patient.FirstName);
                   SecondName.setText(patient.SecondName);
                   DateofBirth.setText(patient.DateofBirth);
                   gendertype.setValue(patient.gendertype);
                   Phonenumber.setText(patient.Phonenumber);
                   IDNumber.setText(patient.IDNumber);
                   PatientID.setText(patient.PatientID);

                   s.close();
                 connect.close();
		} else {
                    msg.setMessage("DATA NOT FOUND");
		}
	    } catch(SQLException e) {
		System.out.println(e.getMessage());
                    System.out.println("patientdb not connected");

	    }

    }
   
    
    
    @FXML
    void updatedata(ActionEvent event) throws ClassNotFoundException, SQLException {
    PreparedStatement pst = null;
    connection con = null;
    connection conClass = new connection();
    connection conObj = new connection();
       Connection connect = null;
    Statement s = null;
    
    try {
    Class.forName("com.mysql.jdbc.Driver");

    connect = DriverManager.getConnection(""
    + "jdbc:mysql://localhost/hospitaldb"
    + "?user=root&password=");
     System.out.println("patientdb for update connected");

    s = connect.createStatement();
    
	patient patient = null;
        String sql = "UPDATE patient SET "+
                "FirstName = ?, " +
                "SecondName = ? ," +
                "DOB = ?, " +
                "Gender = ?, " +
                "Phonenumber = ?, " +
                "IDNumber = ? " +
                "WHERE PatientID = ? ";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
	    psttmt.setString(4, gendertype.getValue().toString());
	    psttmt.setString(1,FirstName.getText().toString());
            psttmt.setString(2, SecondName.getText().toString());
	    psttmt.setString(3, DateofBirth.getText().toString());
            psttmt.setString(5, Phonenumber.getText().toString());
            psttmt.setString(6, IDNumber.getText().toString());
            psttmt.setString(7, PatientID.getText().toString());
	    psttmt.executeUpdate();
            
            msg.setMessage("DATA UPDATED");

	} catch(SQLException e) {
	    System.out.println(e.getMessage());
            msg.setMessage("DATA NOT UPDATED");
            
	} 
         s.close();
    connect.close();
	
	    }
    catch(SQLException e) {
		System.out.println(e.getMessage());
                    System.out.println("patientdb for update not connected");

	    }
}

    
    class patient  {
    public String FirstName;
    public String SecondName;
    public String DateofBirth;
    public String gendertype;
    public String Phonenumber;
    public String IDNumber;
    public String PatientID;

    }
        }
    

