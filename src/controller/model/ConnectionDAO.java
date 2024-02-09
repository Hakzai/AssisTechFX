/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Codeiro
 */
public abstract class ConnectionDAO {
    
    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    
    public abstract void getConnection();
    
}
