/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.userinterface;

import com.mysql.fabric.xmlrpc.base.Value;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Herbert
 */
public class Store {
//    IntegerProperty tid = new SimpleIntegerProperty();
//    StringProperty tdrugname = new SimpleStringProperty();
//    StringProperty tdrugamount = new SimpleStringProperty();
//    StringProperty ttype = new SimpleStringProperty();
//    StringProperty ttime = new SimpleStringProperty();

       public IntegerProperty tid;
        public void settid(int value){
            tidProperty().set(value);
        }
        public int gettid(){
            return tidProperty().get();
        }
        public IntegerProperty tidProperty(){
            if (tid == null){
                tid = new SimpleIntegerProperty(this, "DrugID");
            }
            return tid;
        }
        
        public StringProperty tdrugname;
        public void settdrugname(String value){
            tdrugnameProperty().set(value);
        }
        public String gettdrugname(){
            return tdrugnameProperty().get();
        }
        public StringProperty tdrugnameProperty(){
            if (tdrugname == null){
                tdrugname = new SimpleStringProperty(this, "DrugName");
            }
            return tdrugname;
        }
        
//    public IntegerProperty tidProperty() { //name should be exactly like this [IntegerProperty variable name (id) + (Property) = idProperty] (case sensitive)
//            return tid;
//        }
//
//        public StringProperty tdrugnameProperty() {
//            return tdrugname;
//        }
//
//        public StringProperty tdrugamountProperty() {
//            return tdrugamount;
//        }
//        
//        public StringProperty ttypeProperty() {
//            return ttype;
//        }
//        
//        public StringProperty ttimeProperty() {
//            return ttime;
//        }
//        
//        public Store(int DrugIDValue, String DrugNameValue, String AmountValue, String TypeValue, String TimeValue) 
//        {
//            tid.set(DrugIDValue);
//            tdrugname.set(DrugNameValue);
//            tdrugamount.set(AmountValue);
//            ttype.set(TypeValue);
//            ttime.set(TimeValue);
//        }

//        Store(){}

//    private final SimpleStringProperty tdrugname;
//    private final SimpleStringProperty tdrugamount;
//    private final SimpleStringProperty ttype;
//    private final SimpleStringProperty ttime;
//    
//    Store(String tdrugname,String tdrugamount,String ttype,String ttime)
//    {
//        this.tdrugname = new SimpleStringProperty(tdrugname);
//        this.tdrugamount = new SimpleStringProperty(tdrugamount);
//        this.ttype = new SimpleStringProperty(ttype);
//        this.ttime = new SimpleStringProperty(ttime);
//    }
//    
//    public String getTdrugname()
//    {
//        return tdrugname.get();
//    }
//    public void setTdrugname(String tdrugname)
//    {
//        this.tdrugname.set(tdrugname);
//    }
//    
//    public String getTdrugamount()
//    {
//        return tdrugamount.get();
//    }
//    public void setTdrugamount(String tdrugamount)
//    {
//        this.tdrugamount.set(tdrugamount);
//    }
//    
//    public String getTtype()
//    {
//        return ttype.get();
//    }
//    public void setTtype(String ttype)
//    {
//        this.ttype.set(ttype);
//    }
//    
//    public String getTtime()
//    {
//        return ttime.get();
//    }
//    public void setTtime(String ttime)
//    {
//        this.ttime.set(ttime);
//    }

    
    
}
