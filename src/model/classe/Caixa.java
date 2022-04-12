/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classe;

import java.util.Date;

/**
 *
 * @author Codeiro
 */
public class Caixa {

    private int idRecibo;
    private String tipoServico;
    private double valorFinal;
    private String formaPagamento;
    private int nroOrdem;
    
    //TEM UM
    private OrdemServico ordemServico;
    
    /**
     * @return the ordemServico
     */
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    /**
     * @param ordemServico the ordemServico to set
     */
    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
    
    public Caixa(){
    
    }
    
    public Caixa(int idRecibo){
        this.idRecibo = idRecibo;
    }
    
    public Caixa(int idRecibo, String tipoServico, double valorFinal, String formaPagamento, int nroOrdem){
        this.idRecibo = idRecibo;
        this.tipoServico = tipoServico;
        this.valorFinal = valorFinal;
        this.formaPagamento = formaPagamento;
        this.nroOrdem = nroOrdem;
    }

    /**
     * @return the idRecibo
     */
    public int getIdRecibo() {
        return idRecibo;
    }

    /**
     * @return the valorFinal
     */
    public double getValorFinal() {
        return valorFinal;
    }

    /**
     * @param idRecibo the idRecibo to set
     */
    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    /**
     * @param valorFinal the valorFinal to set
     */
    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    /**
     * @return the tipoServico
     */
    public String getTipoServico() {
        return tipoServico;
    }

    /**
     * @param tipoServico the tipoServico to set
     */
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the nroOrdem
     */
    public int getNroOrdem() {
        return nroOrdem;
    }

    /**
     * @param nroOrdem the nroOrdem to set
     */
    public void setNroOrdem(int nroOrdem) {
        this.nroOrdem = nroOrdem;
    }
    
}
