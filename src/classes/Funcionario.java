public class Funcionario extends Pessoa {
    private final String cargo;
    private final double salario;

    public Funcionario(int id, String nome, String cpf, String cargo, double salario) {
        super(id, nome, cpf);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }
    
    public double getSalario() {
        return salario;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Detalhes do Funcionário ---");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Cargo: " + this.cargo);
        System.out.println("-------------------------------");
    }
}
