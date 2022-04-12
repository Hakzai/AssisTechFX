package controller.view;

import javafx.event.ActionEvent;
import controller.dao.crud.CaixaDAO;
import controller.dao.crud.utils.CaixaUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.classe.Caixa;

public class FXMLCaixaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbCaixaTitulo;

    @FXML
    private Pane PaneCaixa;

    @FXML
    private TableView<Caixa> tableCaixa = new TableView<>();

    @FXML
    private TableColumn<Caixa, Integer> tableColumnCaixaIdRecibo = new TableColumn<>();

    @FXML
    private TableColumn<Caixa, String> tableColumnCaixaTipoServico = new TableColumn<>();

    @FXML
    private TableColumn<Caixa, Double> tableColumnCaixaValorFinal = new TableColumn<>();

    @FXML
    private TableColumn<Caixa, String> tableColumnCaixaFormaPagamento = new TableColumn<>();

    @FXML
    private TableColumn<Caixa, Integer> tableColumnCaixaOrdemNumero = new TableColumn<>();

    @FXML
    private GridPane gridPaneCaixa;

    @FXML
    private TextField txtTipoServico;

    @FXML
    private Label lbTipoServico;

    @FXML
    private Label lbValorFinal;

    @FXML
    private TextField txtValorFinal;

    @FXML
    private Label lbOrdemNumero;

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbFormaPagamento;

    @FXML
    private ComboBox<Integer> cbOrdemNumero;

    @FXML
    private ComboBox<String> cbFormaPagamento;
    private ObservableList<String> formaPagamento = FXCollections.observableArrayList("Cartao de Credito", "Debito", "Dinheiro", "PIX"); // popula o combo box

    @FXML
    private TextField txtIdRecibo;

    @FXML
    private Label lbIdRecibo;

    @FXML
    private Button btnRelatorios;
    
    @FXML
    void handleBtnNovo(ActionEvent event) {
        limpaCampos();
    }
    
    @FXML
    void handleBtnApagar(ActionEvent event) {
        if(txtIdRecibo.getText().isEmpty()) // VERIFICA SE HÁ CAIXA SELECIONADO
        {
            JOptionPane.showMessageDialog(null, "Não há Caixa Selecionado!", "Erro!", 0);
        }
        
        Caixa caixa = new Caixa(Integer.parseInt(txtIdRecibo.getText()));
        
        CaixaDAO cxDAO = new CaixaDAO();
        cxDAO.delete(caixa);
        limpaCampos();
        readTable();
        
        JOptionPane.showMessageDialog(null, "O Caixa foi apagado com sucesso!");
    }

    @FXML
    void handleBtnSalvar(ActionEvent event) {
        
        // VERIFICA SE HÁ CAMPOS VAZIOS CAMPOS PREENCHIDOS
        if(txtTipoServico.getText().isEmpty() || txtValorFinal.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos precisam estar preenchidos", "Erro!", 0);
            return;
        }
        
        // CRIA NOVO CAIXA
        else if (txtIdRecibo.getText().isEmpty()){
            Caixa caixa = new Caixa();
            CaixaDAO cxDAO = new CaixaDAO();

            caixa = getDataTableObject();
            cxDAO.save(caixa);

            JOptionPane.showMessageDialog(null, "Caixa Salvo com Sucesso!");
        }
        
        // ATUALIZA O CAIXA SELECIONADO
        else{
            Caixa caixa = new Caixa();
            CaixaDAO cxDAO = new CaixaDAO();

            caixa = getDataTableObject();
            cxDAO.update(caixa);

            JOptionPane.showMessageDialog(null, "Caixa Alterado com Sucesso!");
        }
        
        limpaCampos(); // LIMPA OS FIELDS TXT
        readTable();   // CARREGA DE NOVO A TABELA
    }
    
    @FXML
    void tableCaixaMouseClicked(MouseEvent event) {
        ObservableList<Caixa> selectCaixa = tableCaixa.getSelectionModel().getSelectedItems();
        
        for(Caixa caixa : selectCaixa) // RECUPERA OS DADOS DA TABELA NOS FIELDS
            setDataTableObject(caixa);
    }

    @FXML
    void initialize() {
        assert lbCaixaTitulo != null : "fx:id=\"lbCaixaTitulo\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert PaneCaixa != null : "fx:id=\"PaneCaixa\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert tableCaixa != null : "fx:id=\"tableCaixa\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert tableColumnCaixaIdRecibo != null : "fx:id=\"tableColumnCaixaReciboNumero\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert tableColumnCaixaTipoServico != null : "fx:id=\"tableColumnCaixaTipoServico\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert tableColumnCaixaValorFinal != null : "fx:id=\"tableColumnCaixaValorFinal\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert tableColumnCaixaFormaPagamento != null : "fx:id=\"tableColumnCaixaFormaPagamento\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert tableColumnCaixaOrdemNumero != null : "fx:id=\"tableColumnCaixaOrdemNumero\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert gridPaneCaixa != null : "fx:id=\"gridPaneCaixa\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert txtTipoServico != null : "fx:id=\"txtTipoServico\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert lbTipoServico != null : "fx:id=\"lbTipoServico\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert lbValorFinal != null : "fx:id=\"lbValorFinal\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert txtValorFinal != null : "fx:id=\"txtValorFinal\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert lbOrdemNumero != null : "fx:id=\"lbOrdemNumero\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert btnApagar != null : "fx:id=\"btnApagar\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert btnNovo != null : "fx:id=\"btnNovo\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert btnSalvar != null : "fx:id=\"btnSalvar\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert lbFormaPagamento != null : "fx:id=\"lbFormaPagamento\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert cbOrdemNumero != null : "fx:id=\"cbOrdemNumero\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert cbFormaPagamento != null : "fx:id=\"cbFormaPagamento\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert txtIdRecibo != null : "fx:id=\"txtIdRecibo\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert lbIdRecibo != null : "fx:id=\"lbIdRecibo\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        assert btnRelatorios != null : "fx:id=\"btnRelatorios\" was not injected: check your FXML file 'FXMLCaixa.fxml'.";
        
        // INSERT NO COMBOBOX Forma de Pagamento
        cbFormaPagamento.setItems(formaPagamento);
        
        // INSERT NO COMBOBOX Nro Ordem
        CaixaUtils cxUtils = new CaixaUtils();
        ObservableList<Integer> nroOrdemOList = FXCollections.observableArrayList();
        for(Caixa caixa : cxUtils.getNroOrdens()){
            nroOrdemOList.add(caixa.getNroOrdem());
        }
        ObservableList<Integer> nroOrdem = FXCollections.observableArrayList(nroOrdemOList);
        
        cbOrdemNumero.setItems(nroOrdem);
        
        // CARREGA A TABELA
        readTable();
    }
    
     // initialize é como um FORM_POST_OPEN ou LOAD
    // os métodos de ações estarão aqui pra baixo
    
    // CONSTRUTOR
    public FXMLCaixaController(){
        // NÃO PRECISA DO INIATIALIZE, JÁ É AUTOMATICO
    }
    
    // INSERE OS DADOS DOS CAMPOS NO DAO E DEPOIS NA TABELA
    public Caixa getDataTableObject(){
        Caixa caixa = new Caixa();
        
        caixa.setTipoServico(txtTipoServico.getText());
        caixa.setValorFinal(Double.parseDouble(txtValorFinal.getText()));
        //caixa.setFormaPagamento(cbFormaPagamento.getPromptText());
        caixa.setFormaPagamento(cbFormaPagamento.getValue());
        caixa.setNroOrdem(cbOrdemNumero.getValue());
        
        if(!txtIdRecibo.getText().isEmpty())
            caixa.setIdRecibo(Integer.parseInt(txtIdRecibo.getText()));
        
        return caixa;
    }
    
    // INSERE OS DADOS DA TABELA/OBJETO PARA OS CAMPOS
    public void setDataTableObject(Caixa caixa){
        txtTipoServico.setText(caixa.getTipoServico());
        txtValorFinal.setText(Double.toString(caixa.getValorFinal()));
        cbFormaPagamento.setValue(caixa.getFormaPagamento());
        cbFormaPagamento.setPromptText(caixa.getFormaPagamento());
        cbOrdemNumero.setValue(caixa.getNroOrdem());
        cbOrdemNumero.setPromptText(Integer.toString(caixa.getNroOrdem()));
        txtIdRecibo.setText(Integer.toString(caixa.getIdRecibo()));
    }
    
    public void limpaCampos(){
        txtTipoServico.setText("");
        txtValorFinal.setText("");
        cbFormaPagamento.setPromptText("Escolha a Forma que o Cliente pagou");
        cbFormaPagamento.setValue(null);
        cbOrdemNumero.setPromptText("Selecione a Ordem");
        cbOrdemNumero.setValue(null);
        txtIdRecibo.setText("");
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Caixa> caixaOList = FXCollections.observableArrayList();
        CaixaDAO cxDAO = new CaixaDAO();
        
        // Busca no banco todas as informações
        for(Caixa caixa : cxDAO.listar()){
            caixaOList.add(new Caixa(
                caixa.getIdRecibo(),
                caixa.getTipoServico(),
                caixa.getValorFinal(),
                caixa.getFormaPagamento(),
                caixa.getNroOrdem()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnCaixaIdRecibo.setCellValueFactory(new PropertyValueFactory<>("idRecibo"));
        tableColumnCaixaTipoServico.setCellValueFactory(new PropertyValueFactory<>("tipoServico"));
        tableColumnCaixaValorFinal.setCellValueFactory(new PropertyValueFactory<>("valorFinal"));
        tableColumnCaixaFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));
        tableColumnCaixaOrdemNumero.setCellValueFactory(new PropertyValueFactory<>("nroOrdem"));
        
        // Insere os dados na tabela
        tableCaixa.setItems(caixaOList);
    }
}
