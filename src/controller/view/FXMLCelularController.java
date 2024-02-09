package controller.view;

import constants.db.ConstantsCelularSQL;
import controller.FXMLControllerBase;
import controller.dao.crud.CelularDAO;
import controller.dao.crud.ClienteDAO;
import controller.dao.crud.utils.CelularUtils;
import controller.dao.crud.utils.ClienteUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.classe.Celular;
import model.classe.CelularNew;
import model.classe.Cliente;

public class FXMLCelularController extends FXMLControllerBase{
    
    Logger LOG = Logger.getLogger(FXMLCelularController.class.getName());
    
    @FXML
    private AnchorPane apanCelulares;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbCelularesTitulo;

    @FXML
    private Pane PaneCelulares;

    @FXML
    private TableView<CelularNew> tableCelulares;

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
    protected void handleBtnNovo(ActionEvent event)
    {
        UTILS.clearTextFields(apanCelulares.getChildren());
    }

    @FXML
    protected void handleBtnSalvar(ActionEvent event)
    {
        if(UTILS.isTextFieldsEmpty(apanCelulares.getChildren()))
        {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        else if (UTILS.isNewEntry(apanCelulares.getChildren()))
        {
            CelularNew celular = getDataTableObject();

            DAO.databaseUpdate(celular, ConstantsCelularSQL.SAVE);
            if(DAO.getStatusCode())
            {
                JOptionPane.showMessageDialog(null, "Celular Salvo com Sucesso!");
            }
        }
        else
        {
            CelularNew celular = getDataTableObject();
            
            DAO.databaseUpdate(celular, ConstantsCelularSQL.UPDATE);
            if(DAO.getStatusCode())
            {
                JOptionPane.showMessageDialog(null, "Celular Alterado com Sucesso!");
            }
        }

        UTILS.clearTextFields(apanCelulares.getChildren());
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    protected void handleBtnApagar(ActionEvent event) {
        if(UTILS.isNewEntry(apanCelulares.getChildren())) // VERIFICA SE Ha CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Nao ha Celular Selecionado!", "Erro!", 0);
        }
        
        CelularNew celular = getDataTableObject();
        
        DAO.databaseUpdate(celular, ConstantsCelularSQL.DELETE);
        
        UTILS.clearTextFields(apanCelulares.getChildren());
        readTable();
        
        if(DAO.getStatusCode())
        {
            JOptionPane.showMessageDialog(null, "O Celular foi apagado com sucesso!");
        }
    }

    @FXML
    protected void tableMouseClicked(MouseEvent event) {
        ObservableList<CelularNew> selectCelulares = tableCelulares.getSelectionModel().getSelectedItems();
        
        for(CelularNew celular : selectCelulares) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(celular);
    }
    
    // inserindo as opcoes no cbPropriedade
    private ObservableList<String> isPropriedade = FXCollections.observableArrayList("Sim", "Nao");
        

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
        
        // inserindo as opcoes no cbCliente
        searchCliente();
        
        readComboBox();       
        readTable();
        UTILS.clearTextFields(apanCelulares.getChildren());
        
    }
    
    // initialize e como um FORM_POST_OPEN ou LOAD
    // os metodos de acoes estarao aqui pra baixo
    
    // CONSTRUTOR
    public FXMLCelularController(){
        // NaO PRECISA DO INIATIALIZE, Ja e AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public CelularNew getDataTableObject(){
        CelularNew celular = new CelularNew();
        
        celular.setModel(txtModelo.getText());
        celular.setManufacturer(txtMarca.getText());
        celular.setYear(txtAno.getText());
        celular.setSerialNum(txtSerial.getText());
        celular.setUseState(txtEstadoUso.getText());
        celular.setIsOurProperty(cbPropriedade.getValue());
        
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
    public void setDataTableObject(CelularNew celular){
        txtID.setText(Integer.toString(celular.getId()));
        txtModelo.setText(celular.getModel());
        txtMarca.setText(celular.getManufacturer());
        txtAno.setText(celular.getYear());
        txtSerial.setText(celular.getSerialNum());
        txtEstadoUso.setText(celular.getUseState());
        cbPropriedade.setValue(celular.getIsOurProperty());
        cbPropriedade.setPromptText(celular.getIsOurProperty());
        
        CelularUtils cDAO = new CelularUtils(); // SETANDO O COMBO BOX CLIENTE
        cbCliente.setPromptText(cDAO.getClientNameById(celular.getIdCliente()));
        cbCliente.setValue(cDAO.getClientNameById(celular.getIdCliente()));
        
        if(cbCliente.getValue().equals("")){ // SETANDO O CB CASO SEJA DA ASSISTÊNCIA
            cbCliente.setPromptText("ASSISTÊNCIA");
            cbCliente.setValue("ASSISTÊNCIA");
        }
    }
    
    public void searchCliente(){
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
        
        // BUSCA NO BANCO AS INFORMAcoES
        for(Cliente cliente : cliDAO.listar()){
            
            clientesOList.add(cliente.getNome());
        }
        
        // insert no ComboBox Cliente
        cbCliente.setItems(clientesOList);
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<CelularNew> celularesOList = FXCollections.observableArrayList();
        ObservableList<Object> objectList = FXCollections.observableArrayList();

        DAO.databaseRead(objectList, ConstantsCelularSQL.LISTAR);

        for(Object object : objectList){
            HashMap<Integer, String> map = (HashMap<Integer,String>) object;
            CelularNew celular = new CelularNew(
                    UTILS.stringToInt(map.get(1)),
                    map.get(2),
                    map.get(3),
                    map.get(4),
                    map.get(5),
                    map.get(6),
                    map.get(7),
                    UTILS.stringToInt(map.get(8))
            );

            celularesOList.add(celular);
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnCelularId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnCelularMarca.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        tableColumnCelularModelo.setCellValueFactory(new PropertyValueFactory<>("model"));
        tableColumnCelularAno.setCellValueFactory(new PropertyValueFactory<>("year"));
        tableColumnCelularSerial.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
        tableColumnCelularEstadoUso.setCellValueFactory(new PropertyValueFactory<>("useState"));
        tableColumnCelularPropriedade.setCellValueFactory(new PropertyValueFactory<>("isOurProperty"));
        tableColumnCelularCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        
        // Insere os dados na tabela
        tableCelulares.setItems(celularesOList);
    }
    
}
