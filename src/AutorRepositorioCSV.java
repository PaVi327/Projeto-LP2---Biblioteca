import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepositorioCSV implements IRepositorio<Autor> {
    private static final String FILE_PATH = "data/Autores.csv";

    @Override
    public void salvar(Autor autor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(autor.toCsvString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar autor: " + e.getMessage());
        }
    }

    @Override
    public List<Autor> listarTodos() {
        List<Autor> autores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(",");
                autores.add(new Autor(Integer.parseInt(dados[0]), dados[1]));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler autores: " + e.getMessage());
        }
        return autores;
    }

    @Override
    public Autor buscarPorId(int id) {
        for (Autor autor : listarTodos()) {
            if (autor.getId() == id) return autor;
        }
        return null;
    }

    @Override
    public void removerPorId(int id) {
        List<Autor> todos = listarTodos();
        todos.removeIf(item -> item.getId() == id);
        reescreverArquivo(todos);
    }

    @Override
    public void atualizar(Autor autorAtualizado) {
        List<Autor> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == autorAtualizado.getId()) {
                todos.set(i, autorAtualizado);
                break;
            }
        }
        reescreverArquivo(todos);
    }

    private void reescreverArquivo(List<Autor> autores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            bw.write("id,nome");
            bw.newLine();
            for (Autor autor : autores) {
                bw.write(autor.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de autores: " + e.getMessage());
        }
    }
}