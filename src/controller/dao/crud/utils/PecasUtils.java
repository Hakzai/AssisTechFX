/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsPecasSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.PecasDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Pecas;

/**
 *
 * @author Codeiro
 */
public class PecasUtils extends PecasDAO {
    
    public PecasUtils(){
        super();
    }
    
    public List<Pecas> getIdPecass(){
        String sql = ConstantsPecasSQL.GET_ID_PECAS;
        
        List<Pecas> idsList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Pecas pecas = new Pecas();
                
                pecas.setId(rs.getInt("ID"));
                pecas.setNome(rs.getString("NOME"));
                
                idsList.add(pecas);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getIdPecass: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return idsList;
        
    }
    
    public int getFornecedorIdByName(String name){
        conn = ConnectionFactory.getConnection();
        
        String sql = ConstantsPecasSQL.GET_ID_FORNECEDOR_BY_NOME;
        int fornecedorId = -1;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, name);
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){
                fornecedorId = rs.getInt("Id_Fornecedor");
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getFornecedorIdByName: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
            rs = null;
            stmt = null;
        }
        
        return fornecedorId;
                
    }
    
    public String getFornecedorNameById(int id_fornecedor){
        conn = ConnectionFactory.getConnection();
        
        String sql = ConstantsPecasSQL.GET_NOME_FORNECEDOR_BY_ID;
        String fornecedorName = "";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id_fornecedor);
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){
                fornecedorName = rs.getString("Nome_Fornecedor");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getFornecedorNameById: " + ex);
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
            rs = null;
            stmt = null;
        }
        
        
        return fornecedorName;
    }
    
    public String getDataPrimeiraCompraById(int id_fornecedor){
        conn = ConnectionFactory.getConnection();
        
        String sql = ConstantsPecasSQL.GET_DATA_PRIMEIRA_COMPRA_BY_ID;
        String fornecedorDataPrimeiraCompra = "";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id_fornecedor);
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){
                fornecedorDataPrimeiraCompra = rs.getString("data_primeira_compra");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getDataPrimeiraCompraById: " + ex);
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
            rs = null;
            stmt = null;
        }
        
        return fornecedorDataPrimeiraCompra;
    }
    
    public boolean setDataPrimeiraCompra(int id_fornecedor){
        conn = ConnectionFactory.getConnection();
        
        boolean sqlResult = true;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // System.out.println("yyyy/MM/dd -> "+dtf.format(LocalDateTime.now())); -- EXEMPLO COMO USAR dtf
        
        String sql = ConstantsPecasSQL.SET_DATA_PRIMEIRA_COMPRA_BY_ID;
        String dateNow = dtf.format(LocalDateTime.now());
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, dateNow);
            stmt.setInt(2, id_fornecedor);
                        
            stmt.executeUpdate();
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO setDataPrimeiraCompra: " + ex);
            sqlResult = false;
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
            stmt = null;
        }
        
        return sqlResult;
    }
    
    public List<Pecas> findByName(String name){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsPecasSQL.FIND_BY_NAME);
        sqlBuilder = sqlBuilder.append(name).append("%'");
        
        String sql = sqlBuilder.toString();
        
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
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return pecasList;
    }
    
    public Pecas getPecasById(int id){
        String sql = ConstantsPecasSQL.GET_PECAS_BY_ID;                
        
        Pecas pecas = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                pecas = new Pecas();
                
                pecas.setId(rs.getInt("ID"));
                pecas.setNome(rs.getString("NOME"));
                pecas.setMarca(rs.getString("MARCA"));
                pecas.setQuantidade(rs.getInt("QUANTIDADE"));
                pecas.setIdFornecedor(rs.getInt("ID_FORNECEDOR"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getPecasById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return pecas;
    }
    
}
