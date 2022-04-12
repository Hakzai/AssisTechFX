/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classe;

import java.util.Date;

public class Aluguel {

    private Date data_inicio;
    private Date data_termino;
    
    //TEM UM
    private Cliente cliente;
    private Celular celular;
    private OrdemServico ordem;
    
    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the celular
     */
    public Celular getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(Celular celular) {
        this.celular = celular;
    }
    
    /**
     * @return the ordem
     */
    public OrdemServico getOrdem() {
        return ordem;
    }

    /**
     * @param ordem the ordem to set
     */
    public void setOrdem(OrdemServico ordem) {
        this.ordem = ordem;
    }
    
    public Aluguel(){
    
    }

    /**
     * @return the data_inicio
     */
    public Date getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return the data_termino
     */
    public Date getData_termino() {
        return data_termino;
    }

    /**
     * @param data_termino the data_termino to set
     */
    public void setData_termino(Date data_termino) {
        this.data_termino = data_termino;
    }
    
}
