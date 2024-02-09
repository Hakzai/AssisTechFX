package controller.view;

import controller.dao.crud.FuncionarioDAO;
import controller.dao.crud.utils.FuncionarioUtils;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.classe.Funcionario;

public class FXMLFuncionarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneFuncionarios;

    @FXML
    private Label lbFuncionariosTitulo;

    @FXML
    private Pane PaneFuncionarios;

    @FXML
    private TableView<Funcionario> tableFuncionarios = new TableView<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncId = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncNome = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncCPF = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncEndereco = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncTelefone = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncEmail = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncEquipamento = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncDataContratacao = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, Float> tableColumnFuncSalario = new TableColumn<>();

    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncDataDemissao = new TableColumn<>();

    @FXML
    private GridPane gridPaneFuncionarios;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbCPF;

    @FXML
    private TextField txtCPF;

    @FXML
    private Label lbEndereco;

    @FXML
    private TextField txtEndereco;

    @FXML
    private Label lbTelefone;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Label lbEmail;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnApagar;
    
    @FXML
    private Button btnDemitir;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnEquipamento;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbDemissao;

    @FXML
    private TextField txtDataDemissao;

    @FXML
    private Label lbSalario;

    @FXML
    private TextField txtSalario;

    @FXML
    private Label lbID;

    @FXML
    private TextField txtID;
    
    // SETANDO UM FUNCIONARIO EM EVIDENCIA
    private Funcionario funcionarioSelecionado = null; 

    @FXML
    void handleBtnEquipamento(ActionEvent event) throws Exception{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLEquipamento.fxml"));
        Pane root = loader.load();
        FXMLEquipamentoController equipamentoController = (FXMLEquipamentoController) loader.getController();
        
        btnEquipamento.setDisable(true);
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela Equipamentos");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        btnEquipamento.setDisable(false); // APÓS FECHAR A JANELA MOSTRA A TELA CELULAR NOVAMENTE
    }
    
    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(null == getFuncionarioSelecionado()) // VERIFICA SE Ha CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Nao ha Funcionario Selecionado!", "Erro!", 0);
        }
        else{
            int input = JOptionPane.showConfirmDialog(null, "Você tem certeza que quer Apagar este Funcionario?", "Apagar Funcionario", 0, 2);
            if(input == 0){
                FuncionarioDAO fDAO = new FuncionarioDAO();
                fDAO.delete(getFuncionarioSelecionado());
                
                JOptionPane.showMessageDialog(null, "O Funcionario foi apagado com sucesso!");
                limpaCampos();
            }
            readTable();
        }
    }
    
    @FXML
    void handleBtnDemitir(ActionEvent event) {
        if(null == getFuncionarioSelecionado())
            JOptionPane.showMessageDialog(null, "Selecione um Funcionario Valido", "Erro!", 0);
       else if(null != funcionarioSelecionado.getDataDemissao())
            JOptionPane.showMessageDialog(null, "Funcionario ja Demitido!", "Erro!", 0);
        else{    
            int input = JOptionPane.showConfirmDialog(null, "Você tem certeza que quer Demitir este Funcionario?", "Demitir Funcionario", 0, 2);
            if(input == 0){
                FuncionarioUtils fDAO = new FuncionarioUtils();
                fDAO.setDataDemissaoByTableSelect(getFuncionarioSelecionado());
                
                JOptionPane.showMessageDialog(null, "O Funcionario foi demitido com sucesso!");
            }
            readTable();
        }
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
        // VERIFICA SE Ha CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || txtEndereco.getText().isEmpty()
                || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSalario.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVO FUNCIONARIO
        else if (txtID.getText().isEmpty()){
            Funcionario funcionario = new Funcionario();
            FuncionarioDAO fDAO = new FuncionarioDAO();

            funcionario = getDataTableObject();
            fDAO.save(funcionario);

            JOptionPane.showMessageDialog(null, "Funcionario Salvo com Sucesso!");
        }
        
        // ATUALIZA O FUNCIONARIO SELECIONADO
        else{
            Funcionario funcionario = new Funcionario();
            FuncionarioDAO fDAO = new FuncionarioDAO();

            funcionario = getDataTableObject();
            fDAO.update(funcionario);

            JOptionPane.showMessageDialog(null, "Funcionario Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableFuncionariosMouseClicked(MouseEvent event) {
        ObservableList<Funcionario> selectFuncionarios = tableFuncionarios.getSelectionModel().getSelectedItems();
    
        for(Funcionario funcionario : selectFuncionarios){ // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(funcionario);
            setFuncionarioSelecionado(funcionario);
        }
        
        //if(!getFuncionarioSelecionado().getData.)
    }

    @FXML
    void initialize() {
        assert AnchorPaneFuncionarios != null : "fx:id=\"AnchorPaneFuncionarios\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbFuncionariosTitulo != null : "fx:id=\"lbFuncionariosTitulo\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert PaneFuncionarios != null : "fx:id=\"PaneFuncionarios\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableFuncionarios != null : "fx:id=\"tableFuncionarios\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncId != null : "fx:id=\"tableColumnFuncId\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncNome != null : "fx:id=\"tableColumnFuncNome\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncCPF != null : "fx:id=\"tableColumnFuncCPF\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncEndereco != null : "fx:id=\"tableColumnFuncEndereco\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncTelefone != null : "fx:id=\"tableColumnFuncTelefone\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncEmail != null : "fx:id=\"tableColumnFuncEmail\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncEquipamento != null : "fx:id=\"tableColumnFuncEquipamento\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncDataContratacao != null : "fx:id=\"tableColumnFuncDataContratacao\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncSalario != null : "fx:id=\"tableColumnFuncSalario\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert tableColumnFuncDataDemissao != null : "fx:id=\"tableColumnFuncDataDemissao\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert gridPaneFuncionarios != null : "fx:id=\"gridPaneFuncionarios\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbCPF != null : "fx:id=\"lbCPF\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtCPF != null : "fx:id=\"txtCPF\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbEndereco != null : "fx:id=\"lbEndereco\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtEndereco != null : "fx:id=\"txtEndereco\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbTelefone != null : "fx:id=\"lbTelefone\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtTelefone != null : "fx:id=\"txtTelefone\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbEmail != null : "fx:id=\"lbEmail\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert btnApagar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert btnEquipamento != null : "fx:id=\"btnEquipamento\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbDemissao != null : "fx:id=\"lbDemissao\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtDataDemissao != null : "fx:id=\"txtDemissao\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbSalario != null : "fx:id=\"lbSalario\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtSalario != null : "fx:id=\"txtSalario\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert lbID != null : "fx:id=\"lbID\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        assert btnDemitir != null : "fx:id=\"btnDemitir\" was not injected: check your FXML file 'FXMLFuncionario.fxml'.";
        
        // CARREGA A TABELA
        readTable();
    }
    // initialize e como um FORM_POST_OPEN ou LOAD
    // os metodos de acoes estarao aqui pra baixo
    
    // CONSTRUTOR
    public FXMLFuncionarioController(){
        // NaO PRECISA DO INIATIALIZE, Ja e AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public Funcionario getDataTableObject(){
        Funcionario funcionario = new Funcionario();
        
        funcionario.setNome(txtNome.getText());
        funcionario.setCpf(txtCPF.getText());
        funcionario.setEndereco(txtEndereco.getText());
        funcionario.setTelefone(txtTelefone.getText());
        funcionario.setEmail(txtEmail.getText());
        funcionario.setDataDemissao(txtDataDemissao.getText());
        funcionario.setSalario(Float.parseFloat(txtSalario.getText()));

        if(!txtID.getText().isEmpty())
            funcionario.setId(Integer.parseInt(txtID.getText()));
        else
            funcionario.setDataContratacao(getDateTime()); // CHECK SE O FUNCIONARIO e NOVO
            // POIS AQUI SÓ SETO A DATA NO MOMENTO DA CONTRATAcaO
        
        return funcionario;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(Funcionario funcionario){
        txtNome.setText(funcionario.getNome());
        txtCPF.setText(funcionario.getCpf());
        txtEndereco.setText(funcionario.getEndereco());
        txtTelefone.setText(funcionario.getTelefone());
        txtEmail.setText(funcionario.getEmail());
        txtSalario.setText(Float.toString(funcionario.getSalario()));
        if(null != funcionario.getDataDemissao())
            txtDataDemissao.setText(funcionario.getDataDemissao());
        else
            txtDataDemissao.setText("");
        txtID.setText(Integer.toString(funcionario.getId()));
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtCPF.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtSalario.setText("");
        txtDataDemissao.setText("");
        txtID.setText("");
        
        setFuncionarioSelecionado(null);
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Funcionario> funcionariosOList = FXCollections.observableArrayList();
        FuncionarioDAO fDAO = new FuncionarioDAO();
        
        // Busca no banco todas as informacoes
        for(Funcionario funcionario : fDAO.listar()){
            funcionariosOList.add(new Funcionario(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEndereco(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                funcionario.getSalario(),
                funcionario.getDataContratacao(),
                funcionario.getDataDemissao()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnFuncId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnFuncNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnFuncCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnFuncEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnFuncTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnFuncEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnFuncSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tableColumnFuncDataContratacao.setCellValueFactory(new PropertyValueFactory<>("dataContratacao"));
        tableColumnFuncDataDemissao.setCellValueFactory(new PropertyValueFactory<>("dataDemissao"));
        
        // Insere os dados na tabela
        tableFuncionarios.setItems(funcionariosOList);
    }
    
    private String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * @return the funcionarioSelecionado
     */
    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    /**
     * @param funcionarioSelecionado the funcionarioSelecionado to set
     */
    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }
    
}

