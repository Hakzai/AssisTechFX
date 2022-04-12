package controller.view;

import controller.dao.crud.CelularDAO;
import controller.dao.crud.ClienteDAO;
import controller.dao.crud.utils.CelularUtils;
import controller.dao.crud.utils.ClienteUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import model.classe.Celular;
import model.classe.Cliente;

public class FXMLCelularController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbCelularesTitulo;

    @FXML
    private Pane PaneCelulares;

    @FXML
    private TableView<Celular> tableCelulares;

    @FXML
    private TableColumn<Celular, Integer> tableColumnCelularId = new TableColumn<>();

    @FXML
    private TableColumn<Celular, String> tableColumnCelularMarca = new TableColumn<>();
    
    @FXML
    private TableColumn<Celular, String> tableColumnCelularModelo = new TableColumn<>();

    @FXML
    private TableColumn<Celular, String> tableColumnCelularAno = new TableColumn<>();

    @FXML
    private TableColumn<Celular, String> tableColumnCelularSerial = new TableColumn<>();

    @FXML
    private TableColumn<Celular, String> tableColumnCelularEstadoUso = new TableColumn<>();

    @FXML
    private TableColumn<Celular, String> tableColumnCelularPropriedade = new TableColumn<>();

    @FXML
    private TableColumn<Celular, String> tableColumnCelularCliente = new TableColumn<>();

    @FXML
    private GridPane gridPaneCelulares;

    @FXML
    private TextField txtModelo;

    @FXML
    private Label lbModelo;

    @FXML
    private Label lbMarca;

    @FXML
    private TextField txtMarca;

    @FXML
    private Label lbAno;

    @FXML
    private TextField txtAno;

    @FXML
    private Label lbSerial;

    @FXML
    private TextField txtSerial;

    @FXML
    private Label lbCliente;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbEstadoUso;

    @FXML
    private TextField txtEstadoUso;

    @FXML
    private Label lbPropriedade;

    @FXML
    private ComboBox<String> cbCliente;

    @FXML
    private ComboBox<String> cbPropriedade;

    @FXML
    private Label lbID;

    @FXML
    private TextField txtID;

    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(txtID.getText().isEmpty()) // VERIFICA SE HÁ CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Não há Celular Selecionado!", "Erro!", 0);
        }
        
        Celular celular = new Celular(Integer.parseInt(txtID.getText()));
        
        CelularDAO cDAO = new CelularDAO();
        cDAO.delete(celular);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "O Cliente foi apagado com sucesso!");
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
        
        // VERIFICA SE HÁ CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtModelo.getText().isEmpty() || txtMarca.getText().isEmpty() || txtAno.getText().isEmpty()
                || txtSerial.getText().isEmpty() || txtEstadoUso.getText().isEmpty() || cbPropriedade.getValue().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVO CELULAR
        else if (txtID.getText().isEmpty()){
            Celular celular = new Celular();
            CelularDAO cDAO = new CelularDAO();

            celular = getDataTableObject();
            cDAO.save(celular);

            JOptionPane.showMessageDialog(null, "Celular Salvo com Sucesso!");
        }
        
        // ATUALIZA O CELULAR SELECIONADO
        else{
            Celular celular = new Celular();
            CelularDAO cDAO = new CelularDAO();

            celular = getDataTableObject();
            cDAO.update(celular);

            JOptionPane.showMessageDialog(null, "Celular Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableCelularesMouseClicked(MouseEvent event) {
        ObservableList<Celular> selectCelulares = tableCelulares.getSelectionModel().getSelectedItems();
        
        for(Celular celular : selectCelulares) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(celular);
    }
    
    // inserindo as opções no cbPropriedade
    private ObservableList<String> isPropriedade = FXCollections.observableArrayList("Sim", "Não");
        

    @FXML
    void initialize() {
        assert lbCelularesTitulo != null : "fx:id=\"lbCelularesTitulo\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert PaneCelulares != null : "fx:id=\"PaneCelulares\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableCelulares != null : "fx:id=\"tableCelulares\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularId != null : "fx:id=\"tableColumnCelularId\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularMarca != null : "fx:id=\"tableColumnCelularMarca\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularModelo != null : "fx:id=\"tableColumnCelularModelo\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularAno != null : "fx:id=\"tableColumnCelularAno\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularSerial != null : "fx:id=\"tableColumnCelularSerial\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularEstadoUso != null : "fx:id=\"tableColumnCelularEstadoUso\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularPropriedade != null : "fx:id=\"tableColumnCelularPropriedade\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert tableColumnCelularCliente != null : "fx:id=\"tableColumnCelularCliente\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert gridPaneCelulares != null : "fx:id=\"gridPaneCelulares\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert txtModelo != null : "fx:id=\"txtModelo\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbModelo != null : "fx:id=\"lbModelo\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbMarca != null : "fx:id=\"lbMarca\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbAno != null : "fx:id=\"lbAno\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert txtAno != null : "fx:id=\"txtAno\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbSerial != null : "fx:id=\"lbSerial\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert txtSerial != null : "fx:id=\"txtSerial\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbCliente != null : "fx:id=\"lbCliente\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbEstadoUso != null : "fx:id=\"lbEstadoUso\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert txtEstadoUso != null : "fx:id=\"txtEstadoUso\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbPropriedade != null : "fx:id=\"lbPropriedade\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert cbCliente != null : "fx:id=\"cbCliente\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert cbPropriedade != null : "fx:id=\"cbPropriedade\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert lbID != null : "fx:id=\"lbID\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'FXMLCelular.fxml'.";
        
        // insert no ComboBox Propriedade
        cbPropriedade.setItems(isPropriedade);
        
        // inserindo as opções no cbCliente
        searchClientes();
        
        readComboBox();       
        readTable();
    }

    
    // initialize é como um FORM_POST_OPEN ou LOAD
    // os métodos de ações estarão aqui pra baixo
    
    // CONSTRUTOR
    public FXMLCelularController(){
        // NÃO PRECISA DO INIATIALIZE, JÁ É AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public Celular getDataTableObject(){
        Celular celular = new Celular();
        
        celular.setModelo(txtModelo.getText());
        celular.setMarca(txtMarca.getText());
        celular.setAno(txtAno.getText());
        celular.setSerial(txtSerial.getText());
        celular.setEstadoUso(txtEstadoUso.getText());
        celular.setIsPropriedade(cbPropriedade.getValue());
        
        CelularUtils cDAO = new CelularUtils(); // CONVERTENDO O COMBO BOX CLIENTE PARA ID_CLIENTE
        celular.setIdCliente(cDAO.getClientIdByName(cbCliente.getValue()));
        
        if(cbCliente.getValue().equals("ASSISTÊNCIA")){ // SETANDO O CB CASO SEJA DA ASSISTÊNCIA
            celular.setIdCliente(0);
        }
        
        if(!txtID.getText().isEmpty())
            celular.setId(Integer.parseInt(txtID.getText()));
        
        return celular;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(Celular celular){
        txtID.setText(Integer.toString(celular.getId()));
        txtModelo.setText(celular.getModelo());
        txtMarca.setText(celular.getMarca());
        txtAno.setText(celular.getAno());
        txtSerial.setText(celular.getSerial());
        txtEstadoUso.setText(celular.getEstadoUso());
        cbPropriedade.setValue(celular.getIsPropriedade());
        cbPropriedade.setPromptText(celular.getIsPropriedade());
        
        CelularUtils cDAO = new CelularUtils(); // SETANDO O COMBO BOX CLIENTE
        cbCliente.setPromptText(cDAO.getClientNameById(celular.getIdCliente()));
        cbCliente.setValue(cDAO.getClientNameById(celular.getIdCliente()));
        
        if(cbCliente.getValue().equals("")){ // SETANDO O CB CASO SEJA DA ASSISTÊNCIA
            cbCliente.setPromptText("ASSISTÊNCIA");
            cbCliente.setValue("ASSISTÊNCIA");
        }
    }
    
    public void limpaCampos(){
        txtID.setText("");
        txtModelo.setText("");
        txtMarca.setText("");
        txtAno.setText("");
        txtSerial.setText("");
        txtEstadoUso.setText("");
        cbPropriedade.setPromptText("Nosso?");
        cbPropriedade.setValue(null);
        cbCliente.setPromptText("Proprietário");
        cbCliente.setValue(null);
    }
    
    public void searchClientes(){
        ClienteUtils cUtils = new ClienteUtils();
        ObservableList<Integer> idClienteOList = FXCollections.observableArrayList();
        for(Cliente cliente : cUtils.getIdClientes()){
            idClienteOList.add(cliente.getId());
        }
    }
    
    public void readComboBox(){
        // ARRAY PARA ABRIGAR OS DADOS DO CLIENTE
        ObservableList<String> clientesOList = FXCollections.observableArrayList();
        ClienteDAO cliDAO = new ClienteDAO();
        
        clientesOList.add("ASSISTÊNCIA"); // ADICIONANDO O NOME DA ASSISTÊNCIA NA LISTA
        
        // BUSCA NO BANCO AS INFORMAÇÕES
        for(Cliente cliente : cliDAO.listar()){
            
            clientesOList.add(cliente.getNome());
        }
        
        // insert no ComboBox Cliente
        cbCliente.setItems(clientesOList);
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Celular> celularesOList = FXCollections.observableArrayList();
        CelularDAO cDAO = new CelularDAO();
        
        // Busca no banco todas as informações
        for(Celular celular : cDAO.listar()){
            celularesOList.add(new Celular(
                celular.getId(),
                celular.getModelo(),
                celular.getMarca(),
                celular.getAno(),
                celular.getSerial(),
                celular.getEstadoUso(),
                celular.getIsPropriedade(),
                celular.getIdCliente()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnCelularId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnCelularMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableColumnCelularModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableColumnCelularAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tableColumnCelularSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        tableColumnCelularEstadoUso.setCellValueFactory(new PropertyValueFactory<>("estadoUso"));
        tableColumnCelularPropriedade.setCellValueFactory(new PropertyValueFactory<>("isPropriedade"));
        tableColumnCelularCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        
        // Insere os dados na tabela
        tableCelulares.setItems(celularesOList);
    }
}
