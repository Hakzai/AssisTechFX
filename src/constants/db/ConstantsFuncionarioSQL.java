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
public class ConstantsFuncionarioSQL {
    
    public static String SAVE = "INSERT INTO Funcionario (NOME, CPF, ENDERECO, TELEFONE, EMAIL, SALARIO, DATA_CONTRATACAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Funcionario SET NOME = ?, CPF = ?, ENDERECO = ?, TELEFONE = ?, EMAIL = ?, SALARIO = ?, DATA_DEMISSAO = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Funcionario WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Funcionario ORDER BY nome";
    
    public static String SET_DATA_DEMISSAO = "UPDATE funcionario SET data_demissao = ? WHERE id = ?";
    public static String LISTAR_FUNCIONARIOS_ATIVOS = "SELECT * FROM Funcionario WHERE data_demissao IS NULL OR data_demissao like '' ORDER BY nome";
    
    public static String GET_ID_FUNCIONARIO = "";
    public static String FIND_BY_NAME = "SELECT * FROM Funcionario WHERE NOME LIKE '%";
    public static String GET_FUNCIONARIO_BY_ID = "SELECT * FROM Funcionario WHERE ID = (?)";
    
}
