/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.connection.ConnectionFactory;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import controller.model.ConnectionDAO;

/**
 *
 * @author Codeiro
 */
public class ConnectionDAOImpl extends ConnectionDAO{
    
    public ConnectionDAOImpl()
    {
        conn = ConnectionFactory.getConnection();
        rs = null;
    }
    
    public void getConnection ()
    { 
        try{
            if(conn.isClosed())
                conn = ConnectionFactory.getConnection();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ConnectionDAO :" + ex);
        }
    }
}
