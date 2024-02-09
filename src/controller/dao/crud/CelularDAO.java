/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsCelularSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Celular;

/**
 *
 * @author Codeiro
 */
public class CelularDAO extends ConnectionDAOImpl {
    
    public CelularDAO(){
        super();
    }
    
    public boolean save (Celular c){
        boolean result = true;
        
        String sql = ConstantsCelularSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, c.getModelo());
            stmt.setString(2, c.getMarca());
            stmt.setString(3, c.getAno());
            stmt.setString(4, c.getSerial());
            stmt.setString(5, c.getEstadoUso());
            stmt.setString(6, c.getIsPropriedade());
            stmt.setInt(7, c.getIdCliente());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Celular c){
        boolean result = true;
        
        String sql = ConstantsCelularSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, c.getModelo());
            stmt.setString(2, c.getMarca());
            stmt.setString(3, c.getAno());
            stmt.setString(4, c.getSerial());
            stmt.setString(5, c.getEstadoUso());
            stmt.setString(6, c.getIsPropriedade());
            stmt.setInt(7, c.getIdCliente());
            stmt.setInt(8, c.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(Celular c){
        boolean result = true;
        
        String sql = ConstantsCelularSQL.DELETE;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<Celular> listar(){
        String sql = ConstantsCelularSQL.LISTAR;
        
        List<Celular> celularList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Celular celular = new Celular();
                
                celular.setId(rs.getInt("ID"));
                celular.setModelo(rs.getString("MODELO"));
                celular.setMarca(rs.getString("MARCA"));
                celular.setAno(rs.getString("ANO"));
                celular.setSerial(rs.getString("SERIAL"));
                celular.setEstadoUso(rs.getString("ESTADO_USO"));
                celular.setIsPropriedade(rs.getString("IS_PROPRIEDADE"));
                celular.setIdCliente(rs.getInt("ID_CLIENTE"));
                
                celularList.add(celular);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return celularList;
    }
    
}
