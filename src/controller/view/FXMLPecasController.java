package controller.view;

import controller.dao.crud.FornecedorDAO;
import controller.dao.crud.PecasDAO;
import controller.dao.crud.utils.FornecedorUtils;
import controller.dao.crud.utils.PecasUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import model.classe.Pecas;

public class FXMLPecasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPanePecas;

    @FXML
    private Label lbPecasTitulo;

    @FXML
    private Pane PanePecas;

    @FXML
    private TableView<Pecas> tablePecas = new TableView<>();

    @FXML
    private TableColumn<Pecas, Integer> tableColumnPecasId = new TableColumn<>();

    @FXML
    private TableColumn<Pecas, String> tableColumnPecasNome = new TableColumn<>();

    @FXML
    private TableColumn<Pecas, String> tableColumnPecasMarca = new TableColumn<>();

    @FXML
    private TableColumn<Pecas, Integer> tableColumnPecasQuantidade = new TableColumn<>();

    @FXML
    private TableColumn<Fornecedor, Integer> tableColumnPecasFornecedor = new TableColumn<>();

    @FXML
    private GridPane gridPanePecas;

    @FXML
    private Label lbNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lbMarca;

    @FXML
    private TextField txtMarca;

    @FXML
    private Label lbQuantidade;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private Label lbFornecedor;

    @FXML
    private ComboBox<String> cbFornecedor;

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
        if(txtID.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Não há Peça Selecionado!", "Erro!", 0);
        }
        
        Pecas peca = new Pecas(Integer.parseInt(txtID.getText()));
        
        PecasDAO pDAO = new PecasDAO();
        pDAO.delete(peca);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "A Peça foi apagado com sucesso!");   
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
        // VERIFICA SE HÁ CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtNome.getText().isEmpty() || txtMarca.getText().isEmpty() || txtQuantidade.getText().isEmpty()
                || cbFornecedor.getPromptText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVA PEÇA
        else if (txtID.getText().isEmpty()){
            Pecas peca = new Pecas();
            PecasUtils pDAO = new PecasUtils();

            peca = getDataTableObject();
            boolean status = true;
            
            // AQUI VAMOS SETAR A DATA PRIMEIRA COMPRA NA TABELA FORNECEDOR CASO NÃO TENHA
            if(pDAO.getDataPrimeiraCompraById(peca.getIdFornecedor()).equals(""))
                status = pDAO.setDataPrimeiraCompra(peca.getIdFornecedor());
            
            if(status) // SE A INSERÇÃO DA DATA OCORREU, A GENTE SALVA A PEÇA NO BD
                pDAO.save(peca);

            JOptionPane.showMessageDialog(null, "Peça Salva com Sucesso!");
        }
        
        // ATUALIZA A PEÇA SELECIONADO
        else{
            Pecas peca = new Pecas();
            PecasUtils pDAO = new PecasUtils();

            peca = getDataTableObject();
            boolean status = true;
            
            // AQUI VAMOS SETAR A DATA PRIMEIRA COMPRA NA TABELA FORNECEDOR CASO NÃO TENHA
            if(pDAO.getDataPrimeiraCompraById(peca.getIdFornecedor()).equals(""))
                status = pDAO.setDataPrimeiraCompra(peca.getIdFornecedor());
            
            if(status) // SE A INSERÇÃO DA DATA OCORREU, A GENTE SALVA A PEÇA NO BD
            pDAO.update(peca);

            JOptionPane.showMessageDialog(null, "Peça Alterada com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableCelularesMouseClicked(MouseEvent event) {
        ObservableList<Pecas> selectPecas = tablePecas.getSelectionModel().getSelectedItems();
        
        for(Pecas peca : selectPecas) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(peca);
    }

    @FXML
    void initialize() {
        assert AnchorPanePecas != null : "fx:id=\"AnchorPanePecas\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert lbPecasTitulo != null : "fx:id=\"lbPecasTitulo\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert PanePecas != null : "fx:id=\"PanePecas\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert tablePecas != null : "fx:id=\"tablePecas\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert tableColumnPecasId != null : "fx:id=\"tableColumnPecasId\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert tableColumnPecasNome != null : "fx:id=\"tableColumnPecasNome\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert tableColumnPecasMarca != null : "fx:id=\"tableColumnPecasMarca\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert tableColumnPecasQuantidade != null : "fx:id=\"tableColumnPecasQuantidade\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert tableColumnPecasFornecedor != null : "fx:id=\"tableColumnPecasFornecedor\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert gridPanePecas != null : "fx:id=\"gridPanePecas\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert lbMarca != null : "fx:id=\"lbMarca\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert lbQuantidade != null : "fx:id=\"lbQuantidade\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert txtQuantidade != null : "fx:id=\"txtQuantidade\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert lbFornecedor != null : "fx:id=\"lbFornecedor\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert cbFornecedor != null : "fx:id=\"cbFornecedor\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert lbID != null : "fx:id=\"lbID\" was not injected: check your FXML file 'FXMLPecas.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'FXMLPecas.fxml'.";

        // INSERT OPTIONS NO cbFornecedor
        searchFornecedor();

        // CARREGA A TABELA
        readTable();
        readComboBox();
    }
    
    // initialize é como um FORM_POST_OPEN ou LOAD
    // os métodos de ações estarão aqui pra baixo
    
    // CONSTRUTOR
    public FXMLPecasController(){
        // NÃO PRECISA DO INIATIALIZE, JÁ É AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public Pecas getDataTableObject(){
        Pecas peca = new Pecas();
        
        peca.setNome(txtNome.getText());
        peca.setMarca(txtMarca.getText());
        peca.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        
        PecasUtils pDAO = new PecasUtils();
        peca.setIdFornecedor(pDAO.getFornecedorIdByName(cbFornecedor.getValue()));
        
        if(!txtID.getText().isEmpty())
            peca.setId(Integer.parseInt(txtID.getText()));
        
        return peca;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(Pecas peca){
        txtNome.setText(peca.getNome());
        txtMarca.setText(peca.getMarca());
        txtQuantidade.setText(Integer.toString(peca.getQuantidade()));
        txtID.setText(Integer.toString(peca.getId()));
        
        PecasUtils pDAO = new PecasUtils();
        cbFornecedor.setPromptText(pDAO.getFornecedorNameById(peca.getIdFornecedor()));
        cbFornecedor.setValue(pDAO.getFornecedorNameById(peca.getIdFornecedor()));
        
        cbFornecedor.setPromptText(Integer.toString(peca.getIdFornecedor()));
        
    }
    
    public void limpaCampos(){
        txtNome.setText("");
        txtMarca.setText("");
        txtQuantidade.setText("");
        cbFornecedor.setValue(null);
        cbFornecedor.setPromptText("Fornecedor");
        txtID.setText("");
    }
    
    public void searchFornecedor(){
        FornecedorUtils fUtils = new FornecedorUtils();
        ObservableList<Integer> idFornecedorOList = FXCollections.observableArrayList();
        for(Fornecedor fornecedor : fUtils.getIdFornecedor()){
            idFornecedorOList.add(fornecedor.getId());
        }
        
    }
    
    public void readComboBox(){
        ObservableList<String> fornecedorOList = FXCollections.observableArrayList();
        FornecedorDAO fornDAO = new FornecedorDAO();
        
        for(Fornecedor fornecedor : fornDAO.listar()){
            fornecedorOList.add(fornecedor.getNome());
        }
        
        cbFornecedor.setItems(fornecedorOList);
    }
    
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Pecas> pecasOList = FXCollections.observableArrayList();
        PecasDAO pDAO = new PecasDAO();
        
        // Busca no banco todas as informações
        for(Pecas peca : pDAO.listar()){
            pecasOList.add(new Pecas(
                peca.getId(),
                peca.getNome(),
                peca.getMarca(),
                peca.getQuantidade(),
                peca.getIdFornecedor()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnPecasId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnPecasNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPecasMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnPecasQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnPecasFornecedor.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
        
        // Insere os dados na tabela
        tablePecas.setItems(pecasOList);
    }
}
