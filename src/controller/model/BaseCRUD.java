/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import java.sql.SQLException;
import model.ModelObject;

/**
 *
 * @author Codeiro
 */
public interface BaseCRUD {
    
    boolean execute(ModelObject obj, String sql);
    
    void save(ModelObject obj, String sql) throws SQLException;
    void update(ModelObject obj, String sql) throws SQLException;
    void delete(ModelObject obj, String sql) throws SQLException;
}
