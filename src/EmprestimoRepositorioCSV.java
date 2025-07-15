import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepositorioCSV implements IRepositorio<Emprestimo> {
    private static final String FILE_PATH = "data/Emprestimos.csv";

    @Override
    public void salvar(Emprestimo emprestimo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(emprestimo.toCsvString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }

    @Override
    public List<Emprestimo> listarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(",");
                emprestimos.add(new Emprestimo(
                    Integer.parseInt(dados[0]),
                    Integer.parseInt(dados[1]),
                    Integer.parseInt(dados[2]),
                    LocalDate.parse(dados[3]),
                    Boolean.parseBoolean(dados[4])
                ));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }

    @Override
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : listarTodos()) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    @Override
    public void removerPorId(int id) {
        List<Emprestimo> todos = listarTodos();
        todos.removeIf(item -> item.getId() == id);
        reescreverArquivo(todos);
    }

    @Override
    public void atualizar(Emprestimo emprestimoAtualizado) {
        List<Emprestimo> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == emprestimoAtualizado.getId()) {
                todos.set(i, emprestimoAtualizado);
                break;
            }
        }
        reescreverArquivo(todos);
    }

    private void reescreverArquivo(List<Emprestimo> emprestimos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            bw.write("id,id_livro,id_usuario,data_emprestimo,ativo");
            bw.newLine();
            for (Emprestimo e : emprestimos) {
                bw.write(e.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de empréstimos: " + e.getMessage());
        }
    }
}