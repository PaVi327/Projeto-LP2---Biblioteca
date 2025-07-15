import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositorioCSV implements IRepositorio<Funcionario> {
    private static final String FILE_PATH = "data/Funcionarios.csv";

    @Override
    public void salvar(Funcionario funcionario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(funcionario.toCsvString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar funcionário: " + e.getMessage());
        }
    }

    @Override
    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(",");
                funcionarios.add(new Funcionario(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], Double.parseDouble(dados[4])));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler funcionários: " + e.getMessage());
        }
        return funcionarios;
    }
    
    @Override
    public Funcionario buscarPorId(int id) {
        for (Funcionario f : listarTodos()) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    @Override
    public void removerPorId(int id) {
        List<Funcionario> todos = listarTodos();
        todos.removeIf(item -> item.getId() == id);
        reescreverArquivo(todos);
    }
    
    @Override
    public void atualizar(Funcionario funcionarioAtualizado) {
        List<Funcionario> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == funcionarioAtualizado.getId()) {
                todos.set(i, funcionarioAtualizado);
                break;
            }
        }
        reescreverArquivo(todos);
    }

    private void reescreverArquivo(List<Funcionario> funcionarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            bw.write("id,nome,cpf,cargo,salario");
            bw.newLine();
            for (Funcionario f : funcionarios) {
                bw.write(f.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de funcionários: " + e.getMessage());
        }
    }
}
