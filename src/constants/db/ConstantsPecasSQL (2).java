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
public class ConstantsPecasSQL {
    
    public static String SAVE = "INSERT INTO Pecas (NOME, MARCA, QUANTIDADE, ID_FORNECEDOR) VALUES (?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Pecas SET NOME = ?, MARCA = ?, QUANTIDADE = ?, ID_FORNECEDOR = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Pecas WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Pecas";
    
    public static String GET_NOME_FORNECEDOR_BY_ID = "SELECT f.nome AS Nome_Fornecedor FROM fornecedor f, pecas p WHERE f.id = p.id_fornecedor AND p.id_fornecedor = (?)";
    public static String GET_ID_FORNECEDOR_BY_NOME = "SELECT id AS Id_Fornecedor FROM fornecedor WHERE nome = (?)";
    public static String GET_DATA_PRIMEIRA_COMPRA_BY_ID = "SELECT data_primeira_compra FROM fornecedor WHERE id = (?)";
    public static String SET_DATA_PRIMEIRA_COMPRA_BY_ID = "UPDATE fornecedor SET data_primeira_compra = ? WHERE id = ?";    
    
    public static String GET_ID_PECAS = "SELECT ID, NOME FROM PECAS";
    public static String FIND_BY_NAME = "SELECT * FROM Pecas WHERE NOME LIKE '%";
    public static String GET_PECAS_BY_ID = "SELECT * FROM Pecas WHERE ID = (?)";
    
}
