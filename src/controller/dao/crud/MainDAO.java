/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.crud;

import constants.db.ConstantsMainSQL;
import controller.connection.ConnectionFactory;
import controller.dao.ConnectionDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classe.Main;

/**
 *
 * @author Codeiro
 */
public class MainDAO extends ConnectionDAOImpl {
    
    public MainDAO(){
        super();
    }
    
    public List<Main> listar(){
        String sql = ConstantsMainSQL.LISTAR;
        
        List<Main> mainList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Main main = new Main();
                
                main.setIdServico(rs.getInt("OS.NUMERO_ORDEM"));
                main.setNomeCliente(rs.getString("CLI.NOME"));
                main.setTelefoneCliente(rs.getString("CLI.TELEFONE"));
                main.setDataInicio(rs.getString("OS.DATA_ENTRADA"));
                main.setDataFinal(rs.getString("OS.DATA_SAIDA"));
                main.setValorPlanejado(rs.getFloat("OS.ORCAMENTO"));
                main.setValorFinal(rs.getFloat("CX.VALOR_FINAL"));
                
                mainList.add(main);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return mainList;
    }
    
}
