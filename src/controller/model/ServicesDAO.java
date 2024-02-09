/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import model.ModelObject;

/**
 *
 * @author Codeiro
 */
public abstract class ServicesDAO {
    
    public abstract void databaseUpdate(ModelObject object, String sql) throws SQLException;
    
    public abstract void databaseRead(ObservableList<Object> objectList, String sql) throws SQLException;
    
    public abstract void loadFormData() throws SQLException;
    
    public abstract void getDataFromTable() throws SQLException;
    
    public abstract Object setDataOnTable() throws SQLException;
}
