# 🏛️ Documentação de Arquitetura – Sistema de Venda de Ingressos Teatro ABC

## 📌 Visão Geral da Arquitetura

O sistema de venda de ingressos do Teatro ABC é uma **aplicação desktop desenvolvida em Java**, que permite a gestão e comercialização de ingressos para espetáculos teatrais.

### Funcionalidades Principais:
- Cadastro de usuários;
- Venda de ingressos;
- Consulta de espetáculos;
- Geração de relatórios.

### Requisitos Não Funcionais

#### ✅ Qualidade e Manutenibilidade
- Uso do padrão DAO com interfaces bem definidas (`IUsuarioDAO`, `IPecaDAO`, etc.);
- Separação clara de responsabilidades em camadas;
- Implementação de interfaces para abstração;
- Estrutura de pacotes organizada e coesa;
- Classes de modelo bem definidas.

#### 📈 Escalabilidade
- Arquitetura em camadas permite fácil adição de novas funcionalidades;
- Interfaces possibilitam múltiplas implementações;
- Estrutura modular favorece a expansão do sistema.

#### 🔐 Segurança
- Sistema de autenticação implementado;
- Controle de acesso via login;
- Separação de responsabilidades entre camadas.

---

## 🧱 Estilo Arquitetural Utilizado

**Arquitetura em camadas**, composta pelos seguintes componentes:

### 🎯 Justificativas da Escolha:
- Promove separação clara de responsabilidades;
- Facilita manutenção e evolução do sistema;
- Permite desenvolvimento e testes independentes por camada;
- Reduz o acoplamento entre componentes;
- Estimula a reutilização de código.

---

## 📦 Estrutura de Pacotes

```plaintext
com.example
├── Main.java               # Classe principal
├── model/                 # Camada de Modelo (entidades e regras de negócio)
├── dao/                   # Camada de Acesso a Dados (persistência)
├── gui/                   # Camada de Apresentação (interface com o usuário)
├── service/               # Camada de Serviço (lógica de negócios)
└── util/                  # Utilitários (funções auxiliares e suporte)

### 📂 Camada de Modelo (`model/`)
- **Responsabilidade:** Representar entidades e regras de negócio.
- **Justificativa:**
  - Encapsula dados e comportamentos;
  - Centraliza regras de negócio;
  - Facilita reutilização de código.

### 🗃️ Camada de Acesso a Dados (`dao/`)
- **Responsabilidade:** Persistência e recuperação de dados.
- **Justificativa:**
  - Separa lógica de acesso de dados da lógica de negócio;
  - Facilita manutenção e troca de fonte de dados;
  - Promove desacoplamento com o modelo.

### 🖥️ Camada de Apresentação (`gui/`)
- **Responsabilidade:** Interface gráfica com o usuário.
- **Justificativa:**
  - Facilita manutenção da UI;
  - Separa interface da lógica de negócio;
  - Utiliza **FlatLaf** para interface moderna.

### ⚙️ Camada de Serviço (`service/`)
- **Responsabilidade:** Regras de negócio e orquestração.
- **Justificativa:**
  - Coordena operações entre camadas;
  - Gerencia transações e regras complexas.

### 🧰 Camada de Utilitários (`util/`)
- **Responsabilidade:** Funções auxiliares reutilizáveis.
- **Justificativa:**
  - Centraliza lógica comum;
  - Evita duplicação de código.

---

## 🧪 Tecnologias e Bibliotecas

| Tecnologia     | Função                                                  |
|----------------|----------------------------------------------------------|
| **Java 21**    | Linguagem principal com recursos modernos                |
| **MySQL**      | Banco de dados relacional robusto                        |
| **Maven**      | Gerenciador de dependências e build                      |
| **FlatLaf**    | Look and feel moderno para Swing                         |
| **Swing/AWT**  | Componentes gráficos da interface                        |
| **JDBC**       | Comunicação com banco de dados                           |

### 🧾 Justificativas:
- **Facilidade de uso** e aprendizagem;
- **Alto desempenho e estabilidade**;
- **Compatibilidade com o currículo acadêmico**.

---

## 🔄 Fluxo de Dados e Controle

### 🔐 Autenticação
- Usuário acessa a tela de Login;
- Credenciais são validadas via `UsuarioService`;
- Se válidas, acesso é concedido à tela principal.

### 👤 Cadastro de Usuário
- Dados inseridos na interface `CadastroUsuario`;
- Validação com `UsuarioService`;
- Persistência via `UsuarioDAO`.

### 🎫 Compra de Ingressos
- Seleção de espetáculo na tela `Espetáculos`;
- `CompraIngresso` gerencia sessão e assentos;
- `VendaIngressoDAO` registra a compra no banco.

### 🔍 Consulta de Ingressos
- Busca por CPF via `ConsultaIngressos`;
- Ingressos do usuário são listados.

### 🛢️ Persistência de Dados
- Toda a persistência é gerenciada pela camada `DAO`;
- Conexão com banco via `ConexaoBanco`;
- Padrão DAO garante abstração e desacoplamento.

---

