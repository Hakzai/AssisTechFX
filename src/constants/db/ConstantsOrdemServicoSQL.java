/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants.db;

/**
 *
 * @author Codeiro
 */
public class ConstantsOrdemServicoSQL {
    
    public static String SAVE = "INSERT INTO Ordem_Servico (DATA_ENTRADA, DATA_SAIDA, ORCAMENTO, ALUGUEL, ID_TECNICO, ID_APARELHO) VALUES (?, ?, ?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Ordem_Servico SET DATA_ENTRADA = ?, DATA_SAIDA = ?, ORCAMENTO = ?, ALUGUEL = ?, ID_TECNICO = ?, ID_APARELHO = ? WHERE NUMERO_ORDEM = ?";
    public static String DELETE = "DELETE FROM Ordem_Servico WHERE NUMERO_ORDEM = ?";
    public static String LISTAR = "SELECT * FROM Ordem_Servico";
    
    public static String GET_ORDEM_SERVICO_BY_NRO = "SELECT * FROM Ordem_Servico WHERE NUMERO_ORDEM = (?)";
    
}
