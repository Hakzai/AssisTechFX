/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Codeiro
 */
public class ConnectionDAO{
    
    protected Connection conn = null;
    protected PreparedStatement stmt = null;
    protected ResultSet rs;
    
    public ConnectionDAO(){
        conn = ConnectionFactory.getConnection();
        rs = null;
    }
    
    public void getConnection (){ 
        try{
            if(conn.isClosed())
                conn = ConnectionFactory.getConnection();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ConnectionDAO :" + ex);
        }
    }
}
