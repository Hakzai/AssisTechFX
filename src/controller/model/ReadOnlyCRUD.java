/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Codeiro
 */
public interface ReadOnlyCRUD {
    
    boolean execute(ObservableList<Object> objList, String sql);
    
    void listar(ObservableList<Object> objectList, String sql) throws SQLException;
    
}
