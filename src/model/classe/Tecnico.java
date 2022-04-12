/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classe;

/**
 *
 * @author Codeiro
 */
public class Tecnico extends Funcionario{

    private String especialidade;
    private int id_equipamento;
    
    public Tecnico(){
        super();
    }
    
    /**
     * @return the especialidade
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * @return the id_equipamento
     */
    public int getId_equipamento() {
        return id_equipamento;
    }

    /**
     * @param id_equipamento the id_equipamento to set
     */
    public void setId_equipamento(int id_equipamento) {
        this.id_equipamento = id_equipamento;
    }
    
}
