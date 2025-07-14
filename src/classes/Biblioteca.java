import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private final String nome;
    private final List<Livro> acervo;
    private final List<Usuario> membros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.acervo = new ArrayList<>();
        this.membros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        this.acervo.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        this.membros.add(usuario);
    }
    
    public void listarAcervo() {
        System.out.println("\n=== Acervo da Biblioteca: " + this.nome + " ===");
        for(Livro livro : acervo) {
            System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo() + " | Disponível: " + livro.isDisponivel());
        }
        System.out.println("==========================================");
    }
}