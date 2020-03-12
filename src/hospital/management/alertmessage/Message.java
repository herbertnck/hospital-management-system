/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.alertmessage;

import javafx.scene.control.Alert;

/**
 *
 * @author Herbert
 */
public class Message {
         public void setMessage(String str)
          {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
          //    alert.setHeaderText("Alert Message");
              alert.setContentText(str);
              alert.show();
          }
}
