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
public class ConstantsCaixaSQL {
    
    public static String SAVE = "INSERT INTO Caixa (TIPO_SERVICO, VALOR_FINAL, FORMA_PAGAMENTO, NRO_ORDEM) VALUES (?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Caixa SET TIPO_SERVICO = ?, VALOR_FINAL = ?, FORMA_PAGAMENTO = ?, NRO_ORDEM = ? WHERE ID_RECIBO = ?";
    public static String DELETE = "DELETE FROM Caixa WHERE ID_RECIBO = ?";
    public static String LISTAR = "SELECT * FROM Caixa";
    
    public static String GET_NRO_ORDENS = "SELECT NUMERO_ORDEM FROM Ordem_Servico";
    public static String FIND_BY_TIPO = "SELECT * FROM Caixa WHERE TIPO_SERVICO LIKE '%"; 
    public static String GET_CAIXA_BY_ID = "SELECT * FROM Caixa WHERE ID_RECIBO = (?)";
}
