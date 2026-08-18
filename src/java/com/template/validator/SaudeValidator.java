package com.template.validator;

import static com.template.util.DialogUtil.showWarning;

public class SaudeValidator {

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