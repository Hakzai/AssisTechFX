/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsFuncionarioSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Funcionario;

/**
 *
 * @author Codeiro
 */
public class FuncionarioDAO extends ConnectionDAO {
    
    public FuncionarioDAO(){
        super();
    }
    
    public boolean save (Funcionario f){
        boolean result = true;
        
        String sql = ConstantsFuncionarioSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf());
            stmt.setString(3, f.getEndereco());
            stmt.setString(4, f.getTelefone());
            stmt.setString(5, f.getEmail());
            stmt.setFloat(6, f.getSalario());
            stmt.setString(7, f.getDataContratacao());
            stmt.setString(8, f.getDataDemissao());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Funcionario f){
        boolean result = true;
        
        String sql = ConstantsFuncionarioSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf());
            stmt.setString(3, f.getEndereco());
            stmt.setString(4, f.getTelefone());
            stmt.setString(5, f.getEmail());
            stmt.setFloat(6, f.getSalario());
            stmt.setString(7, f.getDataDemissao());
            stmt.setInt(8, f.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(Funcionario f){
        boolean result = true;
        
        String sql = ConstantsFuncionarioSQL.DELETE;
        
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
    
    public List<Funcionario> listar(){
        String sql = ConstantsFuncionarioSQL.LISTAR;
        
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return funcionarioList;
    }
    
}
