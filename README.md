# Desafio Técnico Iniflex - Gestão de Funcionários

Implementação do desafio prático de manipulação e cálculo de dados de funcionários em Java 21, estruturado com foco em desacoplamento, imutabilidade e precisão monetária.

---

## Decisões Técnicas e Arquitetura

- **Arquitetura em Camadas:** Separação entre apresentação (`Principal`), regras de negócio (`FuncionarioService`), persistência/mock de dados (`FuncionarioRepository`) e entidades (`Funcionario`, `Pessoa`).
- **Precisão Financeira:** Uso exclusivo de `BigDecimal` com arredondamento `RoundingMode.HALF_EVEN` para cálculos de salários e salários mínimos, prevenindo erros de ponto flutuante.
- **Datas e Períodos:** Utilização da `java.time` API (`LocalDate`, `Period`) para cálculos de idade e filtros de aniversário.
- **Formatação Idiomática:** Formatação monetária e decimal configurada com `DecimalFormatSymbols.getInstance(Locale.of("pt", "BR"))`.
- **Cobertura de Testes:** Suíte de testes unitários com JUnit 5 cobrindo agrupamentos, reajustes, somatórios e ordenações.
- **Automação (CI):** Pipeline via GitHub Actions configurada para executar o build e os testes a cada push ou pull request na branch `main`.

---

## Estrutura do Projeto

```text
desafio-iniflex-java/
├── .github/workflows/maven.yml       # Pipeline CI do GitHub Actions
└── iniflex-tech-challenge/
    ├── pom.xml                       # Gerenciador de dependências e build
    └── src/
        ├── main/java/br/com/iniflex/
        │   ├── model/                # Pessoa e Funcionario
        │   ├── repository/           # FuncionarioRepository (carga inicial)
        │   ├── service/              # FuncionarioService (regras e cálculos)
        │   └── Principal.java        # Orquestração e saída no console
        └── test/java/br/com/iniflex/ # FuncionarioServiceTest (testes unitários)
```

---

## Como Executar

### Pré-requisitos

- JDK 21 instalado e configurado
- Maven 3.8+ (ou utilize o Maven Wrapper incluso no projeto)

### 1. Clonar o repositório

```bash
git clone https://github.com/devjanBarbosa/desafio-iniflex-java.git
cd desafio-iniflex-java/iniflex-tech-challenge
```

### 2. Executar os testes unitários

```bash
# Usando o Maven do sistema:
mvn test

# Ou usando o Wrapper (Windows PowerShell):
.\mvnw.cmd test
```

### 3. Executar a aplicação

```bash
# Compilar e executar a classe Principal:
mvn compile exec:java -Dexec.mainClass="br.com.iniflex.Principal"

# Ou via Maven Wrapper:
.\mvnw.cmd compile exec:java -Dexec.mainClass="br.com.iniflex.Principal"
```
