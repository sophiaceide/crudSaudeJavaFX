package com.template.controller;

import com.template.model.dto.SaudeDTO;
import com.template.services.SaudeService;
import com.template.util.DialogUtil;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtIdade;
    @FXML private TextField txtSintoma;
    @FXML private TextField txtDuracao;
    @FXML private RadioButton rbDoenca;

    // Encapsulamento e Imutabilidade: Instância do serviço para regras de negócio
    private final SaudeService saudeService = new SaudeService();

    @FXML
    public void initialize() {
        // Mapeamento das colunas com a SaudeDTO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colSintoma.setCellValueFactory(new PropertyValueFactory<>("sintoma"));
        colDoenca.setCellValueFactory(new PropertyValueFactory<>("doencasCronicas"));
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("diasDuracao"));

        // Regra de desabilitação de botões baseada no campo Nome
        btnEditar.disableProperty().bind(txtNome.textProperty().isEmpty());
        btnDeletar.disableProperty().bind(txtNome.textProperty().isEmpty());
        btnSalvar.disableProperty().bind(txtNome.textProperty().isEmpty());
        btnLimpar.disableProperty().bind(txtNome.textProperty().isEmpty());

        carregarConsulta();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!validarFormulario()) {
            return;
        }

        try {
            saudeService.salvarPaciente(
                    txtNome.getText().trim(),
                    Integer.parseInt(txtIdade.getText().trim()),
                    txtSintoma.getText().trim(),
                    Integer.parseInt(txtDuracao.getText().trim()),
                    rbDoenca.isSelected()
            );

            DialogUtil.mostrarInfo("Paciente cadastrado com sucesso!");
            carregarConsulta();
            btnLimparAction(null);
        } catch (Exception e) {
            DialogUtil.mostrarErro("Erro ao salvar paciente: " + e.getMessage());
        }
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();
        if (pacienteSelecionado == null) {
            DialogUtil.mostrarErro("Selecione um paciente na tabela para editar.");
            return;
        }

        if (!validarFormulario()) {
            return;
        }

        try {
            saudeService.atualizarPaciente(
                    pacienteSelecionado.getId(),
                    txtNome.getText().trim(),
                    Integer.parseInt(txtIdade.getText().trim()),
                    txtSintoma.getText().trim(),
                    Integer.parseInt(txtDuracao.getText().trim()),
                    rbDoenca.isSelected()
            );

            DialogUtil.mostrarInfo("Paciente atualizado com sucesso!");
            carregarConsulta();
            btnLimparAction(null);
        } catch (Exception e) {
            DialogUtil.mostrarErro("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();

        if (pacienteSelecionado == null) {
            DialogUtil.mostrarErro("Selecione um paciente na tabela para excluir.");
            return;
        }

        try {
            // 1. Deleta no banco via Service
            saudeService.excluirPaciente(pacienteSelecionado);

            // 2. Limpa a seleção atual da tabela
            tblConsulta.getSelectionModel().clearSelection();

            // 3. Recarrega os dados e força a atualização visual IMEDIATAMENTE
            carregarConsulta();
            tblConsulta.refresh();
            btnLimparAction(null);

            // 4. Mostra a mensagem por último (com a tela já atualizada ao fundo)
            DialogUtil.mostrarInfo("Paciente deletado com sucesso!");

        } catch (Exception e) {
            DialogUtil.mostrarErro("Erro ao deletar paciente: " + e.getMessage());
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtIdade.clear();
        txtSintoma.clear();
        txtDuracao.clear();
        rbDoenca.setSelected(false);
    }

    @FXML
    private void carregarCampos() {
        SaudeDTO pacienteSelecionado = tblConsulta.getSelectionModel().getSelectedItem();
        if (pacienteSelecionado != null) {
            txtId.setText(String.valueOf(pacienteSelecionado.getId()));
            txtNome.setText(pacienteSelecionado.getNome());
            txtIdade.setText(String.valueOf(pacienteSelecionado.getIdade()));
            txtSintoma.setText(pacienteSelecionado.getSintoma());
            txtDuracao.setText(String.valueOf(pacienteSelecionado.getDiasDuracao()));
            rbDoenca.setSelected("s".equalsIgnoreCase(pacienteSelecionado.getDoencasCronicas()));
        }
    }

    private void carregarConsulta() {
        tblConsulta.setItems(FXCollections.observableArrayList(saudeService.buscarTodos()));
    }

    private boolean validarFormulario() {
        String nome = txtNome.getText().trim();
        String idade = txtIdade.getText().trim();
        String sintoma = txtSintoma.getText().trim();
        String duracao = txtDuracao.getText().trim();


        if (!DialogUtil.SaudeValidator.validarPaciente(nome, idade, sintoma, duracao)) {
            return false;
        }


        if (!DialogUtil.SaudeValidator.ApenasLetras(nome)) {
            DialogUtil.mostrarErro("O campo Nome deve conter apenas letras e espaços.");
            return false;
        }

        if (!DialogUtil.SaudeValidator.ApenasLetras(sintoma)) {
            DialogUtil.mostrarErro("O campo Sintoma deve conter apenas letras e espaços.");
            return false;
        }

        if (!DialogUtil.SaudeValidator.NumeroValido(idade) || !DialogUtil.SaudeValidator.NumeroValido(duracao)) {
            DialogUtil.mostrarErro("Campos Idade e Duração devem conter apenas números inteiros.");
            return false;
        }

        return true;
    }
}