/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsFornecedorSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.FornecedorDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Fornecedor;

/**
 *
 * @author Codeiro
 */
public class FornecedorUtils extends FornecedorDAO {
    
    public FornecedorUtils() {
        super();
    }
    
    public List<Fornecedor> getIdFornecedor(){
        String sql = ConstantsFornecedorSQL.GET_ID_FORNECEDOR;
        
        List<Fornecedor> idsList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setId(rs.getInt("ID"));
                fornecedor.setNome(rs.getString("NOME"));
                
                idsList.add(fornecedor);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getIdFornecedor: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return idsList;
        
    }
    
    public List<Fornecedor> findByName(String name){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsFornecedorSQL.FIND_BY_NAME);
        sqlBuilder = sqlBuilder.append(name).append("%'");
        
        String sql = sqlBuilder.toString();
        
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
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return fornecedorList;
    }
    
    public Fornecedor getFornecedorById(int id){
        String sql = ConstantsFornecedorSQL.GET_FORNECEDOR_BY_ID;
        
        Fornecedor fornecedor = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                fornecedor = new Fornecedor();
                
                fornecedor.setId(rs.getInt("ID"));
                fornecedor.setNome(rs.getString("NOME"));
                fornecedor.setCnpj(rs.getString("CNPJ"));
                fornecedor.setTelefone(rs.getString("TELEFONE"));
                fornecedor.setDataPrimeiraCompra(rs.getString("DATA_PRIMEIRA_COMPRA"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getFornecedorById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return fornecedor;
    }

    
}
