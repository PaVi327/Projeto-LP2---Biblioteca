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

    public Emprestimo(int id, int idLivro, int idUsuario, LocalDate dataEmprestimo, boolean ativo) {
        this.id = id;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.ativo = ativo;
    }
    
    public void finalizar() {
        this.ativo = false;
    }

    public int getId() { 
        return id; 
    }
    public int getIdLivro() { 
        return idLivro; 
    }
    public int getIdUsuario() { 
        return idUsuario; 
    }
    public boolean isAtivo() { 
        return ativo; 
    }
    public LocalDate getDataEmprestimo() { 
        return dataEmprestimo; 
    }

    public String toCsvString() {
        return this.id + "," + this.idLivro + "," + this.idUsuario + "," + this.dataEmprestimo.toString() + "," + this.ativo;
    }
}
