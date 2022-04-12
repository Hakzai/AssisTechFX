/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsFuncionarioSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Funcionario;

/**
 *
 * @author Codeiro
 */
public class FuncionarioUtils extends FuncionarioDAO {
    
    public FuncionarioUtils() {
        super();
    }
    
    public List<Funcionario> getIdFuncionario(){
        String sql = ConstantsFuncionarioSQL.GET_ID_FUNCIONARIO;
        
        List<Funcionario> idsList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(rs.getInt("ID"));
                funcionario.setNome(rs.getString("NOME"));
                
                idsList.add(funcionario);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getIdFuncionario: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return idsList;
        
    }
    
    public List<Funcionario> findByName(String name){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsFuncionarioSQL.FIND_BY_NAME);
        sqlBuilder = sqlBuilder.append(name).append("%'");
        
        String sql = sqlBuilder.toString();
        
        List<Funcionario> funcionarioList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(rs.getInt("ID"));
                funcionario.setNome(rs.getString("NOME"));
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setEndereco(rs.getString("ENDERECO"));
                funcionario.setTelefone(rs.getString("TELEFONE"));
                funcionario.setEmail(rs.getString("EMAIL"));
                funcionario.setSalario(rs.getFloat("SALARIO"));
                funcionario.setDataContratacao(rs.getString("DATA_CONTRATACAO"));
                funcionario.setDataDemissao(rs.getString("DATA_DEMISSAO"));
                
                funcionarioList.add(funcionario);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return funcionarioList;
    }
    
    public Funcionario getFuncionarioById(int id){
        String sql = ConstantsFuncionarioSQL.GET_FUNCIONARIO_BY_ID;
        
        Funcionario funcionario = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                funcionario = new Funcionario();
                
                funcionario.setId(rs.getInt("ID"));
                funcionario.setNome(rs.getString("NOME"));
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setEndereco(rs.getString("ENDERECO"));
                funcionario.setTelefone(rs.getString("TELEFONE"));
                funcionario.setEmail(rs.getString("EMAIL"));
                funcionario.setSalario(rs.getFloat("SALARIO"));
                funcionario.setDataContratacao(rs.getString("DATA_CONTRATACAO"));
                funcionario.setDataDemissao(rs.getString("DATA_DEMISSAO"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getFuncionarioById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return funcionario;
    }
    
}
