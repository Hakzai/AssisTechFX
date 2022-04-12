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
public class ConstantsFornecedorSQL {
    
    public static String SAVE = "INSERT INTO Fornecedor (NOME, CNPJ, TELEFONE, DATA_PRIMEIRA_COMPRA) VALUES (?, ?, ?, ?)";
    public static String SAVE_NO_DATE = "INSERT INTO Fornecedor (NOME, CNPJ, TELEFONE) VALUES (?, ?, ?)";
    public static String UPDATE = "UPDATE Fornecedor SET NOME = ?, CNPJ = ?, TELEFONE = ?, DATA_PRIMEIRA_COMPRA = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Fornecedor WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Fornecedor";
    
    public static String GET_ID_FORNECEDOR = "SELECT ID, NOME FROM Fornecedor";
    public static String FIND_BY_NAME = "SELECT * FROM Fornecedor WHERE NOME LIKE '%";
    public static String GET_FORNECEDOR_BY_ID = "SELECT * FROM Fornecedor WHERE ID = (?)";
    
}
