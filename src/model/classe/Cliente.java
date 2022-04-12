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
public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String email;
    
    // TEM UM
    private Celular celular;

    // CONSTRUTOR UTILIZADO NA HORA DE CARREGAR OS DADOS NA TABELA
    public Cliente(int id, String nome, String cpf, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE CRIAR O COMBOBOX CLIENTE
    public Cliente(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    // CONSTRUTOR UTILIZADO NA HORA DE EXCLUIR O CLIENTE COM BASE NA TABELA
    public Cliente(int id){
        this.id = id;
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
    
    public Cliente (){
        
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    // TRATAR DEPOIS PARA RETORNAR O NOME NO COMBOBOX, VOU DEIXAR SÓ O NUMERO POR ENQUANTO
    @Override
    public String toString(){
        return Integer.toString(getId());// + " - " + getNome();
    }
        
    
}
