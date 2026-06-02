package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private TableView<SaudeDTO> tblConsulta;
    @FXML private TableColumn<SaudeDTO, Integer> colId;
    @FXML private TableColumn<SaudeDTO, String> colNome;
    @FXML private TableColumn<SaudeDTO, Integer> colIdade;
    @FXML private TableColumn<SaudeDTO, String> colSintoma;
    @FXML private TableColumn<SaudeDTO, Integer> colDuracao;
    @FXML private TableColumn<SaudeDTO, String> colDoenca;
    @FXML private TextField txtNome;
    @FXML private TextField txtDuracao;
    @FXML private TextField txtId;
    @FXML private TextField txtSintoma;
    @FXML private TextField txtDoenca;
    @FXML private TextField txtIdade;




    @FXML
    private void btnSalvarAction(ActionEvent event){
        String nome = txtNome.getText();

        int idade = Integer.parseInt(txtIdade.getText());
        String sintoma = txtSintoma.getText();
        String doenca = txtDoenca.getText();
        int diasDuracao = Integer.parseInt(txtDuracao.getText());

        SaudeDTO saudeDto = new SaudeDTO();
        saudeDto.setNome(nome);

        saudeDto.setIdade(idade);
        saudeDto.setSintoma(sintoma);
        saudeDto.setDiasDuracao(diasDuracao);
        saudeDto.setDoencasCronicas(doenca);

        SaudeDAO saudeDao = new SaudeDAO();
        saudeDao.inserirSaude(saudeDto);

        carregarConsulta();
    }

    @FXML
    public void initialize(){
        //colocando etiquetas nas colunas do SB com o mesmo nome do DAO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colSintoma.setCellValueFactory(new PropertyValueFactory<>("sintoma"));
        colDoenca.setCellValueFactory(new PropertyValueFactory<>("doencasCronicas"));
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("diasDuracao"));
        carregarConsulta();
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtId.clear();
        txtNome.clear();
        txtIdade.clear();
        txtSintoma.clear();
        txtDuracao.clear();
        txtDoenca.clear();
    }

    @FXML
    private void carregarConsulta(){
        //Listar recebe return do DAO
        //Listar recebe a tabela setando os itens dela
        SaudeDAO saudeDao = new SaudeDAO();
        ArrayList<SaudeDTO> listarSaude = saudeDao.listarSaude();
        tblConsulta.setItems(FXCollections.observableArrayList(listarSaude));
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();

        if (pacienteSelecionado != null) {
            SaudeDTO saudeDto = new SaudeDTO();

            saudeDto.setId(pacienteSelecionado.getId());
            saudeDto.setNome(txtNome.getText());
            saudeDto.setIdade(Integer.parseInt(txtIdade.getText()));
            saudeDto.setSintoma(txtSintoma.getText());
            saudeDto.setDoencasCronicas(txtDoenca.getText());
            saudeDto.setDiasDuracao(Integer.parseInt(txtDuracao.getText()));


            SaudeDAO saudeDao = new  SaudeDAO();

            saudeDao.atualizarSaude(saudeDto);

            carregarConsulta();
            btnLimparAction(null);
        }

    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();
        if(pacienteSelecionado != null){
            SaudeDAO saudeDAO = new SaudeDAO();
            saudeDAO.excluirSaude(pacienteSelecionado);
        }
        carregarConsulta();
        btnLimparAction(null);
    }

    @FXML
    private void carregarCampos() {
        SaudeDTO saudeDto = tblConsulta.getSelectionModel().getSelectedItem();

        //se nao estiver vazio, faz o setText, setando a informação do texto
        if (saudeDto != null) {
            txtId.setText(String.valueOf(saudeDto.getId()));
            txtNome.setText(saudeDto.getNome());
            txtDoenca.setText(saudeDto.getDoencasCronicas());
            txtSintoma.setText(saudeDto.getSintoma());
            txtDuracao.setText(String.valueOf(saudeDto.getDiasDuracao()));
            txtIdade.setText(String.valueOf(saudeDto.getIdade()));
        }
    }


}