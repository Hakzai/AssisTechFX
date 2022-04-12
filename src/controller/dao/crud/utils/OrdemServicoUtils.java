/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud.utils;

import constants.db.ConstantsOrdemServicoSQL;
import controller.connection.ConnectionFactory;
import controller.dao.crud.OrdemServicoDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.classe.OrdemServico;

/**
 *
 * @author Codeiro
 */
public class OrdemServicoUtils extends OrdemServicoDAO {
    
    public OrdemServicoUtils() {
        super();
    }
    
    
    
    public OrdemServico getOrdemServicoByNro(int id){
        String sql = ConstantsOrdemServicoSQL.GET_ORDEM_SERVICO_BY_NRO;
        
        OrdemServico ordemServico = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ordemServico = new OrdemServico();
                
                ordemServico.setNumeroOrdem(rs.getInt("NUMERO_ORDEM"));
                ordemServico.setDataEntrada(rs.getString("DATA_ENTRADA"));
                ordemServico.setDataSaida(rs.getString("DATA_SAIDA"));
                ordemServico.setOrcamento(rs.getFloat("ORCAMENTO"));
                ordemServico.setAluguel(rs.getString("ALUGUEL"));
                ordemServico.setIdTecnico(rs.getInt("ID_TECNICO"));
                ordemServico.setIdAparelho(rs.getInt("ID_APARELHO"));
            }
        
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO getOrdemServicoByNro: " + ex);
        }finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return ordemServico;
    }
    
}
