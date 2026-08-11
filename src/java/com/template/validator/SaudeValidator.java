package com.template.validator;

import static com.template.util.DialogUtil.showWarning;

public class SaudeValidator {
    public static boolean validarPaciente(String nome, String idade, String sintoma, String diasDuracao) {
        if (nome.isEmpty() || idade.isEmpty() || sintoma.isEmpty() || diasDuracao.isEmpty()) {
            showWarning("Preencha todos os campos antes de prosseguir");
            return false;
        }
        return true;
    }


    //validar com pesquisa
    public static boolean validarTermo(String termo) {
        if (termo.isEmpty()) {
            showWarning("Digite um termo de pesquisa");
            return false;
        }
        return true;
    }

}


/*public class IUServices{

}
*/