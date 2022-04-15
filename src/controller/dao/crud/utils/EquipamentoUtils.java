/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsEquipamentoSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.EquipamentoDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Equipamento;

/**
 *
 * @author Codeiro
 */
public class EquipamentoUtils extends EquipamentoDAO {
    
    public EquipamentoUtils() {
        super();
    }
    
    public boolean update(Equipamento e){
        this.getConnection();
        
        boolean result = true;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String sql = ConstantsEquipamentoSQL.UPDATE;
        String dateNow = dtf.format(LocalDateTime.now());
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getMarca());
            stmt.setDouble(3, e.getPreco());
            stmt.setString(4, e.getDataCompra());
            stmt.setString(5, dateNow);
            stmt.setString(6, e.getManutencao());
            stmt.setInt(7, e.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<Equipamento> getIdEquipamento(){
        String sql = ConstantsEquipamentoSQL.GET_ID_EQUIPAMENTO;
        
        List<Equipamento> idsList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Equipamento equipamento = new Equipamento();
                
                equipamento.setId(rs.getInt("ID"));
                equipamento.setNome(rs.getString("NOME"));
                
                idsList.add(equipamento);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getIdEquipamento: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return idsList;
        
    }
    
    public String getFuncionarioNameById(int id_equip){
        this.getConnection();
        
        String sql = ConstantsEquipamentoSQL.GET_NOME_FUNCIONARIO_BY_EQ_ID;
        String funcionarioName = "";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id_equip);
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){
                funcionarioName = rs.getString("Nome_Funcionario");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getFuncionarioNameById: " + ex);
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
            rs = null;
            stmt = null;
        }
        
        return funcionarioName;
    }
    
     public void setFuncionarioUsingEquipmentByName(String nomeFuncionario, int idEquipamento){
        this.getConnection();
        
        String sql = ConstantsEquipamentoSQL.SET_FUNCIONARIO_USING_EQ_BY_NOME;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, nomeFuncionario);
            stmt.setInt(2, idEquipamento);
            
            stmt.executeUpdate();            
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO setFuncionarioUsingEquipmentByName: " + ex);
        } finally{
            ConnectionFactory.closeConnection(conn, stmt);
            stmt = null;
        }
        
    }
     
    public String getManutencaoStatusByEquipmentId(int idEquipamento){
        this.getConnection();
        
        String sql = ConstantsEquipamentoSQL.GET_MANUTENCAO_STATUS_BY_EQUIPMENT_ID;
        String manutencao = "";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idEquipamento);            
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){
                manutencao = rs.getString("manutencao");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getManutencaoStatusByEquipmentId: " + ex);
        } finally{
            ConnectionFactory.closeConnection(conn, stmt);
            stmt = null;
        }
        
        return manutencao;
    }
    
    public List<Equipamento> findByName(String name){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsEquipamentoSQL.FIND_BY_NAME);
        sqlBuilder = sqlBuilder.append(name).append("%'");
        
        String sql = sqlBuilder.toString();
        
        List<Equipamento> equipamentoList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Equipamento equipamento = new Equipamento();
                
                equipamento.setId(rs.getInt("ID"));
                equipamento.setNome(rs.getString("NOME"));
                equipamento.setMarca(rs.getString("MARCA"));
                equipamento.setPreco(rs.getFloat("PRECO"));
                equipamento.setDataCompra(rs.getString("DATA_COMPRA"));
                equipamento.setDataUltimaManutencao(rs.getString("DATA_ULTIMA_MANUTENCAO"));
                equipamento.setManutencao(rs.getString("MANUTENCAO"));
                
                equipamentoList.add(equipamento);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return equipamentoList;
    }
    
    public Equipamento getEquipamentoById(int id){
        String sql = ConstantsEquipamentoSQL.GET_EQUIPAMENTO_BY_ID;
        
        Equipamento equipamento = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                equipamento = new Equipamento();
                
                equipamento.setId(rs.getInt("ID"));
                equipamento.setNome(rs.getString("NOME"));
                equipamento.setMarca(rs.getString("MARCA"));
                equipamento.setPreco(rs.getFloat("PRECO"));
                equipamento.setDataCompra(rs.getString("DATA_COMPRA"));
                equipamento.setDataUltimaManutencao(rs.getString("DATA_ULTIMA_MANUTENCAO"));
                equipamento.setManutencao(rs.getString("MANUTENCAO"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getEquipamentoById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return equipamento;
    }

    
}
