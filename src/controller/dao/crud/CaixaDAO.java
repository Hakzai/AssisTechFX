/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsCaixaSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAOImpl;
import model.classe.Caixa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Codeiro
 */
public class CaixaDAO extends ConnectionDAOImpl {
    
    public CaixaDAO(){
        super();
    }
    
    public boolean save (Caixa c){
        boolean result = true;
        
        String sql = ConstantsCaixaSQL.SAVE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, c.getTipoServico());
            stmt.setDouble(2, c.getValorFinal());
            stmt.setString(3, c.getFormaPagamento());
            stmt.setInt(4, c.getNroOrdem());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
    public boolean update(Caixa c){
        boolean result = true;
        
        String sql = ConstantsCaixaSQL.UPDATE;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, c.getTipoServico());
            stmt.setDouble(2, c.getValorFinal());
            stmt.setString(3, c.getFormaPagamento());
            stmt.setInt(4, c.getNroOrdem());
            stmt.setInt(5, c.getIdRecibo());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public boolean delete(Caixa c){
        boolean result = true;
            
            String sql = ConstantsCaixaSQL.DELETE;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, c.getIdRecibo());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public List<Caixa> listar(){
        String sql = ConstantsCaixaSQL.LISTAR;
        
        List<Caixa> caixaList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Caixa caixa = new Caixa();
                
                caixa.setIdRecibo(rs.getInt("ID_RECIBO"));
                caixa.setTipoServico(rs.getString("TIPO_SERVICO"));
                caixa.setValorFinal(rs.getDouble("VALOR_FINAL"));
                caixa.setFormaPagamento(rs.getString("FORMA_PAGAMENTO"));
                caixa.setNroOrdem(rs.getInt("NRO_ORDEM"));
                
                caixaList.add(caixa);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return caixaList;
    }
    
}
