import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositorioCSV implements IRepositorio<Livro> {
    private static final String FILE_PATH = "data/Livros.csv";

    @Override
    public void salvar(Livro livro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(livro.toCsvString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar livro: " + e.getMessage());
        }
    }
    
    @Override
    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(",");
                Livro livro = new Livro(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3]));
                livro.setDisponivel(Boolean.parseBoolean(dados[4]));
                livros.add(livro);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler livros: " + e.getMessage());
        }
        return livros;
    }

    @Override
    public Livro buscarPorId(int id) {
        for (Livro livro : listarTodos()) {
            if (livro.getId() == id) return livro;
        }
        return null;
    }
    
    @Override
    public void removerPorId(int id) {
        List<Livro> todos = listarTodos();
        todos.removeIf(livro -> livro.getId() == id);
        reescreverArquivo(todos);
    }

    @Override
    public void atualizar(Livro livroAtualizado) {
        List<Livro> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == livroAtualizado.getId()) {
                todos.set(i, livroAtualizado);
                break;
            }
        }
        reescreverArquivo(todos);
    }

    private void reescreverArquivo(List<Livro> livros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            bw.write("id,titulo,id_autor,id_editora,disponivel");
            bw.newLine();
            for (Livro livro : livros) {
                bw.write(livro.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de livros: " + e.getMessage());
        }
    }
}