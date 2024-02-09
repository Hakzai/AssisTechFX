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
public class Celular {
    
    private int id;
    private String modelo;
    private String marca;
    private String ano;
    private String serial;
    private String estadoUso;
    private String isPropriedade; // esse atributo determina se o celular e da assistencia ou nao
    private boolean importado;
    private int idCliente;
    
    public Celular(){
        
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE CARREGAR OS DADOS NA TABELA
    public Celular(int id, String modelo, String marca, String ano, String serial, String estadoUso, String isPropriedade, int idCliente){
       this.id = id;
       this.modelo = modelo;
       this.marca = marca;
       this.ano = ano;
       this.serial = serial;
       this.estadoUso = estadoUso;
       this.isPropriedade = isPropriedade;
       this.idCliente = idCliente;               
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE EXCLUIR O CLIENTE COM BASE NA TABELA
    public Celular(int id){
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the estadoUso
     */
    public String getEstadoUso() {
        return estadoUso;
    }

    /**
     * @param estadoUso the estadoUso to set
     */
    public void setEstadoUso(String estadoUso) {
        this.estadoUso = estadoUso;
    }

    /**
     * @return the isPropriedade
     */
    public String getIsPropriedade() {
        return isPropriedade;
    }

    /**
     * @param isPropriedade the isPropriedade to set
     */
    public void setIsPropriedade(String isPropriedade) {
        this.isPropriedade = isPropriedade;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    @Override
    public String toString(){
        return Integer.toString(getIdCliente());
    }
    
}
