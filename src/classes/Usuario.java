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
        System.out.println("--- Detalhes do Usuário ---");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Código de Membro: " + this.codigoMembro);
        System.out.println("---------------------------");
    }
}