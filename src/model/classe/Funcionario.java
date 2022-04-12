
package model.classe;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private float salario;
    private String dataContratacao;
    private String dataDemissao;
    
    public Funcionario(){
        
    }
    
    public Funcionario(int id, String nome, String cpf, String endereco, String telefone, String email, float salario, 
            String dataContratacao, String dataDemissao){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.dataDemissao = dataDemissao;        
    }
    
    public Funcionario(int id){
        this.id = id;
    }
    
    @Override public String toString() {
        return getId() + " - " + getNome();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the dataContratacao
     */
    public String getDataContratacao() {
        return dataContratacao;
    }

    /**
     * @return the dataDemissao
     */
    public String getDataDemissao() {
        return dataDemissao;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param dataContratacao the dataContratacao to set
     */
    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    /**
     * @param dataDemissao the dataDemissao to set
     */
    public void setDataDemissao(String dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    /**
     * @return the salario
     */
    public float getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

       
}
