/**
 * Sample Skeleton for 'FXMLMain.fxml' Controller Class
 */

package controller.view;

import controller.dao.crud.ClienteDAO;
import controller.dao.crud.MainDAO;
import controller.view.FXMLClientesController;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.classe.Cliente;
import model.classe.Main;

public class FXMLMainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="AnchorPaneMain"
    private AnchorPane AnchorPaneMain; // Value injected by FXMLLoader

    @FXML // fx:id="btnClientes"
    private Button btnClientes; // Value injected by FXMLLoader

    @FXML // fx:id="btnPecas"
    private Button btnPecas; // Value injected by FXMLLoader

    @FXML // fx:id="btnAluguel"
    private Button btnAluguel; // Value injected by FXMLLoader

    @FXML // fx:id="btnOrdemServico"
    private Button btnOrdemServico; // Value injected by FXMLLoader

    @FXML // fx:id="btnFornecedores"
    private Button btnFornecedores; // Value injected by FXMLLoader

    @FXML // fx:id="btnCaixa"
    private Button btnCaixa; // Value injected by FXMLLoader

    @FXML // fx:id="btnFuncionarios"
    private Button btnFuncionarios; // Value injected by FXMLLoader

    @FXML // fx:id="btnRelatorios"
    private Button btnRelatorios; // Value injected by FXMLLoader

    @FXML // fx:id="btnEquipamento"
    private Button btnEquipamento; // Value injected by FXMLLoader

    @FXML // fx:id="borderMainServicos"
    private BorderPane borderMainServicos; // Value injected by FXMLLoader

    @FXML // fx:id="tableMainServicos"
    private TableView<Main> tableMainServicos = new TableView<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainId"
    private TableColumn<Main, Integer> tableColumnMainId = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainNome"
    private TableColumn<Main, String> tableColumnMainNome = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainTelefone"
    private TableColumn<Main, String> tableColumnMainTelefone = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainDataInicio"
    private TableColumn<Main, String> tableColumnMainDataInicio = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainDataPlanejada"
    private TableColumn<Main, String> tableColumnMainDataPlanejada = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainDataFinal"
    private TableColumn<Main, String> tableColumnMainDataFinal = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainValorPlanejado"
    private TableColumn<Main, Float> tableColumnMainValorPlanejado = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainValorFinal"
    private TableColumn<Main, Float> tableColumnMainValorFinal = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnMainStatus"
    private TableColumn<Main, String> tableColumnMainStatus = new TableColumn<>(); // Value injected by FXMLLoader

    @FXML // fx:id="lbMainTitulo"
    private Label lbMainTitulo; // Value injected by FXMLLoader

    @FXML
    void handleBtnAluguel(ActionEvent event) throws Exception {
        System.out.println("Teste btnAluguel");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLAluguel.fxml"));
        Pane root = loader.load();
        FXMLAluguelController aluguelController = (FXMLAluguelController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela Aluguel");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnCaixa(ActionEvent event) throws Exception{
        System.out.println("Teste btnCaixa");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLCaixa.fxml"));
        Pane root = loader.load();
        FXMLCaixaController caixaController = (FXMLCaixaController) loader.getController();
        
        Stage main = (Stage) btnCaixa.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela de Controle do Caixa");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnClientes(ActionEvent event) throws Exception{
        System.out.println("Teste btnCliente");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLClientes.fxml"));
        Pane root = loader.load();
        FXMLClientesController clientesController = (FXMLClientesController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela Clientes");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.wait();
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE

    }

    @FXML
    void handleBtnFornecedores(ActionEvent event) throws Exception {
        System.out.println("Teste btnFornecedores");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLFornecedor.fxml"));
        Pane root = loader.load();
        FXMLFornecedorController fornecedorController = (FXMLFornecedorController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela de Fornecedores");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnFuncionarios(ActionEvent event) throws Exception {
        System.out.println("Teste btnFuncionarios");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLFuncionario.fxml"));
        Pane root = loader.load();
        FXMLFuncionarioController funcionarioController = (FXMLFuncionarioController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela de Funcionarios");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnEquipamento(ActionEvent event) throws Exception {
        System.out.println("Teste btnEquipamentos");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLEquipamento.fxml"));
        Pane root = loader.load();
        FXMLEquipamentoController equipamentoController = (FXMLEquipamentoController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela de Funcionarios");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnOrdemServico(ActionEvent event) throws Exception {
        System.out.println("Teste btnOrdemServico");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLOrdemServico.fxml"));
        Pane root = loader.load();
        FXMLOrdemServicoController ordemServicoController = (FXMLOrdemServicoController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela de Ordem de Servico");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnPecas(ActionEvent event) throws Exception{
        System.out.println("Teste btnPecas");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLPecas.fxml"));
        Pane root = loader.load();
        FXMLPecasController pecasController = (FXMLPecasController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela Pecas");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE
    }

    @FXML
    void handleBtnRelatorios(ActionEvent event) throws Exception {
        System.out.println("Teste btnRelatorios");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/telasfx/FXMLRelatorios.fxml"));
        Pane root = loader.load();
        FXMLRelatoriosController relatoriosController = (FXMLRelatoriosController) loader.getController();
        
        Stage main = (Stage) btnClientes.getScene().getWindow(); 
        main.hide(); // ESCONDE A TELA MAIN
        
        Scene scene = new Scene(root);	
        Stage stage = new Stage();
        stage.setTitle("Tela de Funcionarios");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        main.show(); // APÓS FECHAR A JANELA MOSTRA A TELA MAIN NOVAMENTE

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert AnchorPaneMain != null : "fx:id=\"AnchorPaneMain\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnClientes != null : "fx:id=\"btnClientes\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnPecas != null : "fx:id=\"btnPecas\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnAluguel != null : "fx:id=\"btnAluguel\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnOrdemServico != null : "fx:id=\"btnOrdem\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnFornecedores != null : "fx:id=\"btnFornecedores\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnCaixa != null : "fx:id=\"btnCaixa\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnFuncionarios != null : "fx:id=\"btnFuncionarios\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnRelatorios != null : "fx:id=\"btnRelatorios\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnEquipamento != null : "fx:id=\"btnManutencao\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert borderMainServicos != null : "fx:id=\"borderMainServicos\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableMainServicos != null : "fx:id=\"tableMainServicos\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainId != null : "fx:id=\"tableColumnMainId\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainNome != null : "fx:id=\"tableColumnMainNome\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainTelefone != null : "fx:id=\"tableColumnMainTelefone\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainDataInicio != null : "fx:id=\"tableColumnMainDataInicio\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainDataPlanejada != null : "fx:id=\"tableColumnMainDataPlanejada\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainDataFinal != null : "fx:id=\"tableColumnMainDataFinal\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainValorPlanejado != null : "fx:id=\"tableColumnMainValorPlanejado\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainValorFinal != null : "fx:id=\"tableColumnMainValorFinal\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tableColumnMainStatus != null : "fx:id=\"tableColumnMainStatus\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert lbMainTitulo != null : "fx:id=\"lbMainTitulo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        
        /* // desabilitando os botoes sem tela e coluna sem valor
        btnRelatorios.setDisable(true);
        btnPecas.setDisable(true);
        btnFornecedores.setDisable(true);
        btnEquipamento.setDisable(true);
        btnAluguel.setDisable(true);
        tableColumnMainDataPlanejada.setVisible(false);
        tableColumnMainStatus.setVisible(false);
        *
        * desabilitei direto no editor
        */

        readTable();
    }
    
    public void readTable(){
        // Array para abrigar as tuplas da tabela
        ObservableList<Main> mainOList = FXCollections.observableArrayList();
        MainDAO cDAO = new MainDAO();
        
        // Busca no banco todas as informacoes
        for(Main main : cDAO.listar()){
            mainOList.add(new Main(
                main.getIdServico(),
                main.getNomeCliente(),
                main.getTelefoneCliente(),
                main.getDataInicio(),
                main.getDataFinal(),
                main.getValorPlanejado(),
                main.getValorFinal()
            ));
        }
        
        // Determina qual coluna recebe qual valor do Modelo chamado
        tableColumnMainId.setCellValueFactory(new PropertyValueFactory<>("idServico"));
        tableColumnMainNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableColumnMainTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneCliente"));
        tableColumnMainDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        tableColumnMainDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        tableColumnMainValorPlanejado.setCellValueFactory(new PropertyValueFactory<>("valorPlanejado"));
        tableColumnMainValorFinal.setCellValueFactory(new PropertyValueFactory<>("valorFinal"));
        
        // Insere os dados na tabela
        tableMainServicos.setItems(mainOList);
    }


}
