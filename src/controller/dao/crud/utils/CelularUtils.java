/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsCelularSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.CelularDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Celular;

/**
 *
 * @author Codeiro
 */
public class CelularUtils extends CelularDAO {
    
    public CelularUtils() {
        super();
    }
    
    public List<Celular> getIdClientes(){
        String sql = ConstantsCelularSQL.GET_ID_CLIENTES;
        
        List<Celular> idsList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Celular celular = new Celular();
                
                celular.setIdCliente(rs.getInt("ID_CLIENTE"));
                
                idsList.add(celular);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getIdClientes: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return idsList;
        
    }
    
    public int getClientIdByName(String name){
        conn = ConnectionFactory.getConnection();
        
        String sql = ConstantsCelularSQL.GET_ID_CLIENTE_BY_NOME;
        int clientId = -1;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, name);
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){
                clientId = rs.getInt("Id_Cliente");
            }
            
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO getClientIdByName: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
            rs = null;
            stmt = null;
        }
        
        return clientId;
    }
    
    public String getClientNameById(int id_cliente){
        conn = ConnectionFactory.getConnection();
        
        String sql = ConstantsCelularSQL.GET_NOME_CLIENTE_BY_ID;
        String clientName = "";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id_cliente);
            
            rs = stmt.executeQuery();
            
            if(null != rs && rs.next()){ // PRECISA VERIFICAR E STARTAR O RS
                clientName = rs.getString("Nome_Cliente");
            }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getClientNameById: " + ex);
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
            rs = null;
            stmt = null;
        }
        
        return clientName;
    }
    
    public List<Celular> findByModelo(String modelo){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsCelularSQL.FIND_BY_MODELO);
        sqlBuilder = sqlBuilder.append(modelo).append("%'");
        
        String sql = sqlBuilder.toString();
        
        List<Celular> celularList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Celular celular = new Celular();
                
                celular.setId(rs.getInt("ID"));
                celular.setModelo(rs.getString("MODELO"));
                celular.setMarca(rs.getString("MARCA"));
                celular.setAno(rs.getString("ANO"));
                celular.setSerial(rs.getString("SERIAL"));
                celular.setEstadoUso(rs.getString("ESTADO_USO"));
                celular.setIsPropriedade(rs.getString("IS_PROPRIEDADE"));
                celular.setIdCliente(rs.getInt("ID_CLIENTE"));
                
                celularList.add(celular);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return celularList;
    }
    
    public Celular getCelularById(int id){
        String sql = ConstantsCelularSQL.GET_CELULAR_BY_ID;
        
        Celular celular = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                celular = new Celular();
                
                celular.setId(rs.getInt("ID"));
                celular.setModelo(rs.getString("MODELO"));
                celular.setMarca(rs.getString("MARCA"));
                celular.setAno(rs.getString("ANO"));
                celular.setSerial(rs.getString("SERIAL"));
                celular.setEstadoUso(rs.getString("ESTADO_USO"));
                celular.setIsPropriedade(rs.getString("IS_PROPRIEDADE"));
                celular.setIdCliente(rs.getInt("ID_CLIENTE"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getCelularById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return celular;
    }
    
}
