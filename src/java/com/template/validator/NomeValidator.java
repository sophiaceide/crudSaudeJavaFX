package com.template.validator;

import java.util.regex.Pattern;

public class NomeValidator implements Validador<String>{

    private static final String NOME_REGEX = "^[a-zA-ZáéíóúàèìòùâêîôûãõçÇÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕ\\s]+$";
    private final Pattern pattern = Pattern.compile(NOME_REGEX);
    private final String valor; // Armazena o texto (nome ou sintoma) a ser validado
    private final String nomeCampo;

    public NomeValidator(String valor, String nomeCampo) {
        this.valor = valor;
        this.nomeCampo = nomeCampo;
    }

    @Override
    public boolean validar(String valor) {
        return this.valor != null && pattern.matcher(this.valor).matches();
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + " deve conter apenas letras";
    }

    @Override
    public String getValor() {
        return valor;
    }
}
