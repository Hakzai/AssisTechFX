/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsCaixaSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.CaixaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Caixa;

/**
 *
 * @author Codeiro
 */
public class CaixaUtils extends CaixaDAO {
    
    public CaixaUtils(){
        super();
    }
    
    public List<Caixa> getNroOrdens(){
        String sql = ConstantsCaixaSQL.GET_NRO_ORDENS;
        
        List<Caixa> ordensList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Caixa caixa = new Caixa();
                
                caixa.setNroOrdem(rs.getInt("NUMERO_ORDEM"));
                
                ordensList.add(caixa);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getNroOrdens: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return ordensList;
        
    }
    
    public List<Caixa> findByTipo(String name){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder = sqlBuilder.append(ConstantsCaixaSQL.FIND_BY_TIPO);
        sqlBuilder = sqlBuilder.append(name).append("%'");
        
        String sql = sqlBuilder.toString();
        
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
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findByName: " + ex);
            
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return caixaList;
    }
    
    public Caixa getCaixaById(int id){
        String sql = ConstantsCaixaSQL.GET_CAIXA_BY_ID;
        
        Caixa caixa = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                caixa = new Caixa();
                
                caixa.setIdRecibo(rs.getInt("ID_RECIBO"));
                caixa.setTipoServico(rs.getString("TIPO_SERVICO"));
                caixa.setValorFinal(rs.getDouble("VALOR_FINAL"));
                caixa.setFormaPagamento(rs.getString("FORMA_PAGAMENTO"));
                caixa.setNroOrdem(rs.getInt("NRO_ORDEM"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getCaixaById: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return caixa;
    }
    
}
