package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SaudeDAO {

    private static final Logger logger = Logger.getLogger(SaudeDAO.class.getName());



    public void inserirSaude(SaudeDTO saude) {
        String sql = "INSERT INTO saude(nome, idade, sintoma, duracao_dias, doencas_cronic) VALUES(?, ?, ?, ?, ?)";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql); ) {
            ps.setString(1, saude.getNome());
            ps.setInt(2, saude.getIdade());
            ps.setString(3, saude.getSintoma());
            ps.setInt(4, saude.getDiasDuracao());
            ps.setString(5, saude.getDoencasCronicas());
            ps.execute();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir dados!", e);
        }
    }

    ArrayList<SaudeDTO> listaSaude = new ArrayList<>();

    public ArrayList<SaudeDTO> listarSaude() {

        String sql = "select * from saude";
        ArrayList<SaudeDTO> listarSaude = new ArrayList<>();


        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql); ResultSet resultado = ps.executeQuery(); ){

            while (resultado.next()) {

                SaudeDTO saude =  new SaudeDTO();
                saude.setId(resultado.getId("id"));
                saude.setNome(resultado.getNome("nome"));
                saude.setIdade(resultado.getIdade("idade"));
                saude.setSintoma(resultado.getSintoma("sintoma"));
                saude.setDiasDuracao(resultado.setDiasDuracao("duracao_dias"));
                saude.setDoencasCronicas(resultado.setDoencasCronicas("doencas_cronic"));
                listaSaude.add(usuario);

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao apresentar!", e);
        }
        return listaSaude;
    }
    public void atualizarSaude(SaudeDTO saude) {

        String sql = "UPDATE saude SET idade=?, sintoma=?, duracao_dias=?, doencas_cronic=? WHERE nome=?";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql);){
            ps.setInt(1, saude.getIdade());
            ps.setString(2, saude.getSintoma());
            ps.setInt(3, saude.getDiasDuracao());
            ps.setString(4, saude.getDoencasCronicas());
            ps.setString(5, saude.getNome());

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar!", e);
        }
    }
    public void excluirSaude(SaudeDTO saude) {
        String sql = "DELETE FROM saude WHERE nome=?";


        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql); ) {
            ps.setString(1, saude.getNome());
            ps.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar!", e);
        }

    }

}
