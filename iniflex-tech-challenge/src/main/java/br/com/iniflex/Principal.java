package br.com.iniflex;

import br.com.iniflex.model.Funcionario;
import br.com.service.FuncionarioService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Principal {

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FORMATADOR_MOEDA = 
        new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.of("pt", "BR")));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

        // 3.1 - Inserir funcionários
        List<Funcionario> funcionarios = inicializarFuncionarios();

        // 3.2 - Remover funcionário "João"
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 - Imprimir funcionários com formatações
        System.out.println("=== 3.3 LISTA DE FUNCIONÁRIOS ===");
        imprimirTabelaFuncionarios(funcionarios);

        // 3.4 - Aplicar reajuste de 10%
        service.aplicarAumento(funcionarios, new BigDecimal("0.10"));

        // 3.5 & 3.6 - Agrupar por função e imprimir
        System.out.println("\n=== 3.5 & 3.6 FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO (COM 10% DE AUMENTO) ===");
        Map<String, List<Funcionario>> agrupados = service.agruparPorFuncao(funcionarios);
        agrupados.forEach((funcao, lista) -> {
            System.out.println("\n[Função: " + funcao + "]");
            lista.forEach(f -> System.out.println(String.format("  Nome: %-10s | Salário: R$ %s",
                    f.getNome(),
                    FORMATADOR_MOEDA.format(f.getSalario()))));
        });

        // 3.8 - Filtrar aniversariantes de outubro e dezembro
        System.out.println("\n=== 3.8 ANIVERSARIANTES DOS MESES 10 E 12 ===");
        service.filtrarAniversariantes(funcionarios, 10, 12).forEach(f ->
                System.out.println(String.format("Nome: %-10s | Data Nasc: %s | Função: %s",
                        f.getNome(),
                        f.getDataNascimento().format(FORMATADOR_DATA),
                        f.getFuncao())));

        // 3.9 - Identificar funcionário de maior idade
        System.out.println("\n=== 3.9 FUNCIONÁRIO MAIS VELHO ===");
        service.obterMaisVelho(funcionarios).ifPresent(maisVelho ->
                System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + service.calcularIdade(maisVelho) + " anos"));

        // 3.10 - Ordenar funcionários em ordem alfabética
        System.out.println("\n=== 3.10 FUNCIONÁRIOS EM ORDEM ALFABÉTICA ===");
        imprimirTabelaFuncionarios(service.ordenarPorNome(funcionarios));

        // 3.11 - Total dos salários
        System.out.println("\n=== 3.11 TOTAL DOS SALÁRIOS ===");
        BigDecimal total = service.calcularTotalSalarios(funcionarios);
        System.out.println("Total: R$ " + FORMATADOR_MOEDA.format(total));

        // 3.12 - Quantidade de salários mínimos
        System.out.println("\n=== 3.12 PROPORÇÃO DE SALÁRIOS MÍNIMOS (BASE: R$ 1.212,00) ===");
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalarios = service.calcularQtdSalariosMinimos(f, SALARIO_MINIMO);
            System.out.println(String.format("Nome: %-10s | Ganha: %s salários mínimos",
                    f.getNome(),
                    FORMATADOR_MOEDA.format(qtdSalarios)));
        }
    }

    private static List<Funcionario> inicializarFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();
        lista.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), "Operador", new BigDecimal("2009.44")));
        lista.add(new Funcionario("João", LocalDate.of(1990, 5, 12), "Operador", new BigDecimal("2284.38")));
        lista.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), "Coordenador", new BigDecimal("9836.14")));
        lista.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), "Diretor", new BigDecimal("19119.88")));
        lista.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), "Recepcionista", new BigDecimal("2234.68")));
        lista.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), "Operador", new BigDecimal("1582.72")));
        lista.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), "Contador", new BigDecimal("4071.84")));
        lista.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), "Gerente", new BigDecimal("3017.45")));
        lista.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), "Eletricista", new BigDecimal("1606.85")));
        lista.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), "Gerente", new BigDecimal("2799.93")));
        return lista;
    }

    private static void imprimirTabelaFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println(String.format("Nome: %-10s | Data Nasc: %s | Função: %-13s | Salário: R$ %s",
                    f.getNome(),
                    f.getDataNascimento().format(FORMATADOR_DATA),
                    f.getFuncao(),
                    FORMATADOR_MOEDA.format(f.getSalario())));
        }
    }
}