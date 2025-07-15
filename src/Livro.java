public class Livro {
    private final int id;
    private String titulo;
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

    public int getId() { 
        return id; 
    }
    public String getTitulo() { 
        return titulo; 
    }
    public int getIdAutor() { 
        return idAutor; 
    }
    public int getIdEditora() { 
        return idEditora; 
    }
    public boolean isDisponivel() { 
        return disponivel; 
    }
    public void setDisponivel(boolean disponivel) { 
        this.disponivel = disponivel; 
    }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public String toCsvString() {
        return this.id + "," + this.titulo + "," + this.idAutor + "," + this.idEditora + "," + this.disponivel;
    }
}