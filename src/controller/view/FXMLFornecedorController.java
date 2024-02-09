package controller.view;

import controller.dao.crud.FornecedorDAO;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javax.swing.JOptionPane;
import model.classe.Fornecedor;

public class FXMLFornecedorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneFornecedor;

    @FXML
    private Label lbFornecedorTitulo;

    @FXML
    private Pane PaneFornecedor;

    @FXML
    private TableView<Fornecedor> tableFornecedor = new TableView<>();

    @FXML
    private TableColumn<Fornecedor, Integer> tableColumnFornecedorId = new TableColumn<>();

    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorNome = new TableColumn<>();;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorCNPJ = new TableColumn<>();;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorTelefone = new TableColumn<>();;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnDataCompra = new TableColumn<>();;

    @FXML
    private GridPane gridPaneFornecedor;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbCNPJ;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private Label lbTelefone;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Label lbDataCompra;

    @FXML
    private TextField txtDataCompra;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbID;

    @FXML
    private TextField txtID;

    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(txtID.getText().isEmpty()) // VERIFICA SE Ha CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Nao ha Fornecedor Selecionado!", "Erro!", 0);
        }
        
        Fornecedor fornecedor = new Fornecedor(Integer.parseInt(txtID.getText()));
        
        FornecedorDAO fDAO = new FornecedorDAO();
        fDAO.delete(fornecedor);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "O Fornecedor foi apagado com sucesso!");
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
        // VERIFICA SE Ha CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtNome.getText().isEmpty() || txtCNPJ.getText().isEmpty() || txtTelefone.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVO FORNECEDOR
        else if (txtID.getText().isEmpty()){
            Fornecedor fornecedor = new Fornecedor();
            FornecedorDAO fDAO = new FornecedorDAO();

            fornecedor = getDataTableObject();
            fDAO.save(fornecedor);

            JOptionPane.showMessageDialog(null, "Fornecedor Salvo com Sucesso!");
        }

        // ATUALIZA O FORNECEDOR SELECIONADO
        else{
            Fornecedor fornecedor = new Fornecedor();
            FornecedorDAO fDAO = new FornecedorDAO();

            fornecedor = getDataTableObject();
            fDAO.update(fornecedor);

            JOptionPane.showMessageDialog(null, "Fornecedor Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableFornecedorMouseClicked(MouseEvent event) {
        ObservableList<Fornecedor> selectFornecedor = tableFornecedor.getSelectionModel().getSelectedItems();
        
        for(Fornecedor fornecedor : selectFornecedor) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(fornecedor);
    }

    @FXML
    void initialize() {
        assert AnchorPaneFornecedor != null : "fx:id=\"AnchorPaneFornecedor\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert lbFornecedorTitulo != null : "fx:id=\"lbFornecedorTitulo\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert PaneFornecedor != null : "fx:id=\"PaneFornecedor\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert tableFornecedor != null : "fx:id=\"tableFornecedor\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert tableColumnFornecedorId != null : "fx:id=\"tableColumnFornecedorId\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert tableColumnFornecedorNome != null : "fx:id=\"tableColumnFornecedorNome\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert tableColumnFornecedorCNPJ != null : "fx:id=\"tableColumnFornecedorCNPJ\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert tableColumnFornecedorTelefone != null : "fx:id=\"tableColumnFornecedorTelefone\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert tableColumnDataCompra != null : "fx:id=\"tableColumnDataCompra\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert gridPaneFornecedor != null : "fx:id=\"gridPaneFornecedor\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert lbCNPJ != null : "fx:id=\"lbCNPJ\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert txtCNPJ != null : "fx:id=\"txtCNPJ\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert lbTelefone != null : "fx:id=\"lbTelefone\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert txtTelefone != null : "fx:id=\"txtTelefone\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert lbDataCompra != null : "fx:id=\"lbDataCompra\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert txtDataCompra != null : "fx:id=\"txtDataCompra\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert lbID != null : "fx:id=\"lbID\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'FXMLFornecedor.fxml'.";

        // CARREGA A TABELA
        readTable();
    }
    // initialize e como um FORM_POST_OPEN ou LOAD
    // os metodos de acoes estarao aqui pra baixo
    
    // CONSTRUTOR
    public FXMLFornecedorController(){
        // NaO PRECISA DO INIATIALIZE, Ja e AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public Fornecedor getDataTableObject(){
        Fornecedor fornecedor = new Fornecedor();
        
        fornecedor.setNome(txtNome.getText());
        fornecedor.setCnpj(txtCNPJ.getText());
        fornecedor.setTelefone(txtTelefone.getText());
        fornecedor.setDataPrimeiraCompra(txtDataCompra.getText());
        // fornecedor.setId(Integer.parseInt(txtID.getText()));
        
        return fornecedor;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(Fornecedor fornecedor){
        txtNome.setText(fornecedor.getNome());
        txtCNPJ.setText(fornecedor.getCnpj());
        txtTelefone.setText(fornecedor.getTelefone());
        txtDataCompra.setText(fornecedor.getDataPrimeiraCompra());
        txtID.setText(Integer.toString(fornecedor.getId()));

        // APÓS O CLIQUE NA LINHA, A DATA PRIMEIRA COMPRA e DESABILITADA
        // SOMENTE NA COMPRA DE PEcAS SERa SETADA OU CLICANDO EM NOVO
        txtDataCompra.setDisable(true);

    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtCNPJ.setText("");
        txtTelefone.setText("");
        txtDataCompra.setText("");
        txtDataCompra.setDisable(false); // HABILITA O CAMPO PARA PREENCHIMENTO DO CADASTRO
        txtID.setText("");
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Fornecedor> fornecedorOList = FXCollections.observableArrayList();
        FornecedorDAO fDAO = new FornecedorDAO();
        
        // Busca no banco todas as informacoes
        for(Fornecedor fornecedor : fDAO.listar()){
            fornecedorOList.add(new Fornecedor(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getTelefone(),
                fornecedor.getDataPrimeiraCompra()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnFornecedorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnFornecedorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnFornecedorCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        tableColumnFornecedorTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnDataCompra.setCellValueFactory(new PropertyValueFactory<>("dataPrimeiraCompra"));
        
        // Insere os dados na tabela
        tableFornecedor.setItems(fornecedorOList);
    }
    
    private String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
