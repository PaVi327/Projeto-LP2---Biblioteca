import java.util.List;

public class GerenciadorDeEmprestimos {

    private final IRepositorio<Livro> livroRepositorio;
    private final IRepositorio<Emprestimo> emprestimoRepositorio;
    private final List<Emprestimo> emprestimosAtivos;

    public GerenciadorDeEmprestimos(IRepositorio<Livro> livroRepo, IRepositorio<Emprestimo> emprestimoRepo) {
        this.livroRepositorio = livroRepo;
        this.emprestimoRepositorio = emprestimoRepo;
        this.emprestimosAtivos = emprestimoRepo.listarTodos();
    }

    public void realizarEmprestimo(Usuario usuario, Livro livro)
            throws LivroIndisponivelException, UsuarioJaPossuiEmprestimoException {
        
        if (!livro.isDisponivel()) {
            throw new LivroIndisponivelException("O livro '" + livro.getTitulo() + "' não está disponível.");
        }

        for (Emprestimo emp : this.emprestimosAtivos) {
            if (emp.getIdUsuario() == usuario.getId() && emp.isAtivo()) {
                throw new UsuarioJaPossuiEmprestimoException("O usuário " + usuario.getNome() + " já possui um empréstimo ativo.");
            }
        }
        
        System.out.println("INFO: Empréstimo autorizado para " + usuario.getNome() + "!");
        
        livro.setDisponivel(false);
        livroRepositorio.atualizar(livro);

        int novoId = !emprestimosAtivos.isEmpty() ? emprestimosAtivos.get(emprestimosAtivos.size() - 1).getId() + 1 : 1;
        Emprestimo novoEmprestimo = new Emprestimo(novoId, livro.getId(), usuario.getId());

        emprestimoRepositorio.salvar(novoEmprestimo);
        this.emprestimosAtivos.add(novoEmprestimo);
        
        System.out.println("Empréstimo salvo com sucesso no arquivo.");
    }
}