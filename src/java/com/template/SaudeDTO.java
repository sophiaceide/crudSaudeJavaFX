package com.template;

public class SaudeDTO {

    private int id;
    private String nome;
    private int idade;
    private String sintoma;
    private int diasDuracao;
    private String doencasCronicas;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public int getDiasDuracao() {
        return diasDuracao;
    }

    public void setDiasDuracao(int duracaoDias) {
        this.diasDuracao = duracaoDias;
    }

    public String getDoencasCronicas() {
        return doencasCronicas;
    }

    public void setDoencasCronicas(String doencasCronicas) {
        this.doencasCronicas = doencasCronicas;
    }

}
