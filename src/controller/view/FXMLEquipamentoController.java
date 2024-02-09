package controller.view;

import controller.dao.crud.EquipamentoDAO;
import controller.dao.crud.FuncionarioDAO;
import controller.dao.crud.utils.EquipamentoUtils;
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
import model.classe.Equipamento;
import model.classe.Funcionario;

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
    private ComboBox<String> cbFuncionario;

    @FXML
    private ComboBox<String> cbManutencao;
    private ObservableList<String> optionsManutencao = FXCollections.observableArrayList("SIM", "NAO"); // popula o combo box

    @FXML
    private Label lbID;

    @FXML
    private TextField txtID;
    
    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(txtID.getText().isEmpty()) // VERIFICA SE Ha CLIENTE SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Nao ha Equipamento Selecionado!", "Erro!", 0);
        }
        
        else{
            int input = JOptionPane.showConfirmDialog(null, "Você tem certeza que quer Apagar este Equipamento?", "Apagar Equipamento", 0, 2);
            if(input == 0){
                Equipamento equipamento = new Equipamento(Integer.parseInt(txtID.getText()));

                EquipamentoDAO eqDAO = new EquipamentoDAO();
                eqDAO.delete(equipamento);

                JOptionPane.showMessageDialog(null, "O Equipamento foi apagado com sucesso!");
                limpaCampos();
            }
            readTable();
        }
    }

    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
        cbFuncionario.setDisable(true); // DESABILITA O CB EM CASO DE NOVO EQUIP
    }

    // PRECISA DEBUGAR ESSE MeTODO PRA VER PORQUE PAROU DE SALVAR
    
    @FXML
    void handleBtnSalvar(ActionEvent event) {
        // VERIFICA SE Ha CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtNome.getText().isEmpty() || txtMarca.getText().isEmpty() || txtPreco.getText().isEmpty()
                || txtDataCompra.getText().isEmpty()){
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
            equipamento = getDataTableObject();

            // SE TEM UMA DATA DE MANUTENcaO E O STATUS MANUTENcaO NaO ESTa MUDANDO, ENTaO VAMOS MANTER ELA
            if(!isManutencaoChangeToYes(equipamento)){
                EquipamentoDAO eqDAO = new EquipamentoDAO();
                eqDAO.update(equipamento);
            }
            else{ // CASO CONTRaRIO PEGAREMOS A DATA DE HOJE E SETAREMOS NO CAMPO
                EquipamentoUtils eqDAO = new EquipamentoUtils();
                eqDAO.update(equipamento);
            }

            JOptionPane.showMessageDialog(null, "Equipamento Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }

    @FXML
    void tableEquipamentosMouseClicked(MouseEvent event) {
        cbFuncionario.setDisable(false); // HABILITANDO O CB PARA EDIcaO
        cbManutencao.setDisable(false);
        
        ObservableList<Equipamento> selectEquipamentos = tableEquipamento.getSelectionModel().getSelectedItems();
        
        for(Equipamento equipamento : selectEquipamentos){ // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(equipamento);
            
            if (null == equipamento.getDataUltimaManutencao())
                txtDataUltimaManutencao.setText("<Sem Manutencao>");
        }
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
     readComboBox();
 }
 // initialize e como um FORM_POST_OPEN ou LOAD
 // os metodos de acoes estarao aqui pra baixo
 
 // CONSTRUTOR
 public FXMLEquipamentoController(){
     // NaO PRECISA DO INIATIALIZE, Ja e AUTOMATICO
 }
 
  /***
  * 
  * 
  * AQUI e O SEGUINTE, FALTA FAZER O NOME SELECIONADO NO COMBO BOX ASSIGNAR COM O EQUIPAMENTO ESCOLHIDO
  * PARA STATUS EM USO DO EQUIPAMENTO
  */
 
 // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
 public Equipamento getDataTableObject(){
     Equipamento equipamento = new Equipamento();
     
     equipamento.setNome(txtNome.getText());
     equipamento.setMarca(txtMarca.getText());
     equipamento.setPreco(Float.parseFloat(txtPreco.getText()));
     equipamento.setDataCompra(txtDataCompra.getText());
     
     // AQUI SETAMOS O TEXTO QUE APARECE NO FIELD txtDataUltimaManutencao CASO NAO TENHA DATA
     if(!txtDataUltimaManutencao.getText().equals("<Sem Manutencao>"))
        equipamento.setDataUltimaManutencao(txtDataUltimaManutencao.getText());
     
     // AQUI VERIFICAMOS SE O STATUS ESTa DISABLE PARA COLOCAR 'NAO' COMO PADRaO
     if(!cbManutencao.isDisabled())
        equipamento.setManutencao(cbManutencao.getValue());
     else
        equipamento.setManutencao("NAO");
          
     // O EQUIPAMENTO SÓ PODE SER ASSIGNADO A UM FUNCIONARIO CASO Ja TENHA SIDO CRIADO
     // CASO CONTRaRIO PRECISA PRIMEIRO CRIAR O EQUIP PRA DEPOIS ASSIGNAR A UM FUNCIONARIO
     if(!txtID.getText().isEmpty()){
         equipamento.setId(Integer.parseInt(txtID.getText()));
     
        if(null != cbFuncionario.getValue()){
            EquipamentoUtils eqDAO = new EquipamentoUtils();
            eqDAO.setFuncionarioUsingEquipmentByName(cbFuncionario.getValue(), Integer.parseInt(txtID.getText()));
        }
     }
             
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
     cbManutencao.setPromptText(equipamento.getManutencao());
     cbManutencao.setValue(equipamento.getManutencao());
     
     if(null != String.valueOf(equipamento.getIdFuncionario())){
        EquipamentoUtils eqDAO = new EquipamentoUtils();
        cbFuncionario.setPromptText(eqDAO.getFuncionarioNameById(equipamento.getId()));
        cbFuncionario.setValue(eqDAO.getFuncionarioNameById(equipamento.getId()));
     }
     else{
        cbFuncionario.setPromptText("Este equipamento nao esta sendo utilizado no Momento");
        cbFuncionario.setValue(null);
     }
     
           
 }
 
 public void limpaCampos(){
    txtNome.setText("");
    txtMarca.setText("");
    txtPreco.setText("");
    txtDataCompra.setText("");
    txtDataUltimaManutencao.setText("");
    txtID.setText("");
    cbManutencao.setValue(null);
    cbManutencao.setPromptText("Status");
    cbFuncionario.setValue(null);
    cbFuncionario.setPromptText("Que esta usando no Momento");
    
 }
 
 public void readComboBox(){
     ObservableList<String> funcionariosOList = FXCollections.observableArrayList();
        FuncionarioUtils funcDAO = new FuncionarioUtils();
        
        for(Funcionario funcionario : funcDAO.listarAtivos()){
            funcionariosOList.add(funcionario.getNome());
        }
        
     cbFuncionario.setItems(funcionariosOList);
     
     cbManutencao.setItems(optionsManutencao);
 }
 
 public void readTable(){
     // Array para abrigar as tuplas da tabela
     ObservableList<Equipamento> equipamentosOList = FXCollections.observableArrayList();
     EquipamentoDAO eqDAO = new EquipamentoDAO();
     
     // Busca no banco todas as informacoes
     for(Equipamento equipamento : eqDAO.listar()){
         equipamentosOList.add(new Equipamento(
             equipamento.getId(),
             equipamento.getNome(),
             equipamento.getMarca(),
             equipamento.getPreco(),
             equipamento.getDataCompra(),
             equipamento.getDataUltimaManutencao(),
             equipamento.getManutencao(),
             equipamento.getIdFuncionario()
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
 
    public boolean isManutencaoChangeToYes(Equipamento e){
        
        EquipamentoUtils eDAO = new EquipamentoUtils();
        String manutencaoCompare = eDAO.getManutencaoStatusByEquipmentId(e.getId());
        
        // VERIFICAR SE O CAMPO STATUS ESTa MUDANDO DE 'NAO' PARA 'SIM'
        if(!manutencaoCompare.equals(e.getManutencao()) && e.getManutencao().equals("SIM"))
            return true;
        
        return false;
    }
 
}
