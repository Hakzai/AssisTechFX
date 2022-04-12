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
public class Equipamento {
    
    private int id;
    private String nome;
    private String marca;
    private float preco;
    private String dataCompra;
    private String manutencao;
    private String dataUltimaManutencao;
    
    public Equipamento(){
        
    }
    
    public Equipamento(int id){
        this.id = id;
    }
    
    public Equipamento(int id, String nome, String marca, float preco, String dataCompra, String dataUltimaManutencao, String manutencao){
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.dataCompra = dataCompra;
        this.dataUltimaManutencao = dataUltimaManutencao;
        this.manutencao = manutencao;        
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @return the dataCompra
     */
    public String getDataCompra() {
        return dataCompra;
    }

    /**
     * @param dataCompra the dataCompra to set
     */
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     * @return the manutencao
     */
    public String getManutencao() {
        return manutencao;
    }

    /**
     * @param manutencao the manutencao to set
     */
    public void setManutencao(String manutencao) {
        this.manutencao = manutencao;
    }

    /**
     * @return the dataUltimaManutencao
     */
    public String getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    /**
     * @param dataUltimaManutencao the dataUltimaManutencao to set
     */
    public void setDataUltimaManutencao(String dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }
}
