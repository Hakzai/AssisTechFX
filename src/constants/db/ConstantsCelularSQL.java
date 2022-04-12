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
public class ConstantsCelularSQL {
    
    public static String SAVE = "INSERT INTO Celular (MODELO, MARCA, ANO, SERIAL, ESTADO_USO, IS_PROPRIEDADE, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static String UPDATE = "UPDATE Celular SET MODELO = ?, MARCA = ?, ANO = ?, SERIAL = ?, ESTADO_USO = ?, IS_PROPRIEDADE = ?, ID_CLIENTE = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM Celular WHERE ID = ?";
    public static String LISTAR = "SELECT * FROM Celular";
    
    public static String GET_NOME_CLIENTE_BY_ID = "SELECT cli.nome AS Nome_Cliente FROM cliente cli, celular cel WHERE cel.id_cliente = cli.id AND cel.id_cliente = (?)";
    public static String GET_ID_CLIENTE_BY_NOME = "SELECT id AS Id_Cliente FROM cliente WHERE nome = (?)";
    public static String GET_CELULAR_BY_ID = "SELECT * FROM Celular WHERE ID = (?)";
        
    // NO USE
    public static String FIND_BY_MODELO = "SELECT * FROM Celular WHERE modelo LIKE (?)"; 
    public static String GET_ID_CLIENTES = "SELECT DISTINCT id_cliente FROM Celular WHERE id_cliente IS NOT NULL ORDER BY id_cliente;";
    public static String GET_ALL_MODELO = "SELECT DISTINCT modelo FROM celular ORDER BY modelo";
    public static String GET_MODELO_SERIAL = "SELECT modelo, serial FROM celular ORDER BY modelo";
    public static String GET_CELULAR_CLIENTE = "SELECT cel.id, cel.modelo, cel.marca, cel.serial, cli.nome, cli.telefone, cli.id FROM celular cel, cliente cli WHERE cel.id_cliente = cli.id ORDER BY cel.id";
    
}
