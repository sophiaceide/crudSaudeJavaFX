package com.template;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class MainController {
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
    @FXML private RadioButton rbDoenca;
    @FXML private Label lblMensagem;
    @FXML private Label lblValidacao;
    @FXML private Label lblMensagemDados;



    @FXML
    private void btnSalvarAction(ActionEvent event) {

        if (!preencherCampos()) {
            return;
        }

        String nome = txtNome.getText();

        int idade = Integer.parseInt(txtIdade.getText());
        String sintoma = txtSintoma.getText();
        int diasDuracao = Integer.parseInt(txtDuracao.getText());

        SaudeDTO saudeDto = new SaudeDTO();
        saudeDto.setNome(nome);

        saudeDto.setIdade(idade);
        saudeDto.setSintoma(sintoma);
        saudeDto.setDiasDuracao(diasDuracao);
        if (rbDoenca.isSelected()) {
            saudeDto.setDoencasCronicas("s");
        } else {
            saudeDto.setDoencasCronicas("n");
        }

        SaudeDAO saudeDao = new SaudeDAO();
        saudeDao.inserirSaude(saudeDto);
        if(saudeDto != null){
            lblMensagem.setVisible(true);
            PauseTransition pausa = new PauseTransition(Duration.seconds(3));
            pausa.setOnFinished(e -> lblMensagem.setVisible(false));
            pausa.play();
        }
        carregarConsulta();
        btnLimparAction(null); // Limpa os campos automaticamente após salvar (Ótimo UX!)




    }

    @FXML
    public void initialize() {
        lblValidacao.setVisible(false);
        lblMensagemDados.setVisible(false);
        //colocando etiquetas nas colunas do SB com o mesmo nome do DAO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colSintoma.setCellValueFactory(new PropertyValueFactory<>("sintoma"));
        colDoenca.setCellValueFactory(new PropertyValueFactory<>("doencasCronicas"));
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("diasDuracao"));



        btnEditar.disableProperty().bind(txtNome.textProperty().isEmpty());
        btnDeletar.disableProperty().bind(txtNome.textProperty().isEmpty());
        btnSalvar.disableProperty().bind(txtNome.textProperty().isEmpty());
        btnLimpar.disableProperty().bind(txtNome.textProperty().isEmpty());


        carregarConsulta();
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtIdade.clear();
        txtSintoma.clear();
        txtDuracao.clear();
    }

    @FXML
    private void carregarConsulta() {
        //Listar recebe return do DAO
        //Listar recebe a tabela setando os itens dela
        SaudeDAO saudeDao = new SaudeDAO();
        ArrayList<SaudeDTO> listarSaude = saudeDao.listarSaude();
        tblConsulta.setItems(FXCollections.observableArrayList(listarSaude));


    }

    @FXML
    private void btnEditarAction(ActionEvent event) {

        if (!preencherCampos()) {
            return;
        }

        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();

        if (pacienteSelecionado != null) {
            SaudeDTO saudeDto = new SaudeDTO();

            saudeDto.setId(pacienteSelecionado.getId());
            saudeDto.setNome(txtNome.getText());
            saudeDto.setIdade(Integer.parseInt(txtIdade.getText()));
            saudeDto.setSintoma(txtSintoma.getText());
            if (rbDoenca.isSelected()) {
                saudeDto.setDoencasCronicas("s");
            } else {
                saudeDto.setDoencasCronicas("n");
            }
            saudeDto.setDiasDuracao(Integer.parseInt(txtDuracao.getText()));


            SaudeDAO saudeDao = new SaudeDAO();

            saudeDao.atualizarSaude(saudeDto);

            carregarConsulta();
            btnLimparAction(null);
        }

    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();
        if (pacienteSelecionado != null) {
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
            if (rbDoenca.isSelected()) {
                saudeDto.setDoencasCronicas("s");
            } else {
                saudeDto.setDoencasCronicas("n");
            }
            txtSintoma.setText(saudeDto.getSintoma());
            txtDuracao.setText(String.valueOf(saudeDto.getDiasDuracao()));
            txtIdade.setText(String.valueOf(saudeDto.getIdade()));

        }
    }
    private boolean verificarLetra(String texto) {
        String regra = "^[a-zA-ZáéíóúàèìòùâêîôûãõçÇÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕ\\s]+$";
        return texto.matches(regra); //metodo matches retorna boolean (se coincidiu com a regra ou nao
    }
    private boolean preencherCampos() {
        if (txtNome.getText().trim().isEmpty() ||
                txtIdade.getText().trim().isEmpty() ||
                txtSintoma.getText().trim().isEmpty() ||
                txtDuracao.getText().trim().isEmpty()) {


            //lblValidacao.setText("Por favor, preencha todos os campos!");
            lblValidacao.setVisible(true);

            // Faz o aviso sumir em 3 segundos
            PauseTransition pausa = new PauseTransition(Duration.seconds(3));
            pausa.setOnFinished(e -> lblValidacao.setVisible(false));
            pausa.play();

            return false;
        }
        if(!verificarLetra(txtNome.getText())){
            lblValidacao.setText("Erro: O nome deve conter apenas letras e espaços");
            lblValidacao.setVisible(true);
            PauseTransition pausa = new PauseTransition(Duration.seconds(3));
            pausa.setOnFinished(ev -> lblValidacao.setVisible(false));
            pausa.play();
            return false;
        }
        if(!verificarLetra(txtSintoma.getText())){
            lblValidacao.setText("Erro: Os sintomas devem conter apenas letras e espaços");
            lblValidacao.setVisible(true);
            PauseTransition pausa = new PauseTransition(Duration.seconds(3));
            pausa.setOnFinished(ev -> lblValidacao.setVisible(false));
            pausa.play();
            return false;
        }
        try {
            Integer.parseInt(txtIdade.getText().trim());
            Integer.parseInt(txtDuracao.getText().trim());

        } catch (NumberFormatException e) {

            lblMensagemDados.setText("Não inserir letras nos campos idade e duração!");
            lblMensagemDados.setVisible(true);

            PauseTransition pausa = new PauseTransition(Duration.seconds(3));
            pausa.setOnFinished(ev -> lblMensagemDados.setVisible(false));
            pausa.play();

            return false;
        }

        return true;
    }
}