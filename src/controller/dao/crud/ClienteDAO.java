/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsClienteSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Cliente;

/**
 *
 * @author Codeiro
 */
public class ClienteDAO extends ConnectionDAOImpl {
    
    public ClienteDAO(){
        super();
    }
    
    public boolean save (Cliente c){
        boolean result = true;
        
        String sql = ConstantsClienteSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getEmail());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Cliente c){
        boolean result = true;
        
        String sql = ConstantsClienteSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getEmail());
            stmt.setInt(6, c.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(Cliente c){
        boolean result = true;
        
        String sql = ConstantsClienteSQL.DELETE;
        
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
    
    public List<Cliente> listar(){
        String sql = ConstantsClienteSQL.LISTAR;
        
        List<Cliente> clienteList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setEmail(rs.getString("EMAIL"));
                
                clienteList.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return clienteList;
    }
    
}
