public class Usuario extends Pessoa {
    private final String codigoMembro;

    public Usuario(int id, String nome, String cpf, String codigoMembro) {
        super(id, nome, cpf);
        this.codigoMembro = codigoMembro;
    }

    public String getCodigoMembro() {
        return codigoMembro;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Usuário: " + getNome() + " (ID: " + getId() + ", Código: " + this.codigoMembro + ")");
    }

    public String toCsvString() {
        return getId() + "," + getNome() + "," + getCpf() + "," + this.codigoMembro;
    }
}