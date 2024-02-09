package controller.view;

import controller.dao.crud.CelularDAO;
import controller.dao.crud.OrdemServicoDAO;
import controller.dao.crud.utils.CelularUtils;
import controller.dao.crud.utils.FuncionarioUtils;
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
import model.classe.Celular;
import model.classe.Funcionario;
import model.classe.OrdemServico;

public class FXMLOrdemServicoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneOrdemServico;

    @FXML
    private Label lbOrdemServicoTitulo;

    @FXML
    private Pane PaneOrdemServico;

    @FXML
    private TableView<OrdemServico> tableOrdemServico = new TableView<>();

    @FXML
    private TableColumn<OrdemServico, Integer> tableColumnOrdemNumero = new TableColumn<>();

    @FXML
    private TableColumn<OrdemServico, String> tableColumnOrdemAluguel = new TableColumn<>();

    @FXML
    private TableColumn<OrdemServico, String> tableColumnOrdemDataEntrada = new TableColumn<>();

    @FXML
    private TableColumn<OrdemServico, Integer> tableColumnOrdemAparelho = new TableColumn<>();

    @FXML
    private TableColumn<OrdemServico, Float> tableColumnOrdemOrcamento = new TableColumn<>();

    @FXML
    private TableColumn<OrdemServico, String> tableColumnOrdemDataSaida = new TableColumn<>();

    @FXML
    private TableColumn<OrdemServico, Integer> tableColumnOrdemTecnico = new TableColumn<>();

    @FXML
    private GridPane gridPaneOrdemServico;

    @FXML
    private TextField txtDataEntrada;

    @FXML
    private Label lbDataEntrada;

    @FXML
    private Label lbOrcamento;

    @FXML
    private TextField txtOrcamento;

    @FXML
    private Label lbTecnico;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbAparelho;

    @FXML
    private Label lbAluguel;

    @FXML
    private ComboBox<Integer> cbTecnico;

    @FXML
    private ComboBox<String> cbAluguel;
    private ObservableList<String> aluguel = FXCollections.observableArrayList("Sim", "Nao"); // popula o combo box

    @FXML
    private ComboBox<String> cbAparelho;

    @FXML
    private TextField txtDataSaida;

    @FXML
    private Label lbDataSaida;
    
    @FXML
    private TextField txtNumeroOrdem;

    @FXML
    private Label lbNumeroOrdem;
    
    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(txtNumeroOrdem.getText().isEmpty()) // VERIFICA SE Ha CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Nao ha OrdemServico Selecionado!", "Erro!", 0);
        }
        
        OrdemServico ordemServico = new OrdemServico(Integer.parseInt(txtNumeroOrdem.getText()));
        
        OrdemServicoDAO osDAO = new OrdemServicoDAO();
        osDAO.delete(ordemServico);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "O OrdemServico foi apagado com sucesso!");
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
         // VERIFICA SE Ha CAMPOS VAZIOS CAMPOS PREENCHIDOS
         AnchorPaneOrdemServico.getChildren();
        if(cbAluguel.getValue().isEmpty() || txtDataEntrada.getText().isEmpty() || txtOrcamento.getText().isEmpty() || txtDataSaida.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVA ORDEM
        else if (txtNumeroOrdem.getText().isEmpty()){
            OrdemServico ordemServico = new OrdemServico();
            OrdemServicoDAO osDAO = new OrdemServicoDAO();

            ordemServico = getDataTableObject();
            osDAO.save(ordemServico);

            JOptionPane.showMessageDialog(null, "OrdemServico Salva com Sucesso!");
        }
        
        // ATUALIZA A ORDEM SELECIONADO
        else{
            OrdemServico ordemServico = new OrdemServico();
            OrdemServicoDAO osDAO = new OrdemServicoDAO();

            ordemServico = getDataTableObject();
            osDAO.update(ordemServico);

            JOptionPane.showMessageDialog(null, "OrdemServico Alterada com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableOrdemMouseClicked(MouseEvent event) {
        ObservableList<OrdemServico> selectOrdem = tableOrdemServico.getSelectionModel().getSelectedItems();
        
        for(OrdemServico ordemServico : selectOrdem) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(ordemServico);
    }

    @FXML
    void initialize() {
        assert AnchorPaneOrdemServico != null : "fx:id=\"AnchorPaneOrdemServico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbOrdemServicoTitulo != null : "fx:id=\"lbOrdemServicoTitulo\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert PaneOrdemServico != null : "fx:id=\"PaneOrdemServico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableOrdemServico != null : "fx:id=\"tableOrdemServico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemNumero != null : "fx:id=\"tableColumnOrdemNumero\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemAluguel != null : "fx:id=\"tableColumnOrdemAluguel\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemDataEntrada != null : "fx:id=\"tableColumnOrdemDataEntrada\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemAparelho != null : "fx:id=\"tableColumnOrdemAparelho\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemOrcamento != null : "fx:id=\"tableColumnOrdemOrcamento\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemDataSaida != null : "fx:id=\"tableColumnOrdemDataSaida\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert tableColumnOrdemTecnico != null : "fx:id=\"tableColumnOrdemTecnico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert gridPaneOrdemServico != null : "fx:id=\"gridPaneOrdemServico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert txtDataEntrada != null : "fx:id=\"txtDataEntrada\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbDataEntrada != null : "fx:id=\"lbDataEntrada\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbOrcamento != null : "fx:id=\"lbOrcamento\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert txtOrcamento != null : "fx:id=\"txtOrcamento\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbTecnico != null : "fx:id=\"lbTecnico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbAparelho != null : "fx:id=\"lbAparelho\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbAluguel != null : "fx:id=\"lbAluguel\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert cbTecnico != null : "fx:id=\"cbTecnico\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert cbAluguel != null : "fx:id=\"cbAluguel\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert cbAparelho != null : "fx:id=\"cbAparelho\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert txtDataSaida != null : "fx:id=\"txtDataSaida\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbDataSaida != null : "fx:id=\"lbDataSaida\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert txtNumeroOrdem != null : "fx:id=\"txtNumeroOrdem\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        assert lbNumeroOrdem != null : "fx:id=\"lbOrdemNumero\" was not injected: check your FXML file 'FXMLOrdemServico.fxml'.";
        
        cbAluguel.setItems(aluguel);
        
        // CARREGA A TABELA
        readTable();
        readComboBoxAparelho();
    }
    // initialize e como um FORM_POST_OPEN ou LOAD
    // os metodos de acoes estarao aqui pra baixo
    
    // CONSTRUTOR
    public FXMLOrdemServicoController(){
        // NaO PRECISA DO INIATIALIZE, Ja e AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public OrdemServico getDataTableObject(){
        OrdemServico ordemServico = new OrdemServico();
        
        ordemServico.setAluguel(cbAluguel.getValue());
        ordemServico.setDataEntrada(txtDataEntrada.getText());
        //ordemServico.setIdAparelho(cbAparelho.getValue());
        ordemServico.setOrcamento(Float.parseFloat(txtOrcamento.getText()));
        ordemServico.setDataSaida(txtDataSaida.getText());
        ordemServico.setIdTecnico(cbTecnico.getValue());
        
        if(!txtNumeroOrdem.getText().isEmpty())
            ordemServico.setNumeroOrdem(Integer.parseInt(txtNumeroOrdem.getText()));
        
        return ordemServico;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(OrdemServico ordemServico){
        cbAluguel.setValue(ordemServico.getAluguel());
        txtDataEntrada.setText(ordemServico.getDataEntrada());
        //cbAparelho.setValue(ordemServico.getIdAparelho());
        txtOrcamento.setText(Float.toString(ordemServico.getOrcamento()));
        txtDataSaida.setText(ordemServico.getDataSaida());
        cbTecnico.setValue(ordemServico.getIdTecnico());
        txtNumeroOrdem.setText(Integer.toString(ordemServico.getNumeroOrdem()));
    }
    
    public void limpaCampos(){
        cbAluguel.setPromptText("");
        cbAluguel.setValue("");
        txtDataEntrada.setText("");
        txtOrcamento.setText("");
        cbAluguel.setPromptText("Selecione o celular");
        cbAluguel.setValue("");
        cbTecnico.setPromptText("Selecione o responsavel pelo servico");
        cbTecnico.setValue(0);
        txtDataSaida.setText("");
        txtNumeroOrdem.setText("");
    }
    
    /*public void searchAparelho(){
        CelularUtils cUtils = new CelularUtils();
        ObservableList<Integer> idCelularOList = FXCollections.observableArrayList();
        for(Celular celular : cUtils.getId()){
            idCelularOList.add(celular.getId());
        }
    }
    
    public void searchFuncionario(){
        FuncionarioUtils fUtils = new FuncionarioUtils();
        ObservableList<Integer> idFuncionarioOList = FXCollections.observableArrayList();
        for(Funcionario funcionario : fUtils.getId()){
            idFuncionarioOList.add(funcionario.getId());
        }
    }*/
    
    public void readComboBoxAparelho(){
        ObservableList<String> celularesOList = FXCollections.observableArrayList();
        CelularDAO celDAO = new CelularDAO();
        
        for(Celular celular : celDAO.listar())
            celularesOList.add(celular.getModelo());
        
        cbAparelho.setItems(celularesOList);
    }
    
    public void readComboBoxTecnico(){
        
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<OrdemServico> ordemServicosOList = FXCollections.observableArrayList();
        OrdemServicoDAO osDAO = new OrdemServicoDAO();
        
        // Busca no banco todas as informacoes
        for(OrdemServico ordemServico : osDAO.listar()){
            ordemServicosOList.add(new OrdemServico(
                ordemServico.getNumeroOrdem(),
                ordemServico.getAluguel(),
                ordemServico.getDataEntrada(),
                ordemServico.getIdAparelho(),
                ordemServico.getOrcamento(),
                ordemServico.getDataSaida(),
                ordemServico.getIdTecnico()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnOrdemNumero.setCellValueFactory(new PropertyValueFactory<>("numeroOrdem"));
        tableColumnOrdemAluguel.setCellValueFactory(new PropertyValueFactory<>("aluguel"));
        tableColumnOrdemDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
        tableColumnOrdemAparelho.setCellValueFactory(new PropertyValueFactory<>("idAparelho"));
        tableColumnOrdemOrcamento.setCellValueFactory(new PropertyValueFactory<>("orcamento"));
        tableColumnOrdemDataSaida.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
        tableColumnOrdemTecnico.setCellValueFactory(new PropertyValueFactory<>("idTecnico"));
        
        // Insere os dados na tabela
        tableOrdemServico.setItems(ordemServicosOList);
    }
    
}
