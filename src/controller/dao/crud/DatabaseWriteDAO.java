/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAOImpl;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import model.ModelObject;
import controller.model.BaseCRUD;

/**
 *
 * @author Codeiro
 */
public class DatabaseWriteDAO extends ConnectionDAOImpl implements BaseCRUD{
    
    public DatabaseWriteDAO(){
        super();
    }
    
    public boolean execute(ModelObject obj, String sql)
    {
        getConnection();
        try
        {
            if(sql.toUpperCase().contains("INSERT"))
            {
                save(obj, sql);
            }
            else if(sql.toUpperCase().contains("UPDATE"))
            {
                update(obj, sql);
            }
            else if(sql.toUpperCase().contains("DELETE"))
            {
                delete(obj, sql);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Write DAO: " + ex);
            return false;
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return true;
    }
    
    public void save (ModelObject object, String sql) throws SQLException
    {
        stmt = conn.prepareStatement(sql);

        int i = 1;
        for(String var : object.getModelVars())
        {
            stmt.setString(i, var);
            i++;
        }            

        stmt.executeUpdate();
    }
    
    public void update (ModelObject object, String sql) throws SQLException
    {
        stmt = conn.prepareStatement(sql);

        int i = 1;
        for(String var : object.getModelVars())
        {
            stmt.setString(i, var);
            i++;
        }                        
        stmt.setString(i, object.getModelPK().get("id"));

        stmt.executeUpdate();
    }
    
    public void delete(ModelObject object, String sql) throws SQLException
    {
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, object.getModelPK().get("id"));
        stmt.executeUpdate();
    }
}
