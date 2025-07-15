# Sistema de Gerenciamento de Biblioteca

### Discentes
* Paulo Vitor Lopes dos Santos
* Raul Ferreira Almeida
* Viviane Estefani da Silva Santos Lopes

## Introdução
O projeto consiste em um gerenciador de atividades de uma biblioteca, que permite gerir e consultar informações sobre livros, empréstimos de livros, usuários e funcionários. Os dados sobre essas entidades são armazenados em arquivos *`.csv`*.


## Implementação dos requisitos do projeto


### 1. Mínimo 8 Classes
O projeto foi estruturado com diversas classes para garantir a separação de responsabilidades e a aplicação dos conceitos de POO. As principais são:
* **Modelos:** 
    - `Pessoa.java` (abstrata)
    - `Usuario.java`
    - `Funcionario.java`
    - `Livro.java`
    - `Autor.java`
    - `Editora.java`
    - `Emprestimo.java`
* **Persistência de dados:** 
    - `IRepositorio.java` (interface)
    - `LivroRepositorioCSV.java`
    - `UsuarioRepositorioCSV.java`
    - `FuncionarioRepositorioCSV.java`
    - `AutorRepositorioCSV.java`
    - `EmprestimoRepositorioCSV.java`
* **Regras de negócio:** 
    - `GerenciadorDeEmprestimos.java`
* **Exceções:** 
    - `LivroIndisponivelException.java`
    - `UsuarioJaPossuiEmprestimoException.java`
* **Aplicação principal:** 
    - `Main.java`

### 2. Relacionamento
Os três tipos de relacionamentos foram modelados no sistema:
* **1:1 (Um-para-Um):** 
    - Um `Emprestimo` ativo possui uma relação 1:1 com um `Livro` que se encontra temporariamente indisponível. A transação de empréstimo está unicamente ligada àquele livro específico até sua devolução.
* **1:n (Um-para-Muitos):** 
    - A relação entre `Autor` e `Livro` é um-para-muitos. Um mesmo autor pode ter escrito vários livros. Este relacionamento é modelado no arquivo `Livros.csv` através da coluna `id_autor`, que referencia o ID do autor correspondente. O mesmo se aplica à `Editora`.
* **n:m (Muitos-para-Muitos):** 
    - A relação entre `Usuario` e `Livro` é muitos-para-muitos, resolvida pela entidade associativa `Emprestimo`. Um usuário pode realizar múltiplos empréstimos ao longo do tempo, e um livro pode ser emprestado a múltiplos usuários em diferentes momentos.

### 3. Encapsulamento
O princípio do encapsulamento é aplicado em todas as classes de modelo. Atributos são declarados como `private` e o acesso é feito por `getters` e `setters`, como pode ser observado na classe `Livro.java` com os atributos `titulo` e `disponivel`.

### 4. Herança
A herança foi utilizada para criar uma hierarquia de classes e para o reuso de código. A classe `Pessoa.java` foi declarada como `abstract` e serve como superclasse para `Usuario.java` e `Funcionario.java`, que herdam seus atributos e métodos.

### 5. Polimorfismo
O polimorfismo é aplicado através do `@Override`. O método abstrato `exibirDetalhes()` na classe `Pessoa` é implementado de formas diferentes nas subclasses `Usuario` e `Funcionario`. Uma coleção de `Pessoa` pode conter objetos de ambos os tipos, e `pessoa.exibirDetalhes()` invoca a implementação correta na execução, conforme o tipo real do objeto.

### 6. Interfaces ou Classes Abstratas
O projeto foi implementado utilizando os dois conceitos
* **Classe Abstrata:**
    - A classe `Pessoa.java` é `abstract` e serve como um modelo base que não pode ser instanciado diretamente.
* **Interface:**
    - A `IRepositorio.java` define um contrato para as classes de persistência de dados. Essas classes implementam essa interface, o que garante que dos métodos de manipulação de dados sejam padronizados.

### 7. CRUD e Tratamento de Exceções

* **CRUD:** O sistema implementa um menu no programa principal que permite ao usuário interagir com as quatro operações CRUD para essas 5 entidades definidas:
    1.  `Livro`
    2.  `Usuario`
    3.  `Funcionario`
    4.  `Autor`
    5.  `Emprestimo`

* **Tratamento de Exceções:**
    - A classe `GerenciadorDeEmprestimos.java` lança as exceções `LivroIndisponivelException` e `UsuarioJaPossuiEmprestimoException` quando for o caso.
    - Na classe `Main.java`, a chamada ao método `realizarEmprestimo()` é envolvida por um bloco `try-catch` para tratar esses erros de uma forma controlada.

