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
public class ConstantsMainSQL {
    
    public static String LISTAR = "SELECT OS.NUMERO_ORDEM, CLI.NOME, CLI.TELEFONE, OS.DATA_ENTRADA, OS.DATA_SAIDA,\n" +
                        "    OS.ORCAMENTO, CX.VALOR_FINAL FROM ORDEM_SERVICO OS, CLIENTE CLI, CAIXA CX, CELULAR CEL\n" +
                        "    WHERE CX.NRO_ORDEM = OS.NUMERO_ORDEM AND OS.ID_APARELHO = CEL.ID AND CEL.ID_CLIENTE = CLI.ID";
    
}
