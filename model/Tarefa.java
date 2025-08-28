package br.edu.fatec.pg.jdbc.model;

public class Tarefa {
    private String nmTarefa;
    private String categoria;
    private boolean status;

    public Tarefa(String nmTarefa, String categoria, boolean status) {
        this.nmTarefa = nmTarefa;
        this.categoria = categoria;
        this.status = status;
    }

    public String getNmTarefa() {
        return nmTarefa;
    }

    public void setNmTarefa(String nmTarefa) {
        this.nmTarefa = nmTarefa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "Nome='" + nmTarefa + '\'' +
                ", Categoria='" + categoria + '\'' +
                ", Status=" + (status ? "Concluída" : "Pendente") +
                '}';
    }
}
