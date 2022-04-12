/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsFornecedorSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Fornecedor;

/**
 *
 * @author Codeiro
 */
public class FornecedorDAO extends ConnectionDAO {
    
    public FornecedorDAO(){
        super();
    }
    
    public boolean save (Fornecedor f){
        boolean result = true;
        
        String sql = "";
        
        if(null != f.getDataPrimeiraCompra())
            sql = ConstantsFornecedorSQL.SAVE;
        else
            sql = ConstantsFornecedorSQL.SAVE_NO_DATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getTelefone());
            
            if(null != f.getDataPrimeiraCompra())
                stmt.setString(4, f.getDataPrimeiraCompra());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Fornecedor f){
        boolean result = true;
        
        String sql = ConstantsFornecedorSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getTelefone());
            stmt.setString(4, f.getDataPrimeiraCompra());
            stmt.setInt(5, f.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(Fornecedor f){
        boolean result = true;
        
        String sql = ConstantsFornecedorSQL.DELETE;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<Fornecedor> listar(){
        String sql = ConstantsFornecedorSQL.LISTAR;
        
        List<Fornecedor> fornecedorList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setId(rs.getInt("ID"));
                fornecedor.setNome(rs.getString("NOME"));
                fornecedor.setCnpj(rs.getString("CNPJ"));
                fornecedor.setTelefone(rs.getString("TELEFONE"));
                fornecedor.setDataPrimeiraCompra(rs.getString("DATA_PRIMEIRA_COMPRA"));
                
                fornecedorList.add(fornecedor);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return fornecedorList;
    }
    
}
