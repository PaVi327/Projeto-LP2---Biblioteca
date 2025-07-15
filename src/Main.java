import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LivroRepositorioCSV livroRepo = new LivroRepositorioCSV();
    private static final UsuarioRepositorioCSV usuarioRepo = new UsuarioRepositorioCSV();
    private static final FuncionarioRepositorioCSV funcionarioRepo = new FuncionarioRepositorioCSV();
    private static final AutorRepositorioCSV autorRepo = new AutorRepositorioCSV();
    private static final EmprestimoRepositorioCSV emprestimoRepo = new EmprestimoRepositorioCSV();
    private static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> menuGerenciarLivros();
                case 2 -> menuGerenciarUsuarios();
                case 3 -> menuGerenciarFuncionarios();
                case 4 -> menuGerenciarAutores();
                case 5 -> menuGerenciarEmprestimos();
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gerenciar Livros");
        System.out.println("2. Gerenciar Usuários");
        System.out.println("3. Gerenciar Funcionários");
        System.out.println("4. Gerenciar Autores");
        System.out.println("5. Gerenciar Empréstimos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private static void menuGerenciarLivros() {
        System.out.println("\n--- Gerenciar Livros ---");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Listar todos os Livros");
        System.out.println("3. Atualizar Livro");
        System.out.println("4. Remover Livro");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> adicionarLivro();
            case 2 -> listarLivros();
            case 3 -> atualizarLivro();
            case 4 -> removerLivro();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void adicionarLivro() {
        System.out.println("\n--- Adicionar Novo Livro ---");
        System.out.print("Digite o ID do livro: ");
        int id = lerOpcao();
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o ID do autor: ");
        int idAutor = lerOpcao();
        System.out.print("Digite o ID da editora: ");
        int idEditora = lerOpcao();
        
        Livro novoLivro = new Livro(id, titulo, idAutor, idEditora);
        livroRepo.salvar(novoLivro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void listarLivros() {
        System.out.println("\n--- Lista de Livros ---");
        List<Livro> livros = livroRepo.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (Livro livro : livros) {
            System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo() + " | Disponível: " + livro.isDisponivel());
        }
    }

    private static void atualizarLivro() {
        System.out.println("\n--- Atualizar Livro ---");
        System.out.print("Digite o ID do livro que deseja atualizar: ");
        int id = lerOpcao();
        Livro livro = livroRepo.buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.print("Digite o novo título (ou Enter para manter '"+ livro.getTitulo() +"'): ");
        String novoTitulo = scanner.nextLine();
        if (!novoTitulo.trim().isEmpty()) {
            livro.setTitulo(novoTitulo);
        }
        livroRepo.atualizar(livro);
        System.out.println("Livro atualizado com sucesso!");
    }

    private static void removerLivro() {
        System.out.println("\n--- Remover Livro ---");
        System.out.print("Digite o ID do livro que deseja remover: ");
        int id = lerOpcao();
        livroRepo.removerPorId(id);
        System.out.println("Livro removido (se existia).");
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private static void menuGerenciarUsuarios() {
        System.out.println("\n--- Gerenciar Usuários ---");
        System.out.println("1. Adicionar Usuário");
        System.out.println("2. Listar todos os Usuários");
        System.out.println("3. Remover Usuário");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> adicionarUsuario();
            case 2 -> listarUsuarios();
            case 3 -> removerUsuario();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void adicionarUsuario() {
        System.out.println("\n--- Adicionar Novo Usuário ---");
        System.out.print("Digite o ID do usuário: ");
        int id = lerOpcao();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o código de membro: ");
        String codigo = scanner.nextLine();
        Usuario novoUsuario = new Usuario(id, nome, cpf, codigo);
        usuarioRepo.salvar(novoUsuario);
        System.out.println("Usuário adicionado com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("\n--- Lista de Usuários ---");
        List<Usuario> usuarios = usuarioRepo.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario u : usuarios) {
            u.exibirDetalhes();
        }
    }
    
    private static void removerUsuario() {
        System.out.println("\n--- Remover Usuário ---");
        System.out.print("Digite o ID do usuário que deseja remover: ");
        int id = lerOpcao();
        usuarioRepo.removerPorId(id);
        System.out.println("Usuário removido (se existia).");
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private static void menuGerenciarFuncionarios() {
        System.out.println("\n--- Gerenciar Funcionários ---");
        System.out.println("1. Adicionar Funcionário");
        System.out.println("2. Listar todos os Funcionários");
        System.out.println("3. Atualizar Funcionário");
        System.out.println("4. Remover Funcionário");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> adicionarFuncionario();
            case 2 -> listarFuncionarios();
            case 3 -> atualizarFuncionario();
            case 4 -> removerFuncionario();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }
    
    private static void adicionarFuncionario() {
        System.out.println("\n--- Adicionar Novo Funcionário ---");
        System.out.print("Digite o ID do funcionário: ");
        int id = lerOpcao();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Digite o salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        Funcionario novoFuncionario = new Funcionario(id, nome, cpf, cargo, salario);
        funcionarioRepo.salvar(novoFuncionario);
        System.out.println("Funcionário adicionado com sucesso!");
    }

    private static void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        List<Funcionario> funcionarios = funcionarioRepo.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            f.exibirDetalhes();
        }
    }

    private static void atualizarFuncionario() {
        System.out.println("\n--- Atualizar Funcionário ---");
        System.out.print("Digite o ID do funcionário para atualizar: ");
        int id = lerOpcao();
        Funcionario f = funcionarioRepo.buscarPorId(id);
        if (f == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        System.out.print("Digite o novo cargo (ou Enter para manter '" + f.getCargo() + "'): ");
        String novoCargo = scanner.nextLine();
        if(!novoCargo.trim().isEmpty()) f.setCargo(novoCargo);

        System.out.print("Digite o novo salário (ou 0 para manter " + f.getSalario() + "): ");
        double novoSalario = scanner.nextDouble();
        scanner.nextLine();
        if(novoSalario > 0) f.setSalario(novoSalario);

        funcionarioRepo.atualizar(f);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    private static void removerFuncionario() {
        System.out.println("\n--- Remover Funcionário ---");
        System.out.print("Digite o ID do funcionário que deseja remover: ");
        int id = lerOpcao();
        funcionarioRepo.removerPorId(id);
        System.out.println("Funcionário removido (se existia).");
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private static void menuGerenciarAutores() {
        System.out.println("\n--- Gerenciar Autores ---");
        System.out.println("1. Adicionar Autor");
        System.out.println("2. Listar todos os Autores");
        System.out.println("3. Atualizar Autor");
        System.out.println("4. Remover Autor");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> adicionarAutor();
            case 2 -> listarAutores();
            case 3 -> atualizarAutor();
            case 4 -> removerAutor();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void adicionarAutor() {
        System.out.println("\n--- Adicionar Novo Autor ---");
        System.out.print("Digite o ID do novo autor: ");
        int id = lerOpcao();
        System.out.print("Digite o nome do autor: ");
        String nome = scanner.nextLine();
        Autor novoAutor = new Autor(id, nome);
        autorRepo.salvar(novoAutor);
        System.out.println("Autor '" + nome + "' adicionado com sucesso!");
    }

    private static void listarAutores() {
        System.out.println("\n--- Lista de Autores ---");
        List<Autor> autores = autorRepo.listarTodos();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        for (Autor autor : autores) {
            System.out.println("ID: " + autor.getId() + " | Nome: " + autor.getNome());
        }
    }

    private static void atualizarAutor() {
        System.out.println("\n--- Atualizar Autor ---");
        System.out.print("Digite o ID do autor que deseja atualizar: ");
        int id = lerOpcao();
        Autor autor = autorRepo.buscarPorId(id);
        if (autor == null) {
            System.out.println("Autor não encontrado.");
            return;
        }
        System.out.print("Digite o novo nome para '" + autor.getNome() + "' (ou Enter para manter): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            autor.setNome(novoNome);
        }
        autorRepo.atualizar(autor);
        System.out.println("Autor atualizado com sucesso!");
    }

    private static void removerAutor() {
        System.out.println("\n--- Remover Autor ---");
        System.out.print("Digite o ID do autor que deseja remover: ");
        int id = lerOpcao();
        autorRepo.removerPorId(id);
        System.out.println("Autor removido (se existia).");
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private static void menuGerenciarEmprestimos() {
        System.out.println("\n--- Gerenciar Empréstimos ---");
        System.out.println("1. Realizar Empréstimo");
        System.out.println("2. Finalizar Empréstimo");
        System.out.println("3. Listar todos os Empréstimos");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> realizarEmprestimo();
            case 2 -> finalizarEmprestimo();
            case 3 -> listarEmprestimos();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void realizarEmprestimo() {
        System.out.println("\n--- Realizar Empréstimo ---");
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = lerOpcao();
        Usuario usuario = usuarioRepo.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        System.out.print("Digite o ID do livro: ");
        int idLivro = lerOpcao();
        Livro livro = livroRepo.buscarPorId(idLivro);
        if (livro == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }
        
        GerenciadorDeEmprestimos gerenciador = new GerenciadorDeEmprestimos(livroRepo, emprestimoRepo);
        try {
            gerenciador.realizarEmprestimo(usuario, livro);
        } catch (LivroIndisponivelException | UsuarioJaPossuiEmprestimoException e) {
            System.err.println("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    private static void finalizarEmprestimo() {
        System.out.println("\n--- Finalizar Empréstimo ---");
        System.out.print("Digite o ID do empréstimo a ser finalizado: ");
        int idEmprestimo = lerOpcao();
        
        Emprestimo emprestimo = emprestimoRepo.buscarPorId(idEmprestimo);
        if (emprestimo == null || !emprestimo.isAtivo()) {
            System.out.println("Erro: Empréstimo não encontrado ou já finalizado.");
            return;
        }

        emprestimo.finalizar();
        emprestimoRepo.atualizar(emprestimo);

        Livro livro = livroRepo.buscarPorId(emprestimo.getIdLivro());
        if (livro != null) {
            livro.setDisponivel(true);
            livroRepo.atualizar(livro);
        }
        System.out.println("Empréstimo finalizado e livro devolvido ao acervo.");
    }

    private static void listarEmprestimos() {
        System.out.println("\n--- Histórico de Empréstimos ---");
        List<Emprestimo> emprestimos = emprestimoRepo.listarTodos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        for (Emprestimo e : emprestimos) {
            System.out.println("ID: " + e.getId() + " | LivroID: " + e.getIdLivro() + " | UsuarioID: " + e.getIdUsuario() + " | Data: " + e.getDataEmprestimo() + " | Ativo: " + e.isAtivo());
        }
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}