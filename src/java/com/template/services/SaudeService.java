package com.template.services;

import com.template.model.dao.SaudeDAO;
import com.template.model.dto.SaudeDTO;

import java.util.List;

public class SaudeService {

    private final SaudeDAO saudeDAO;

    public SaudeService() {
        this.saudeDAO = new SaudeDAO();
    }

    public void salvarPaciente(String nome, int idade, String sintoma, int duracao, boolean doencaCronica) {
        SaudeDTO dto = montarDTO(null, nome, idade, sintoma, duracao, doencaCronica);
        saudeDAO.inserirSaude(dto);
    }

    public void atualizarPaciente(int id, String nome, int idade, String sintoma, int duracao, boolean doencaCronica) {
        SaudeDTO dto = montarDTO(id, nome, idade, sintoma, duracao, doencaCronica);
        saudeDAO.atualizarSaude(dto);
    }

    public void excluirPaciente(SaudeDTO paciente) {
        saudeDAO.excluirSaude(paciente);
    }

    public List<SaudeDTO> buscarTodos() {
        return saudeDAO.listarSaude();
    }

    private SaudeDTO montarDTO(Integer id, String nome, int idade, String sintoma, int duracao, boolean doencaCronica) {
        SaudeDTO saudeDto = new SaudeDTO();
        if (id != null) {
            saudeDto.setId(id);
        }
        saudeDto.setNome(nome);
        saudeDto.setIdade(idade);
        saudeDto.setSintoma(sintoma);
        saudeDto.setDiasDuracao(duracao);
        saudeDto.setDoencasCronicas(doencaCronica ? "s" : "n");
        return saudeDto;
    }
}