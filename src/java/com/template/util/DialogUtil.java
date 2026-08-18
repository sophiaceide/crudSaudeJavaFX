package com.template.util;

import javafx.scene.control.Alert;

public class DialogUtil {
//static pq nn tem troca de informação dinamica entre os atributos
    public static void mostrarErro(String mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mostrarInfo(String mensagem){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void showWarning(String mensagem){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static class SaudeValidator {

        //public pq o MainController precisa ter acesso a esses métodos
        public static boolean validarPaciente(String nome, String idade, String sintoma, String diasDuracao) {
            if (nome.isEmpty() || idade.isEmpty() || sintoma.isEmpty() || diasDuracao.isEmpty()) {
                showWarning("Preencha todos os campos antes de prosseguir");
                return false;
            }
            return true;
        }


        //constante de expressao
        private static final String REGEX_LETRAS =
                "^[a-zA-ZáéíóúàèìòùâêîôûãõçÇÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕ\\s]+$";

        public static boolean ApenasLetras(String texto) {
            if (texto == null) return false;
            return texto.trim().matches(REGEX_LETRAS);
        }

        public static boolean NumeroValido(String texto) {
            if (texto == null) return false;
            try {
                Integer.parseInt(texto.trim());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
