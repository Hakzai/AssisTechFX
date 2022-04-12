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
public class ConstantsClienteSQL {
    
    public static String SAVE = "INSERT INTO Cliente (NOME, CPF, ENDERECO, TELEFONE, EMAIL) VALUES (?, ?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Cliente SET NOME = ?, CPF = ?, ENDERECO = ?, TELEFONE = ?, EMAIL = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Cliente WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Cliente ORDER BY nome";
    
    public static String GET_ID_CLIENTES = "SELECT ID, NOME FROM CLIENTE";
    public static String FIND_BY_NAME = "SELECT * FROM Cliente WHERE NOME LIKE '%";
    public static String GET_CLIENTE_BY_ID = "SELECT * FROM Cliente WHERE ID = (?)";
    
}
