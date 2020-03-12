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
public class PharmacyController implements Initializable {
    @FXML
    private AnchorPane pharmacy;
    @FXML
    private JFXTextField PatientName;
    @FXML
    private JFXTextField PatientID;
    @FXML
    private JFXTextField DoctorName;
    @FXML
    private JFXTextField DoctorID;
    @FXML
    private JFXTextField DrugName;
    @FXML
    private JFXTextField DrugAmount;
    @FXML
    private JFXComboBox<String> drugtype;
    @FXML
    private JFXTextField PharmacistID;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton Reset;
    @FXML
    private JFXButton Update;
    

    ObservableList<String> showtype = FXCollections.observableArrayList(
        "pieces", "boxes");
    
    Message msg = new Message();

    @FXML
     public void initialize(URL location, ResourceBundle resources) {

    drugtype.setItems(showtype);
    drugtype.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> drugtype.setItems(showtype));
    }
    
     
     
     
     @FXML
    void cleardata(ActionEvent event) {
        PatientName.clear();
        PatientID.clear();
        DoctorName.clear();
        DoctorID.clear();
        DrugName.clear();
        DrugAmount.clear();
        PharmacistID.clear();
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
	String sql = "SELECT * FROM pharmacy WHERE PatientID = '" + PatientID.getText() + "'";
	pharmacy pharmacy;
        System.out.println("pharmacydb connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    pharmacy = new pharmacy();
		    pharmacy.PatientID = rs.getString("PatientID");
                    pharmacy.PatientName = rs.getString("PatientName");
                    pharmacy.DoctorName = rs.getString("DoctorName");
                    pharmacy.DoctorID = rs.getString("DoctorID");
                    pharmacy.DrugName = rs.getString("DrugName");
		    pharmacy.DrugAmount = rs.getString("DrugAmount");
		    pharmacy.drugtype = rs.getString("Type");
                    pharmacy.PharmacistID = rs.getString("PharmacistID");
                    pharmacy.PatientID = rs.getString("PatientID");
                    
		   PatientID.setText(pharmacy.PatientID);
                   PatientName.setText(pharmacy.PatientName);
                   DoctorName.setText(pharmacy.DoctorName);
                   DoctorID.setText(pharmacy.DoctorID);
                   DrugName.setText(pharmacy.DrugName);
                   DrugAmount.setText(pharmacy.DrugAmount);
                   drugtype.setValue(pharmacy.drugtype);
                   PharmacistID.setText(pharmacy.PharmacistID);
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
     System.out.println("pharmacydb for update connected");

    s = connect.createStatement();
    
	StoreController.store store = null;
        String sql = "UPDATE pharmacy SET "+
                "PatientName = ?, " +
                "DoctorName = ?, " +
                "DoctorID = ?, " +
                "DrugName = ?, " +
                "DrugAmount = ?, " +
                "Type = ?, " +
                "PharmacistID = ? " +
                "WHERE PatientID = ?";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
            psttmt.setString(1, PatientName.getText().toString());
            psttmt.setString(2, DoctorName.getText().toString());
            psttmt.setString(3, DoctorID.getText().toString());
            psttmt.setString(4, DrugName.getText().toString());
	    psttmt.setString(5, DrugAmount.getText().toString());
            psttmt.setString(6, drugtype.getValue().toString());
            psttmt.setString(7, PharmacistID.getText().toString());
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
                    System.out.println("pharmacydb for update not connected");

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
 
    String sql = "INSERT INTO pharmacy "
            + "(PatientName,PatientID,DoctorName,DoctorID,DrugName,DrugAmount,Type,PharmacistID) "
            + "VALUES ('" 
            + PatientName.getText() + "','"
            + PatientID.getText() + "','"
            + DoctorName.getText() + "','" 
            + DoctorID.getText() + "','"
            + DrugName.getText() + "','"
            + DrugAmount.getText() + "','"
            + drugtype.getValue() + "','"
            + PharmacistID.getText() + "') ";
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



class pharmacy  {
    public String PatientName;
    public String PatientID;
    public String DoctorName;
    public String DoctorID;
    public String DrugName;
    public String DrugAmount;
    public String drugtype;
    public String PharmacistID;

    }

