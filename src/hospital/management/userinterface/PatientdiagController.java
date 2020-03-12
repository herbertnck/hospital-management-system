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
public class PatientdiagController implements Initializable {
    @FXML
    private AnchorPane patientdiag;
    @FXML
    private JFXTextField PatientName;
    @FXML
    private JFXTextField PatientID;
    @FXML
    private JFXTextField DoctorName;
    @FXML
    private JFXTextField DoctorID;
    @FXML
    private JFXTextField Age;
    @FXML
    private JFXTextField DiagDetails;
    @FXML
    private JFXComboBox<String> gendertype;
    @FXML
    private JFXTextField Remarks;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton Reset;
    @FXML
    private JFXButton Update;
    

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
        PatientName.clear();
        PatientID.clear();
        DoctorName.clear();
        DoctorID.clear();
        Age.clear();
        DiagDetails.clear();
        Remarks.clear();
    }

    
    private PreparedStatement ps;
    PreparedStatement pst;
    connection con;
    connection conClass = new connection();
    connection conObj = new connection();
    
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
	String sql = "SELECT * FROM patientdiagnosis WHERE PatientID = '" + PatientID.getText() + "'";
	patientdiagnosis patientdiagnosis;
        System.out.println("patientdiagnosis connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    patientdiagnosis = new patientdiagnosis();
		    patientdiagnosis.PatientID = rs.getString("PatientID");
                    patientdiagnosis.PatientName = rs.getString("PatientName");
                    patientdiagnosis.DoctorName = rs.getString("DoctorName");
                    patientdiagnosis.DoctorID = rs.getString("DoctorID");
                    patientdiagnosis.Age = rs.getString("Age");
		    patientdiagnosis.DiagDetails = rs.getString("DiagDetails");
		    patientdiagnosis.gendertype = rs.getString("Gender");
                    patientdiagnosis.Remarks = rs.getString("Remarks");
                    patientdiagnosis.PatientID = rs.getString("PatientID");
                    
		   PatientID.setText(patientdiagnosis.PatientID);
                   PatientName.setText(patientdiagnosis.PatientName);
                   DoctorName.setText(patientdiagnosis.DoctorName);
                   DoctorID.setText(patientdiagnosis.DoctorID);
                   Age.setText(patientdiagnosis.Age);
                   DiagDetails.setText(patientdiagnosis.DiagDetails);
                   gendertype.setValue(patientdiagnosis.gendertype);
                   Remarks.setText(patientdiagnosis.Remarks);
         s.close();
        connect.close();
		} 
        else {
           msg.setMessage("DATA NOT FOUND");
	}
	    } 
    
        catch(SQLException e) {
		System.out.println(e.getMessage());
                    System.out.println("patientdiagnosis not connected");
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
     System.out.println("patientdiagnosis for update connected");

    s = connect.createStatement();
    
	StoreController.store store = null;
        String sql = "UPDATE patientdiagnosis SET "+
                "PatientName = ?, " +
                "DoctorName = ?, " +
                "DoctorID = ?, " +
                "Gender = ?, " +
                "Age = ?, " +
                "DiagDetails = ?, " +
                "Remarks = ? " +
                "WHERE PatientID = ?";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
            psttmt.setString(1, PatientName.getText().toString());
            psttmt.setString(2, DoctorName.getText().toString());
            psttmt.setString(3, DoctorID.getText().toString());
            psttmt.setString(5, Age.getText().toString());
	    psttmt.setString(6, DiagDetails.getText().toString());
            psttmt.setString(4, gendertype.getValue().toString());
            psttmt.setString(7, Remarks.getText().toString());
	    psttmt.setString(8, PatientID.getText().toString());
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
                    System.out.println("patientdiagnosisdb for update not connected");

	    }
}
    
    
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
 
    String sql = "INSERT INTO patientdiagnosis "
            + "(PatientName,PatientID,DoctorName,DoctorID,Gender,Age,DiagDetails,Remarks) "
            + "VALUES ('" 
            + PatientName.getText() + "','"
            + PatientID.getText() + "','"
            + DoctorName.getText() + "','" 
            + DoctorID.getText() + "','"
            + gendertype.getValue() + "','"
            + Age.getText() + "','"
            + DiagDetails.getText() + "','"
            + Remarks.getText() + "') ";
    
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
    } catch (SQLException e) {
    // TODO Auto-generated catch block
    System.out.println(e.getMessage());
    e.printStackTrace();
    }
 
        System.out.println("Data Inserted");
    }

}



class patientdiagnosis  {
    public String PatientName;
    public String PatientID;
    public String DoctorName;
    public String DoctorID;
    public String Age;
    public String DiagDetails;
    public String gendertype;
    public String Remarks;

    }

