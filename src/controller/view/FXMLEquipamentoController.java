package controller.view;

import controller.dao.crud.EquipamentoDAO;
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
import model.classe.Equipamento;

public class FXMLEquipamentoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneEquipamento;

    @FXML
    private Label lbEquipamentoTitulo;

    @FXML
    private Pane PaneEquipamento;

    @FXML
    private TableView<Equipamento> tableEquipamento = new TableView<>();

    @FXML
    private TableColumn<Equipamento, Integer> tableColumnEquipamentoId = new TableColumn<>();

    @FXML
    private TableColumn<Equipamento, String> tableColumnEquipamentoNome = new TableColumn<>();;

    @FXML
    private TableColumn<Equipamento, String> tableColumnEquipamentoMarca = new TableColumn<>();;

    @FXML
    private TableColumn<Equipamento, Float> tableColumnEquipamentoPreco = new TableColumn<>();;

    @FXML
    private TableColumn<Equipamento, String> tableColumnDataCompra = new TableColumn<>();;

    @FXML
    private TableColumn<Equipamento, String> tableColumnDataManutencao = new TableColumn<>();;

    @FXML
    private TableColumn<Equipamento, String> tableColumnEmManutencao = new TableColumn<>();;

    @FXML
    private GridPane gridPaneEquipamento;

    @FXML
    private Label lbNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lbMarca;

    @FXML
    private TextField txtMarca;

    @FXML
    private Label lbPreco;

    @FXML
    private TextField txtPreco;

    @FXML
    private Label lbDataCompra;

    @FXML
    private TextField txtDataCompra;

    @FXML
    private Label lbFuncionario;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbDataUltimaManutencao;

    @FXML
    private TextField txtDataUltimaManutencao;

    @FXML
    private Label lbManutencao;

    @FXML
    private ComboBox<Integer> cbFuncionario;

    @FXML
    private ComboBox<String> cbManutencao;

    @FXML
    private Label lbID;

    @FXML
    private TextField txtID;
    
    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(txtID.getText().isEmpty()) // VERIFICA SE HÁ CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Não há Equipamento Selecionado!", "Erro!", 0);
        }
        
        Equipamento equipamento = new Equipamento(Integer.parseInt(txtID.getText()));
        
        EquipamentoDAO eqDAO = new EquipamentoDAO();
        eqDAO.delete(equipamento);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "O Equipamento foi apagado com sucesso!");
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
        // VERIFICA SE HÁ CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtNome.getText().isEmpty() || txtMarca.getText().isEmpty() || txtPreco.getText().isEmpty()
                || txtDataCompra.getText().isEmpty() || txtDataUltimaManutencao.getText().isEmpty() || cbManutencao.getValue().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVO EQUIPAMENTO
        else if (txtID.getText().isEmpty()){
            Equipamento equipamento = new Equipamento();
            EquipamentoDAO eqDAO = new EquipamentoDAO();

            equipamento = getDataTableObject();
            eqDAO.save(equipamento);

            JOptionPane.showMessageDialog(null, "Equipamento Salvo com Sucesso!");
        }
        
        // ATUALIZA O EQUIPAMENTO SELECIONADO
        else{
            Equipamento equipamento = new Equipamento();
            EquipamentoDAO eqDAO = new EquipamentoDAO();

            equipamento = getDataTableObject();
            eqDAO.update(equipamento);

            JOptionPane.showMessageDialog(null, "Equipamento Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableEquipamentosMouseClicked(MouseEvent event) {
        ObservableList<Equipamento> selectEquipamentos = tableEquipamento.getSelectionModel().getSelectedItems();
        
        for(Equipamento equipamento : selectEquipamentos) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(equipamento);
    }

    @FXML
    void initialize() {
        assert AnchorPaneEquipamento != null : "fx:id=\"AnchorPaneEquipamento\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbEquipamentoTitulo != null : "fx:id=\"lbEquipamentoTitulo\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert PaneEquipamento != null : "fx:id=\"PaneEquipamento\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableEquipamento != null : "fx:id=\"tableEquipamento\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnEquipamentoId != null : "fx:id=\"tableColumnEquipamentoId\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnEquipamentoNome != null : "fx:id=\"tableColumnEquipamentoNome\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnEquipamentoMarca != null : "fx:id=\"tableColumnEquipamentoMarca\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnEquipamentoPreco != null : "fx:id=\"tableColumnEquipamentoPreco\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnDataCompra != null : "fx:id=\"tableColumnDataCompra\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnDataManutencao != null : "fx:id=\"tableColumnDataManutencao\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert tableColumnEmManutencao != null : "fx:id=\"tableColumnEmManutencao\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert gridPaneEquipamento != null : "fx:id=\"gridPaneEquipamento\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbNome != null : "fx:id=\"lbNome\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbMarca != null : "fx:id=\"lbMarca\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbPreco != null : "fx:id=\"lbPreco\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert txtPreco != null : "fx:id=\"txtPreco\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbDataCompra != null : "fx:id=\"lbDataCompra\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert txtDataCompra != null : "fx:id=\"txtDataCompra\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbFuncionario != null : "fx:id=\"lbEquipamento\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbDataUltimaManutencao != null : "fx:id=\"lbDataUltimaManutencao\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbDataUltimaManutencao != null : "fx:id=\"txtDataUltimaManutencao\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbManutencao != null : "fx:id=\"lbManutencao\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbFuncionario != null : "fx:id=\"cbEquipamento\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert cbManutencao != null : "fx:id=\"cbManutencao\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert lbID != null : "fx:id=\"lbID\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'FXMLEquipamento.fxml'.";

     // CARREGA A TABELA
     readTable();
 }
 // initialize é como um FORM_POST_OPEN ou LOAD
 // os métodos de ações estarão aqui pra baixo
 
 // CONSTRUTOR
 public FXMLEquipamentoController(){
     // NÃO PRECISA DO INIATIALIZE, JÁ É AUTOMATICO
 }
 
 // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
 public Equipamento getDataTableObject(){
     Equipamento equipamento = new Equipamento();
     
     equipamento.setNome(txtNome.getText());
     equipamento.setMarca(txtMarca.getText());
     equipamento.setPreco(Float.parseFloat(txtPreco.getText()));
     equipamento.setDataCompra(txtDataCompra.getText());
     equipamento.setDataUltimaManutencao(txtDataUltimaManutencao.getText());
     equipamento.setManutencao(cbManutencao.getPromptText());

     if(!txtID.getText().isEmpty())
         equipamento.setId(Integer.parseInt(txtID.getText()));
        
     return equipamento;
 }
 
 // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
 public void setDataTableObject(Equipamento equipamento){
     txtNome.setText(equipamento.getNome());
     txtMarca.setText(equipamento.getMarca());
     txtPreco.setText(Float.toString(equipamento.getPreco()));
     txtDataCompra.setText(equipamento.getDataCompra());
     txtDataUltimaManutencao.setText(equipamento.getDataUltimaManutencao());
     txtID.setText(Integer.toString(equipamento.getId()));
 }
 
 public void limpaCampos(){
     txtNome.setText("");
     txtMarca.setText("");
     txtPreco.setText("");
     txtDataCompra.setText("");
     txtDataUltimaManutencao.setText("");
     cbManutencao.setPromptText("");
     cbFuncionario.setPromptText("");
     txtID.setText("");
 }
 
 public void readTable(){
     // Array para abrigar as tuplas da tabela
     ObservableList<Equipamento> equipamentosOList = FXCollections.observableArrayList();
     EquipamentoDAO eqDAO = new EquipamentoDAO();
     
     // Busca no banco todas as informações
     for(Equipamento equipamento : eqDAO.listar()){
         equipamentosOList.add(new Equipamento(
             equipamento.getId(),
             equipamento.getNome(),
             equipamento.getMarca(),
             equipamento.getPreco(),
             equipamento.getDataCompra(),
             equipamento.getDataUltimaManutencao(),
             equipamento.getManutencao()
         ));
     }
     
     // Determina qual coluna recebe qual valor do Modelo chamado
     tableColumnEquipamentoId.setCellValueFactory(new PropertyValueFactory<>("id"));
     tableColumnEquipamentoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
     tableColumnEquipamentoMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
     tableColumnEquipamentoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
     tableColumnDataCompra.setCellValueFactory(new PropertyValueFactory<>("dataCompra"));
     tableColumnDataManutencao.setCellValueFactory(new PropertyValueFactory<>("dataUltimaManutencao"));
     tableColumnEmManutencao.setCellValueFactory(new PropertyValueFactory<>("manutencao"));
     
     // Insere os dados na tabela
     tableEquipamento.setItems(equipamentosOList);
 }
 
}
