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
public class ConstantsEquipamentoSQL {
    
    public static String SAVE = "INSERT INTO Fornecedor (NOME, MARCA, PRECO, DATA_COMPRA, DATA_ULTIMA_MANUTENCAO, MANUTENCAO) VALUES (?, ?, ?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Equipamento SET NOME = ?, MARCA = ?, PRECO = ?, DATA_COMPRA = ?, DATA_ULTIMA_MANUTENCAO = ?, MANUTENCAO = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Equipamento WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Equipamento";
    
    public static String GET_ID_EQUIPAMENTO = "SELECT ID, NOME FROM Equipamento";
    public static String FIND_BY_NAME = "SELECT * FROM Equipamento WHERE NOME LIKE '%";
    public static String GET_EQUIPAMENTO_BY_ID = "SELECT * FROM Equipamento WHERE ID = (?)";
}
