import java.time.LocalDate;

public class Emprestimo {
    private final int id;
    private final int idLivro;
    private final int idUsuario;
    private final LocalDate dataEmprestimo;
    private boolean ativo;

    public Emprestimo(int id, int idLivro, int idUsuario) {
        this.id = id;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.dataEmprestimo = LocalDate.now();
        this.ativo = true;
    }

    public void finalizar() {
        this.ativo = false;
        System.out.println("Empréstimo ID " + this.id + " finalizado.");
    }

    // Getters
    public int getId() { return id; }
    public int getIdLivro() { return idLivro; }
    public int getIdUsuario() { return idUsuario; }
    public boolean isAtivo() { return ativo; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
}
