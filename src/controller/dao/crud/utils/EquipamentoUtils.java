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
