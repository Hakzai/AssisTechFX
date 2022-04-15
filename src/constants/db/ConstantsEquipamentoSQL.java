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
    
    public static String SAVE = "INSERT INTO Equipamento (NOME, MARCA, PRECO, DATA_COMPRA, DATA_ULTIMA_MANUTENCAO, MANUTENCAO) VALUES (?, ?, ?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Equipamento SET NOME = ?, MARCA = ?, PRECO = ?, DATA_COMPRA = ?, DATA_ULTIMA_MANUTENCAO = ?, MANUTENCAO = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Equipamento WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Equipamento";
    
    public static String GET_NOME_FUNCIONARIO_BY_EQ_ID = "SELECT f.nome AS Nome_Funcionario FROM funcionario f, equipamento eq WHERE f.id = eq.id_funcionario AND eq.id = (?)";
    public static String SET_FUNCIONARIO_USING_EQ_BY_NOME = "UPDATE equipamento SET id_funcionario = (SELECT id FROM funcionario WHERE nome = ?) WHERE id = ?";    // "UPDATE equipamento SET id_funcionario = (SELECT id FROM funcionario WHERE nome = ?) WHERE id = ?";    
    public static String GET_MANUTENCAO_STATUS_BY_EQUIPMENT_ID = "SELECT manutencao FROM equipamento WHERE id = (?)";
    
    public static String GET_ID_EQUIPAMENTO = "SELECT ID, NOME FROM Equipamento";
    public static String FIND_BY_NAME = "SELECT * FROM Equipamento WHERE NOME LIKE '%";
    public static String GET_EQUIPAMENTO_BY_ID = "SELECT * FROM Equipamento WHERE ID = (?)";
}
