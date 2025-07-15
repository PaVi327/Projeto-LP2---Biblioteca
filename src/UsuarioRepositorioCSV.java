import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioCSV implements IRepositorio<Usuario> {
    private static final String FILE_PATH = "data/Usuarios.csv";

    @Override
    public void salvar(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(usuario.toCsvString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(",");
                usuarios.add(new Usuario(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3]));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public Usuario buscarPorId(int id) {
        for (Usuario usuario : listarTodos()) {
            if (usuario.getId() == id) return usuario;
        }
        return null;
    }

    @Override
    public void removerPorId(int id) {
        List<Usuario> todos = listarTodos();
        todos.removeIf(item -> item.getId() == id);
        reescreverArquivo(todos);
    }

    @Override
    public void atualizar(Usuario usuarioAtualizado) {
        List<Usuario> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == usuarioAtualizado.getId()) {
                todos.set(i, usuarioAtualizado);
                break;
            }
        }
        reescreverArquivo(todos);
    }

    private void reescreverArquivo(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            bw.write("id,nome,cpf,codigo_membro");
            bw.newLine();
            for (Usuario usuario : usuarios) {
                bw.write(usuario.toCsvString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de usuários: " + e.getMessage());
        }
    }
}