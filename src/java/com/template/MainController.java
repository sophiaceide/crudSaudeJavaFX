package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    @FXML private TableColumn<SaudeDTO, Integer> Duracao;
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
        int id = Integer.parseInt(txtId.getText());
        int idade = Integer.parseInt(txtIdade.getText());
        String sintoma = txtSintoma.getText();
        String doenca = txtDoenca.getText();
        int diasDuracao = Integer.parseInt(txtDuracao.getText());

        SaudeDTO objsaudedto = new SaudeDTO();
        objsaudedto.setNome(nome);
        objsaudedto.setId(id);
        objsaudedto.setIdade(idade);
        objsaudedto.setSintoma(sintoma);
        objsaudedto.setDiasDuracao(diasDuracao);
        objsaudedto.setDoencasCronicas(doenca);

        SaudeDAO objsaudedao = new SaudeDAO();
        objsaudedao.inserirSaude(objsaudedto);

        carregarSaude();
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
    private void carregarSaude(){
        SaudeDAO objsaudedao = new SaudeDAO();
        ArrayList<SaudeDTO> listaSaude = objsaudedao.listarSaude();
        tblConsulta.setItems(FXCollections.observableArrayList(listaSaude));
    }


    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}