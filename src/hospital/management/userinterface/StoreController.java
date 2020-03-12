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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Herbert
 */
public class StoreController implements Initializable {
    @FXML
    private AnchorPane store;
    @FXML
    private JFXTextField DrugName;
    @FXML
    private JFXTextField DrugAmount;
    @FXML
    private JFXTextField StorekeeperID;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Search;
    @FXML
    private JFXButton Reset;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXComboBox<String> drugtype;

    ObservableList<String> showtype = FXCollections.observableArrayList(
        "Pieces", "Boxes");
    
    Message msg = new Message();

    @FXML
     public void initialize(URL location, ResourceBundle resources) {
    drugtype.setItems(showtype);
    drugtype.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> drugtype.setItems(showtype));
     }
    
     
    @FXML
    void cleardata(ActionEvent event) {
        DrugName.clear();
        DrugAmount.clear();
        StorekeeperID.clear();
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
    String sql = "INSERT INTO store "
            + "(DrugName,Amount,Type,StorekeeperID) "
            + "VALUES ('" 
            + DrugName.getText() + "','"
            + DrugAmount.getText() + "','"
            + drugtype.getValue() + "','"
            + StorekeeperID.getText() +"') ";
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
	String sql = "SELECT * FROM store WHERE DrugName = '" + DrugName.getText() + "'";
	store store;
        System.out.println("soredb connected");

        ResultSet rs;
           rs = s.executeQuery(sql);
		if(rs.next()) { // If the record was found
		    store = new store();
		    store.DrugName = rs.getString("DrugName");
		    store.DrugAmount = rs.getString("Amount");
		    store.drugtype = rs.getString("Type");
                    store.StorekeeperID = rs.getString("StorekeeperID");
                    
		   DrugName.setText(store.DrugName);
                   DrugAmount.setText(store.DrugAmount);
                   drugtype.setValue(store.drugtype);
                   StorekeeperID.setText(store.StorekeeperID);
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
     System.out.println("soredb for update connected");

    s = connect.createStatement();
    
	store store = null;
        String sql = "UPDATE store SET "+
                "Amount = ?, " +
                "Type = ? ," +
                "StorekeeperID = ? " +
                "WHERE DrugName = ? ";

       
       try(PreparedStatement psttmt = connect
               .prepareStatement(sql)) {
	    psttmt.setString(2, drugtype.getValue().toString());
	    psttmt.setInt(1,Integer.parseInt(DrugAmount.getText()));
            psttmt.setInt(3, Integer.parseInt(StorekeeperID.getText()));
	    psttmt.setString(4, DrugName.getText().toString());
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

    
    class store  {
    public String DrugName;
    public String DrugAmount;
    public String drugtype;
    public String StorekeeperID; 

    }
        }
    

