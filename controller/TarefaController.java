package br.edu.fatec.pg.jdbc.controller;

import br.edu.fatec.pg.jdbc.model.Banco;
import br.edu.fatec.pg.jdbc.model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaController {

    public String inserirTarefa(String nome_tarefa, String categoria, boolean status) {
        String query = "INSERT INTO tb_tarefa(nm_tarefa, ds_categoria, status) VALUES(?,?,?)";

        try (Connection conexao = Banco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nome_tarefa);
            stmt.setString(2, categoria);
            stmt.setBoolean(3, status);
            stmt.executeUpdate();
            return "Tarefa gravada com sucesso!";
        } catch (Exception e) {
            return "Erro ao inserir tarefa: " + e.getMessage();
        }
    }

    public List<Tarefa> selecionarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String query = "SELECT * FROM tb_tarefa";

        try (Connection conexao = Banco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tarefas.add(new Tarefa(
                        rs.getString("nm_tarefa"),
                        rs.getString("ds_categoria"),
                        rs.getBoolean("status")));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return tarefas;
    }

    public String atualizarTarefa(String nomeAntigo, String nomeNovo, String categoriaNova, boolean statusNovo) {
        String query = "UPDATE tb_tarefa SET nm_tarefa=?, ds_categoria=?, status=? WHERE nm_tarefa=?";
        try (Connection conexao = Banco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nomeNovo);
            stmt.setString(2, categoriaNova);
            stmt.setBoolean(3, statusNovo);
            stmt.setString(4, nomeAntigo);
            int linhas = stmt.executeUpdate();
            return linhas > 0 ? "Tarefa atualizada!" : "Tarefa não encontrada.";
        } catch (Exception e) {
            return "Erro ao atualizar: " + e.getMessage();
        }
    }

    public String excluirTarefa(String nomeTarefa) {
        String query = "DELETE FROM tb_tarefa WHERE nm_tarefa=?";
        try (Connection conexao = Banco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, nomeTarefa);
            int linhas = stmt.executeUpdate();
            return linhas > 0 ? "Tarefa excluída!" : "Tarefa não encontrada.";
        } catch (Exception e) {
            return "Erro ao excluir: " + e.getMessage();
        }
    }

    public List<Tarefa> filtrarPorCategoria(String categoria) {
        List<Tarefa> tarefas = new ArrayList<>();
        String query = "SELECT * FROM tb_tarefa WHERE ds_categoria = ?";
        try (Connection conexao = Banco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, categoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tarefas.add(new Tarefa(
                        rs.getString("nm_tarefa"),
                        rs.getString("ds_categoria"),
                        rs.getBoolean("status")));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return tarefas;
    }

    public List<Tarefa> filtrarPorStatus(boolean status) {
        List<Tarefa> tarefas = new ArrayList<>();
        String query = "SELECT * FROM tb_tarefa WHERE status = ?";
        try (Connection conexao = Banco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setBoolean(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tarefas.add(new Tarefa(
                        rs.getString("nm_tarefa"),
                        rs.getString("ds_categoria"),
                        rs.getBoolean("status")));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return tarefas;
    }
}
