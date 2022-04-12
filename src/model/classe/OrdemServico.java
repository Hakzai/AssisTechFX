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
public class OrdemServico {

    private int numeroOrdem;
    private String dataEntrada;
    private String dataSaida;
    private float orcamento;
    private String aluguel;    
    private int idTecnico;
    private int idAparelho;

    public OrdemServico() {
    
    }
    
    public OrdemServico(int numeroOrdem, String aluguel, String dataEntrada, int idAparelho, float orcamento, String dataSaida, int idTecnico) {
        this.numeroOrdem = numeroOrdem;
        this.aluguel = aluguel;
        this.dataEntrada = dataEntrada;
        this.idAparelho = idAparelho;
        this.orcamento = orcamento;
        this.dataSaida = dataSaida;
        this.idTecnico = idTecnico;
    }
    
    public OrdemServico(int numeroOrdem){
        this.numeroOrdem = numeroOrdem;
    }
    
    /**
     * @return the aluguel
     */
    public String getAluguel() {
        return aluguel;
    }

    /**
     * @param aluguel the aluguel to set
     */
    public void setAluguel(String aluguel) {
        this.aluguel = aluguel;
    }
    
    /**
     * @return the numeroOrdem
     */
    public int getNumeroOrdem() {
        return numeroOrdem;
    }

    /**
     * @param numeroOrdem the numeroOrdem to set
     */
    public void setNumeroOrdem(int numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    /**
     * @return the dataEntrada
     */
    public String getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the dataSaida
     */
    public String getDataSaida() {
        return dataSaida;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    /**
     * @return the orcamento
     */
    public float getOrcamento() {
        return orcamento;
    }

    /**
     * @param orcamento the orcamento to set
     */
    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    /**
     * @return the idTecnico
     */
    public int getIdTecnico() {
        return idTecnico;
    }

    /**
     * @param idTecnico the idTecnico to set
     */
    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    /**
     * @return the idAparelho
     */
    public int getIdAparelho() {
        return idAparelho;
    }

    /**
     * @param idAparelho the idAparelho to set
     */
    public void setIdAparelho(int idAparelho) {
        this.idAparelho = idAparelho;
    }
    
}
