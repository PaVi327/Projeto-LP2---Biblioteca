public class Autor {
    private final int id;
    private String nome;

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { 
        return id; 
    }
    public String getNome() { 
        return nome;
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    } 
    public String toCsvString() {
        return this.id + "," + this.nome;
    }
}