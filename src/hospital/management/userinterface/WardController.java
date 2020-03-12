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
public class WardController implements Initializable {
    @FXML
    private AnchorPane laboratory;
    @FXML
    private JFXTextField PatientName;
    @FXML
    private JFXTextField PatientID;
    @FXML
    private JFXTextField PhoneNumber;
    @FXML
    private JFXTextField Ward;
    @FXML
    private JFXTextField BedNo;
    @FXML
    private JFXTextField MedicalCondition;
     @FXML
    private JFXTextField PatientMedication;
    @FXML
    private JFXTextField KinName;
    @FXML
    private JFXTextField KinNo;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton Reset;

    Message msg = new Message();

    private PreparedStatement ps;
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
    String sql = "INSERT INTO inpatient "
            + "(PatientName,PatientID,PhoneNumber,Ward,BedNo,MedicalCondition,PatientMedication,KinName,KinNo) "
            + "VALUES ('" 
            + PatientName.getText() + "','"
            + PatientID.getText() + "','"
            + PhoneNumber.getText() + "','" 
            + Ward.getText() + "','"
            + BedNo.getText() + "','"
            + MedicalCondition.getText() + "','"
            + PatientMedication.getText() + "','"
            + KinName.getText() + "','"
            + KinNo.getText() + "') ";
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
           msg.setMessage("DATA SAVED");

    }
   
    
    @FXML
    void cleardata(ActionEvent event) {
        PatientName.clear();
        PatientID.clear();
        PhoneNumber.clear();
        Ward.clear();
        BedNo.clear();
        MedicalCondition.clear();
        PatientMedication.clear();
        KinName.clear();
        KinNo.clear();
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
	String sql = "SELECT * FROM inpatient WHERE PatientID = '" + PatientID.getText() + "'";
	inpatient inpatient;
        System.out.println("pharmacydb connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    inpatient = new inpatient();
		    inpatient.PatientID = rs.getString("PatientID");
		    inpatient.PatientName = rs.getString("PatientName");
		    inpatient.PhoneNumber = rs.getString("PhoneNumber");
                    inpatient.Ward = rs.getString("Ward");
                    inpatient.BedNo = rs.getString("BedNo");
                    inpatient.MedicalCondition = rs.getString("MedicalCondition");
                    inpatient.PatientMedication = rs.getString("PatientMedication");
                    inpatient.KinName = rs.getString("KinName");
                    inpatient.KinNo = rs.getString("KinNo");
                    
		   PatientID.setText(inpatient.PatientID);
                   PatientName.setText(inpatient.PatientName);
                   PhoneNumber.setText(inpatient.PhoneNumber);
                   Ward.setText(inpatient.Ward);
                   BedNo.setText(inpatient.BedNo);
                   MedicalCondition.setText(inpatient.MedicalCondition);
                   PatientMedication.setText(inpatient.PatientMedication);
                   KinName.setText(inpatient.KinName);
                   KinNo.setText(inpatient.KinNo);
                   
         s.close();
    connect.close();
		} else {
                    msg.setMessage("DATA NOT FOUND");
		}
	    } catch(SQLException e) {
		System.out.println(e.getMessage());
                    System.out.println("pharmacydb not connected");

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
     System.out.println("inpatientdb for update connected");

    s = connect.createStatement();
    
	inpatient inpatient = null;
        String sql = "UPDATE inpatient SET "+
                "PatientName = ?, " +
                "PhoneNumber = ?, " +
                "Ward = ?, " +
                "BedNo = ?, " +
                "MedicalCondition = ?, " +
                "PatientMedication = ?, " +
                "KinName = ?, " +
                "KinNo = ? " +
                "WHERE PatientID = ?";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
	    psttmt.setString(1, PatientName.getText().toString());
	    psttmt.setString(2,PhoneNumber.getText().toString());
            psttmt.setString(3, Ward.getText().toString());
            psttmt.setString(4, BedNo.getText().toString());
            psttmt.setString(5, MedicalCondition.getText().toString());
            psttmt.setString(6, PatientMedication.getText().toString());
            psttmt.setString(7, KinName.getText().toString());
            psttmt.setString(8, KinNo.getText().toString());
	    psttmt.setString(9, PatientID.getText().toString());
            
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
                    System.out.println("inpatientdb for update not connected");

	    }
}
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
class inpatient  {
    public String PatientName;
    public String PatientID;
    public String PhoneNumber;
    public String Ward;
    public String BedNo;
    public String MedicalCondition; 
    public String PatientMedication; 
    public String KinName; 
    public String KinNo; 
    }
}
