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
public class Fornecedor {
    
    private int id;
    private String cnpj;
    private String nome;
    private String telefone;
    private String dataPrimeiraCompra;
    
    public Fornecedor(){
        
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE CARREGAR OS DADOS NA TABELA
    public Fornecedor(int id, String nome, String cnpj, String telefone, String dataPrimeiraCompra){
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.dataPrimeiraCompra = dataPrimeiraCompra;        
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE EXCLUIR O FORNECEDOR COM BASE NA TABELA    
    public Fornecedor (int id){
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
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the dataPrimeiraCompra
     */
    public String getDataPrimeiraCompra() {
        return dataPrimeiraCompra;
    }

    /**
     * @param dataPrimeiraCompra the dataPrimeiraCompra to set
     */
    public void setDataPrimeiraCompra(String dataPrimeiraCompra) {
        this.dataPrimeiraCompra = dataPrimeiraCompra;
    }
    
}
