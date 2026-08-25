package com.template.validator;

import java.util.ArrayList;
import java.util.List;


import static com.template.util.DialogUtil.showWarning;
@SuppressWarnings("BooleanMethodIsAlwaysInverted")

public class UsuarioValidator {

    // Método principal de validação que combina todas as regras
    public static boolean validarUsuario(String nome, String idade, String sintoma, String diasDuracao) {
        // Lista de validadores que serão aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("Idade", idade));
        validadores.add(new CampoObrigatorioValidador("Sintoma", sintoma));
        validadores.add(new CampoObrigatorioValidador("Dias de Duração", diasDuracao));

        // Adicionando o validador específico
        validadores.add(new NomeValidator(nome, "Nome"));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) { // O validador agora "conhece" o valor que vai validar
                showWarning(validador.getMensagemErro()); // você usaria DialogUtil.showWarning(mensagem);
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }
}




