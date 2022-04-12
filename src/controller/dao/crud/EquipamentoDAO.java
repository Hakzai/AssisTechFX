/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsEquipamentoSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Equipamento;

/**
 *
 * @author Codeiro
 */
public class EquipamentoDAO extends ConnectionDAO{
    
    public boolean save (Equipamento e){
        boolean result = true;
        
        String sql = ConstantsEquipamentoSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getMarca());
            stmt.setFloat(3, e.getPreco());
            stmt.setString(4, e.getDataCompra());
            stmt.setString(5, e.getDataUltimaManutencao());
            stmt.setString(6, e.getManutencao());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Equipamento e){
        boolean result = true;
        
        String sql = ConstantsEquipamentoSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getMarca());
            stmt.setDouble(3, e.getPreco());
            stmt.setString(4, e.getDataCompra());
            stmt.setString(5, e.getDataUltimaManutencao());
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
    
    public boolean delete(Equipamento e){
        boolean result = true;
        
        String sql = ConstantsEquipamentoSQL.DELETE;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<Equipamento> listar(){
        String sql = ConstantsEquipamentoSQL.LISTAR;
        
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return equipamentoList;
    }

    
}
