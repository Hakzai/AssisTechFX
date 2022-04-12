/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsOrdemServicoSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.OrdemServico;

/**
 *
 * @author Codeiro
 */
public class OrdemServicoDAO extends ConnectionDAO {
    
    public OrdemServicoDAO(){
        super();
    }
    
    public boolean save (OrdemServico os){
        boolean result = true;
        
        String sql = ConstantsOrdemServicoSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, os.getDataEntrada());
            stmt.setString(2, os.getDataSaida());
            stmt.setFloat(3, os.getOrcamento());
            stmt.setString(4, os.getAluguel());
            stmt.setInt(5, os.getIdTecnico());
            stmt.setInt(6, os.getIdAparelho());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(OrdemServico os){
        boolean result = true;
        
        String sql = ConstantsOrdemServicoSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, os.getDataEntrada());
            stmt.setString(2, os.getDataSaida());
            stmt.setFloat(3, os.getOrcamento());
            stmt.setString(4, os.getAluguel());
            stmt.setInt(5, os.getIdTecnico());
            stmt.setInt(6, os.getIdAparelho());
            stmt.setInt(7, os.getNumeroOrdem());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(OrdemServico os){
        boolean result = true;
        
        String sql = ConstantsOrdemServicoSQL.DELETE;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, os.getNumeroOrdem());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<OrdemServico> listar(){
        String sql = ConstantsOrdemServicoSQL.LISTAR;
        
        List<OrdemServico> ordemServicoList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                OrdemServico ordemServico = new OrdemServico();
                
                ordemServico.setNumeroOrdem(rs.getInt("NUMERO_ORDEM"));
                ordemServico.setDataEntrada(rs.getString("DATA_ENTRADA"));
                ordemServico.setDataSaida(rs.getString("DATA_SAIDA"));
                ordemServico.setOrcamento(rs.getFloat("ORCAMENTO"));
                ordemServico.setAluguel(rs.getString("ALUGUEL"));
                ordemServico.setIdTecnico(rs.getInt("ID_TECNICO"));
                ordemServico.setIdAparelho(rs.getInt("ID_APARELHO"));
                
                ordemServicoList.add(ordemServico);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return ordemServicoList;
    }
    
}