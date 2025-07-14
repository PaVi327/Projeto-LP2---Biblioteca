public class Livro {
    private final int id;
    private final String titulo;
    private final int idAutor;
    private final int idEditora;
    private boolean disponivel;

    public Livro(int id, String titulo, int idAutor, int idEditora) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.idEditora = idEditora;
        this.disponivel = true;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getIdAutor() { return idAutor; }
    public int getIdEditora() { return idEditora; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}