/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAOImpl;
import controller.model.ReadOnlyCRUD;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Codeiro
 */
public class ReadOnlyDAO extends ConnectionDAOImpl implements ReadOnlyCRUD {
    
    public ReadOnlyDAO (){
        super();
    }
    
    public boolean execute(ObservableList<Object> objList, String sql)
    {
        getConnection();
        try
        {
            if(sql.toUpperCase().contains("*"))
            {
                listar(objList, sql);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Read DAO: " + ex);
            return false;
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return true;
    }
    
    public void listar(ObservableList<Object> objectList, String sql) throws SQLException
    {
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while(rs.next())
        {
            HashMap<Integer, String> objectMap = new HashMap<>();
            for(int i=1; i<=rs.getMetaData().getColumnCount(); i++)
            {
                objectMap.put(i, rs.getString(i));
            }
            objectList.add(objectMap);
        }
    }
}
