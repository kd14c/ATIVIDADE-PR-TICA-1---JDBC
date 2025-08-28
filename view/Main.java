package br.edu.fatec.pg.jdbc.view;

import br.edu.fatec.pg.jdbc.controller.TarefaController;
import br.edu.fatec.pg.jdbc.model.Tarefa;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TarefaController tc = new TarefaController();
        int opcao;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Inserir Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Tarefa");
            System.out.println("4. Excluir Tarefa");
            System.out.println("5. Filtrar por Categoria");
            System.out.println("6. Filtrar por Status");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome da tarefa: ");
                    String nome = sc.nextLine();
                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();
                    System.out.print("Status (true=Concluída | false=Pendente): ");
                    boolean status = sc.nextBoolean();
                    sc.nextLine();
                    System.out.println(tc.inserirTarefa(nome, cat, status));
                    break;
                case 2:
                    List<Tarefa> tarefas = tc.selecionarTarefas();
                    tarefas.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Nome da tarefa a atualizar: ");
                    String antigo = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novo = sc.nextLine();
                    System.out.print("Nova categoria: ");
                    String novaCat = sc.nextLine();
                    System.out.print("Novo status (true/false): ");
                    boolean novoStatus = sc.nextBoolean();
                    sc.nextLine();
                    System.out.println(tc.atualizarTarefa(antigo, novo, novaCat, novoStatus));
                    break;
                case 4:
                    System.out.print("Nome da tarefa a excluir: ");
                    String excluir = sc.nextLine();
                    System.out.println(tc.excluirTarefa(excluir));
                    break;
                case 5:
                    System.out.print("Categoria para filtrar: ");
                    String categoriaFiltro = sc.nextLine();
                    List<Tarefa> porCategoria = tc.filtrarPorCategoria(categoriaFiltro);
                    porCategoria.forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Status para filtrar (true/false): ");
                    boolean statusFiltro = sc.nextBoolean();
                    sc.nextLine();
                    List<Tarefa> porStatus = tc.filtrarPorStatus(statusFiltro);
                    porStatus.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
