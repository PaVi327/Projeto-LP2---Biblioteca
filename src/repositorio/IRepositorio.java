import java.util.List;

public interface IRepositorio<T> {
    void salvar(T item);
    void removerPorId(int id);
    T buscarPorId(int id);
    List<T> listarTodos();
}