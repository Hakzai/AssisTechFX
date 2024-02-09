package controller.view;

import controller.connection.ConnectionFactory;
import controller.dao.crud.ClienteDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.classe.Cliente;
import controller.model.ServicesDAO;

public class FXMLClientesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneClientes;

    @FXML
    private Label lbClientesTitulo;

    @FXML
    private Pane PaneClientes;

    @FXML
    private TableView<Cliente> tableClientes = new TableView<>();
    
    @FXML
    private TableColumn<Cliente, Integer> tableColumnClientesId = new TableColumn<>();

    @FXML
    private TableColumn<Cliente, String> tableColumnClientesNome = new TableColumn<>();

    @FXML
    private TableColumn<Cliente, String> tableColumnClientesCPF = new TableColumn<>();

    @FXML
    private TableColumn<Cliente, String> tableColumnClientesEndereco = new TableColumn<>();

    @FXML
    private TableColumn<Cliente, String> tableColumnClientesTelefone = new TableColumn<>();

    @FXML
    private TableColumn<Cliente, String> tableColumnClientesEmail = new TableColumn<>();

    @FXML
    private GridPane gridPaneClientes;

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
    private Button btnNovo;

    @FXML
    private Button btnCelulares;

    @FXML
    private Button btnSalvar;
        
    @FXML
    private Label lbID;

    @FXML
    private TextField txtID;

    @FXML
    void handleBtnCelulares(ActionEvent event) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLCelular.fxml"));
        Pane root = loader.load();
        FXMLCelularController celularController = (FXMLCelularController) loader.getController();
        
        btnCelulares.setDisable(true);
        Stage clientes = (Stage) btnCelulares.getScene().getWindow(); 
        clientes.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela Celulares");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        btnCelulares.setDisable(false); // APÓS FECHAR A JANELA MOSTRA A TELA CELULAR NOVAMENTE
        clientes.show();
        
    }
    
    @FXML
    void handleBtnNovo(ActionEvent event){
        limpaCampos();
    }
    
    @FXML
    void handleBtnApagar(ActionEvent event){
        if(txtID.getText().isEmpty()) // VERIFICA SE Ha CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Nao ha Cliente Selecionado!", "Erro!", 0);
        }
        
        Cliente cliente = new Cliente(Integer.parseInt(txtID.getText()));
        
        ClienteDAO cDAO = new ClienteDAO();
        cDAO.delete(cliente);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "O Cliente foi apagado com sucesso!");
    }

    @FXML
    void handleBtnSalvar(ActionEvent event){
        
        // VERIFICA SE Ha CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || txtEndereco.getText().isEmpty()
                || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVO CLIENTE
        else if (txtID.getText().isEmpty()){
            Cliente cliente = new Cliente();
            ClienteDAO cDAO = new ClienteDAO();

            cliente = getDataTableObject();
            cDAO.save(cliente);

            JOptionPane.showMessageDialog(null, "Cliente Salvo com Sucesso!");
        }
        
        // ATUALIZA O CLIENTE SELECIONADO
        else{
            Cliente cliente = new Cliente();
            ClienteDAO cDAO = new ClienteDAO();

            cliente = getDataTableObject();
            cDAO.update(cliente);

            JOptionPane.showMessageDialog(null, "Cliente Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }
    
    @FXML
    void tableClientesMouseClicked(MouseEvent event) {
        ObservableList<Cliente> selectClientes = tableClientes.getSelectionModel().getSelectedItems();
        
        for(Cliente cliente : selectClientes) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(cliente);
    }

    @FXML
    void initialize() {
        assert AnchorPaneClientes != null : "fx:id=\"AnchorPaneClientes\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbClientesTitulo != null : "fx:id=\"lbClientesTitulo\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert PaneClientes != null : "fx:id=\"PaneClientes\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableClientes != null : "fx:id=\"tableClientes\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableColumnClientesId != null : "fx:id=\"tableColumnClientesId\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableColumnClientesNome != null : "fx:id=\"tableColumnClientesNome\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableColumnClientesCPF != null : "fx:id=\"tableColumnClientesCPF\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableColumnClientesEndereco != null : "fx:id=\"tableColumnClientesEndereco\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableColumnClientesTelefone != null : "fx:id=\"tableColumnClientesTelefone\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert tableColumnClientesEmail != null : "fx:id=\"tableColumnClientesEmail\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert gridPaneClientes != null : "fx:id=\"gridPaneClientes\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbCPF != null : "fx:id=\"lbCPF\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert txtCPF != null : "fx:id=\"txtCPF\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbEndereco != null : "fx:id=\"lbEndereco\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert txtEndereco != null : "fx:id=\"txtEndereco\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbTelefone != null : "fx:id=\"lbTelefone\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert txtTelefone != null : "fx:id=\"txtTelefone\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbEmail != null : "fx:id=\"lbEmail\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert btnCelulares != null : "fx:id=\"btnCelulares\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert lbID != null : "fx:id=\"lbID\" was not injected: check your FXML file 'FXMLClientes.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'FXMLClientes.fxml'.";

        // CARREGA A TABELA
        readTable();
        
    }
    // initialize e como um FORM_POST_OPEN ou LOAD
    // os metodos de acoes estarao aqui pra baixo
    
    // CONSTRUTOR
    public FXMLClientesController(){
        // NaO PRECISA DO INIATIALIZE, Ja e AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public Cliente getDataTableObject(){
        Cliente cliente = new Cliente();
        
        cliente.setNome(txtNome.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        
        if(!txtID.getText().isEmpty())
            cliente.setId(Integer.parseInt(txtID.getText()));
        
        return cliente;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(Cliente cliente){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtEndereco.setText(cliente.getEndereco());
        txtTelefone.setText(cliente.getTelefone());
        txtEmail.setText(cliente.getEmail());
        txtID.setText(Integer.toString(cliente.getId()));
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtCPF.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtID.setText("");
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Cliente> clientesOList = FXCollections.observableArrayList();
        ClienteDAO cDAO = new ClienteDAO();
        
        // Busca no banco todas as informacoes
        for(Cliente cliente : cDAO.listar()){
            clientesOList.add(new Cliente(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getEmail()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnClientesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnClientesNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClientesCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnClientesEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnClientesTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnClientesEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        // Insere os dados na tabela
        tableClientes.setItems(clientesOList);
    }
    
}
