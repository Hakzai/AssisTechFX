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
public class Pecas {

    private int id;
    private String nome;
    private String marca;
    private int quantidade;
    private int idFornecedor;
    
    public Pecas (){
        
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE CARREGAR OS DADOS NA TABELA
    public Pecas (int id, String nome, String marca, int quantidade, int idFornecedor){
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.quantidade = quantidade;
        this.idFornecedor = idFornecedor;
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE EXCLUIR A PEÇA COM BASE NA TABELA    
    public Pecas (int id){
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
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the idFornecedor
     */
    public int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * @param idFornecedor the idFornecedor to set
     */
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
}
