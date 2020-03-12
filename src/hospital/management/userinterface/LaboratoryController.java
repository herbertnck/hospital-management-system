/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.userinterface;

import com.jfoenix.controls.JFXButton;
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
public class LaboratoryController implements Initializable {
    @FXML
    private AnchorPane laboratory;
    @FXML
    private JFXTextField PatientName;
    @FXML
    private JFXTextField PatientID;
    @FXML
    private JFXTextField DoctorName;
    @FXML
    private JFXTextField DoctorID;
    @FXML
    private JFXTextField TestName;
    @FXML
    private JFXTextField TestResult;
    @FXML
    private JFXTextField LabtechID;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton Reset;

    Message msg = new Message();

    private PreparedStatement ps;
    PreparedStatement pst;
    connection con;
    connection conClass = new connection();

    
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
    String sql = "INSERT INTO labaratory "
            + "(PatientName,PatientID,DoctorName,DoctorID,TestName,TestResult,LabtechID) "
            + "VALUES ('" 
            + PatientName.getText() + "','"
            + PatientID.getText() + "','"
            + DoctorName.getText() + "','" 
            + DoctorID.getText() + "','"
            + TestName.getText() + "','"
            + TestResult.getText() + "','"
            + LabtechID.getText() + "') ";
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
	String sql = "SELECT * FROM labaratory WHERE PatientID = '" + PatientID.getText() + "'";
	labaratory labaratory;
        System.out.println("labaratorydb connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    labaratory = new labaratory();
		    labaratory.PatientName = rs.getString("PatientName");
		    labaratory.PatientID = rs.getString("PatientID");
		    labaratory.DoctorName = rs.getString("DoctorName");
                    labaratory.DoctorID = rs.getString("DoctorID");
                    labaratory.TestName = rs.getString("TestName");
                    labaratory.TestResult = rs.getString("TestResult");
                    labaratory.LabtechID = rs.getString("LabtechID");
                    
		   PatientName.setText(labaratory.PatientName);
                   PatientID.setText(labaratory.PatientID);
                   DoctorName.setText(labaratory.DoctorName);
                   DoctorID.setText(labaratory.DoctorID);
                   TestName.setText(labaratory.TestName);
                   TestResult.setText(labaratory.TestResult);
                   LabtechID.setText(labaratory.LabtechID);
         s.close();
    connect.close();
		} else {
                    msg.setMessage("DATA NOT FOUND");
		}
	    } catch(SQLException e) {
		System.out.println(e.getMessage());
                    System.out.println("storedb not connected");

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
     System.out.println("labaratorydb for update connected");

    s = connect.createStatement();
    
	labaratory labaratory = null;
        String sql = "UPDATE labaratory SET "+
                "PatientName = ?, " +
                "DoctorName = ?, " +
                "DoctorID = ?, " +
                "TestName = ?, " +
                "TestResult = ?, " +
                "LabtechID = ? " +
                "WHERE PatientID = ?";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
	    psttmt.setString(1, PatientName.getText().toString());
	    psttmt.setString(2,DoctorName.getText().toString());
            psttmt.setString(3, DoctorID.getText().toString());
            psttmt.setString(4, TestName.getText().toString());
            psttmt.setString(5, TestResult.getText().toString());
            psttmt.setString(6, LabtechID.getText().toString());
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
                    System.out.println("storedb for update not connected");

	    }
}
    
    @FXML
    void cleardata(ActionEvent event) {
        PatientName.clear();
        PatientID.clear();
        DoctorName.clear();
        DoctorID.clear();
        TestName.clear();
        TestResult.clear();
        LabtechID.clear();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    class labaratory {
      public String PatientName;
      public String PatientID;
      public String DoctorName;
      public String DoctorID;
      public String TestName;
      public String TestResult;
      public String LabtechID;
        }
    
}
