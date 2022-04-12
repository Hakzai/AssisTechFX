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
public class Main {
    
    private int idServico;
    private String nomeCliente;
    private String telefoneCliente;
    private String dataInicio;
    private String dataPlanejada;
    private String dataFinal;
    private float valorPlanejado;
    private float valorFinal;
    private String status;
    
    public Main(){
        
    }
    
    public Main(int idServico, String nomeCliente, String telefoneCliente, String dataInicio, String dataFinal, float valorPlanejado, float valorFinal){
        this.idServico = idServico;
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.valorPlanejado = valorPlanejado;
        this.valorFinal = valorFinal;
    }
    
    public void setOrdem(){
        OrdemServico ordem = new OrdemServico();
        setIdServico(ordem.getNumeroOrdem());
        setDataInicio(ordem.getDataEntrada());
        setDataFinal(ordem.getDataSaida());
        setValorPlanejado(ordem.getOrcamento());
    }
    
    public void setCliente() {
        Cliente cliente = new Cliente();
        setNomeCliente(cliente.getNome());
        setTelefoneCliente(cliente.getTelefone());
    }
    
    public void setCaixa() {
        Caixa caixa = new Caixa();
        setValorFinal((float) caixa.getValorFinal());
    }
    
    /**
     * @return the idServico
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * @param idServico the idServico to set
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @return the telefoneCliente
     */
    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    /**
     * @param telefoneCliente the telefoneCliente to set
     */
    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    /**
     * @return the dataInicio
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataPlanejada
     */
    public String getDataPlanejada() {
        return dataPlanejada;
    }

    /**
     * @param dataPlanejada the dataPlanejada to set
     */
    public void setDataPlanejada(String dataPlanejada) {
        this.dataPlanejada = dataPlanejada;
    }

    /**
     * @return the dataFinal
     */
    public String getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * @return the valorPlanejado
     */
    public float getValorPlanejado() {
        return valorPlanejado;
    }

    /**
     * @param valorPlanejado the valorPlanejado to set
     */
    public void setValorPlanejado(float valorPlanejado) {
        this.valorPlanejado = valorPlanejado;
    }

    /**
     * @return the valorFinal
     */
    public float getValorFinal() {
        return valorFinal;
    }

    /**
     * @param valorFinal the valorFinal to set
     */
    public void setValorFinal(float valorFinal) {
        this.valorFinal = valorFinal;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
