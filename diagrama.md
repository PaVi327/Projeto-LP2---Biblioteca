```mermaid
classDiagram
    direction TD

    class Pessoa {
        <<Abstract>>
        -int id
        -String nome
        -String cpf
        +exibirDetalhes()*
    }

    class Usuario {
        -String codigoMembro
        +exibirDetalhes()
    }

    class Funcionario {
        -String cargo
        -double salario
        +exibirDetalhes()
    }

    class Livro {
        -int id
        -String titulo
        -int idAutor
        -int idEditora
        -boolean disponivel
        +isDisponivel() : boolean
        +setDisponivel(boolean)
    }

    class Autor {
        -int id
        -String nome
    }
    
    class Editora {
        -int id
        -String nome
    }

    class Emprestimo {
        -int id
        -int idLivro
        -int idUsuario
        -LocalDate dataEmprestimo
        -boolean ativo
        +finalizar()
    }

    class IRepositorio {
        <<Interface>>
        +salvar(T)
        +atualizar(T)
        +removerPorId(int)
        +buscarPorId(int) : T
        +listarTodos() : List~T~
    }

    class GerenciadorDeEmprestimos {
        -IRepositorio livroRepositorio
        -IRepositorio emprestimoRepositorio
        +realizarEmprestimo(Usuario, Livro)
    }

    class LivroIndisponivelException {
        <<Exception>>
    }

    class UsuarioJaPossuiEmprestimoException {
        <<Exception>>
    }

    Pessoa <|-- Usuario
    Pessoa <|-- Funcionario

    Autor "1" -- "0..*" Livro : escreve
    Editora "1" -- "0..*" Livro : publica

    Usuario "1" -- "0..*" Emprestimo : realiza
    Livro "1" -- "0..*" Emprestimo : está em

    GerenciadorDeEmprestimos ..> IRepositorio : usa
    GerenciadorDeEmprestimos ..> LivroIndisponivelException
    GerenciadorDeEmprestimos ..> UsuarioJaPossuiEmprestimoException