package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioController {

    @FXML
    private Button btnIniciar;

    @FXML
    private void BotaoIniciar(ActionEvent event) {
        try {
            // CORREÇÃO: Altere de "TelaInicial.fxml" para "main.fxml"
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

            // Pega a janela (Stage) atual através do clique do botão
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Define a nova tela (main) na janela e exibe
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela principal: Verifique se o arquivo main.fxml está no local correto.");
        }
    }
}