/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsClienteSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Cliente;

/**
 *
 * @author Codeiro
 */
public class ClienteUtils extends ClienteDAO {
    
    public ClienteUtils() {
        super();
    }        
    
    public List<Cliente> getIdClientes(){
        String sql = ConstantsClienteSQL.GET_ID_CLIENTES;
        
        List<Cliente> idsList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                
                idsList.add(cliente);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getIdClientes: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return idsList;
        
    }
    
    public List<Cliente> findByName(String name){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsClienteSQL.FIND_BY_NAME);
        sqlBuilder = sqlBuilder.append(name).append("%'");
        
        String sql = sqlBuilder.toString();
        
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
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return clienteList;
    }
    
    public Cliente getClienteById(int id){
        String sql = ConstantsClienteSQL.GET_CLIENTE_BY_ID;
        
        Cliente cliente = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                cliente = new Cliente();
                
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setEmail(rs.getString("EMAIL"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getClienteById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return cliente;
    }
    
}
