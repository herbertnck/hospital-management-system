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
public class AccountsController implements Initializable {
    @FXML
    private AnchorPane accounts;
    @FXML
    private JFXTextField PatientName;
    @FXML
    private JFXTextField PatientID;
    @FXML
    private JFXTextField DoctorName;
    @FXML
    private JFXTextField DoctorID;
    @FXML
    private JFXTextField BillDetails;
    @FXML
    private JFXTextField BillAmount;
    @FXML
    private JFXTextField AccountantID;
    @FXML
    private JFXButton Reset;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton Save;
    

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
    String sql = "INSERT INTO accounts "
            + "(PatientName,PatientID,DoctorName,DoctorID,BillAmount,BillDetails,AccountantID) "
            + "VALUES ('" 
            + PatientName.getText() + "','"
            + PatientID.getText() + "','"
            + DoctorName.getText() + "','" 
            + DoctorID.getText() + "','"
            + BillAmount.getText() + "','"
            + BillDetails.getText() + "','"
            + AccountantID.getText() + "') ";
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
            msg.setMessage("DATA NOT SAVED");

    }
 
       System.out.println("Data Inserted");
    }

    
    
    @FXML
    void cleardata(ActionEvent event) {
        PatientName.clear();
        PatientID.clear();
        DoctorName.clear();
        DoctorID.clear();
        BillAmount.clear();
        BillDetails.clear();
        AccountantID.clear();
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
	String sql = "SELECT * FROM accounts WHERE PatientID = '" + PatientID.getText() + "'";
	accounts accounts;
        System.out.println("accountsdb connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    accounts = new AccountsController.accounts();
		    accounts.PatientName = rs.getString("PatientName");
		    accounts.PatientID = rs.getString("PatientID");
		    accounts.DoctorName = rs.getString("DoctorName");
                    accounts.DoctorID = rs.getString("DoctorID");
                    accounts.BillAmount = rs.getString("BillAmount");
                    accounts.BillDetails = rs.getString("BillDetails");
                    accounts.AccountantID = rs.getString("AccountantID");
		   PatientName.setText(accounts.PatientName);
                   PatientID.setText(accounts.PatientID);
                   DoctorName.setText(accounts.DoctorName);
                   DoctorID.setText(accounts.DoctorID);
                   BillAmount.setText(accounts.BillAmount);
                   BillDetails.setText(accounts.BillDetails);
                   AccountantID.setText(accounts.AccountantID);
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
     System.out.println("accounts for update connected");

    s = connect.createStatement();
    
	accounts accounts = null;
        String sql = "UPDATE accounts SET "+
                "PatientName = ?, " +
                "DoctorName = ?, " +
                "DoctorID = ?, " +
                "BillAmount = ?, " +
                "BillDetails = ?, " +
                "AccountantID = ? " +
                "WHERE PatientID = ? ";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
	    psttmt.setString(1, PatientName.getText().toString());
	    psttmt.setString(4, BillAmount.getText().toString());
	    psttmt.setString(2, DoctorName.getText().toString());
            psttmt.setString(3, DoctorID.getText().toString());
            psttmt.setString(5, BillDetails.getText().toString());
            psttmt.setString(6, AccountantID.getText().toString());
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
                    System.out.println("accountsdb for update not connected");

	    }
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    class accounts {
         public String PatientName;
         public String PatientID;
         public String DoctorName;
         public String DoctorID;
         public String BillNO;
         public String BillAmount;
         public String BillDetails;
         public String AccountantID;
      }
    
}
