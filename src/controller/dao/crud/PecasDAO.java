/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsPecasSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Pecas;

/**
 *
 * @author Codeiro
 */
public class PecasDAO extends ConnectionDAO {
    
    public PecasDAO(){
        super();
    }
    
    public boolean save (Pecas p){
        boolean result = true;
        
        String sql = ConstantsPecasSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getMarca());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getIdFornecedor());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Pecas p){
        super.getConnection();
        
        boolean result = true;
        
        String sql = ConstantsPecasSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getMarca());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getIdFornecedor());
            stmt.setInt(5, p.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(Pecas p){
        boolean result = true;
        
        String sql = ConstantsPecasSQL.DELETE;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<Pecas> listar(){
        String sql = ConstantsPecasSQL.LISTAR;
                
        List<Pecas> pecasList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Pecas pecas = new Pecas();
                
                pecas.setId(rs.getInt("ID"));
                pecas.setNome(rs.getString("NOME"));
                pecas.setMarca(rs.getString("MARCA"));
                pecas.setQuantidade(rs.getInt("QUANTIDADE"));
                pecas.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
                
                pecasList.add(pecas);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return pecasList;
    }
    
}
