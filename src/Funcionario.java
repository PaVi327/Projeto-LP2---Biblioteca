public class Funcionario extends Pessoa {
    private String cargo;
    private double salario;

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
    public void setCargo(String cargo) { 
        this.cargo = cargo;
    }
    public void setSalario(double salario) { 
        this.salario = salario;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Funcionário: " + getNome() + " (ID: " + getId() + ", Cargo: " + this.cargo + ")");
    }

    public String toCsvString() {
        return getId() + "," + getNome() + "," + getCpf() + "," + this.cargo + "," + this.salario;
    }
}
