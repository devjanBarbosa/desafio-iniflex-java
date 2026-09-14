package br.com.iniflex;

import br.com.iniflex.model.Funcionario;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), "Operador", new BigDecimal("2009.44")));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), "Operador", new BigDecimal("2284.38")));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), "Coordenador", new BigDecimal("9836.14")));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), "Diretor", new BigDecimal("19119.88")));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), "Recepcionista", new BigDecimal("2234.68")));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), "Operador", new BigDecimal("1582.72")));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), "Contador", new BigDecimal("4071.84")));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), "Gerente", new BigDecimal("3017.45")));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), "Eletricista", new BigDecimal("1606.85")));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), "Gerente", new BigDecimal("2799.93")));

        // 3.2 - Remover o funcionário João da lista
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 - Imprimir funcionários formatados (dd/MM/yyyy e R$ #,##0.00)
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat formatadorSalario = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        System.out.println("--- LISTA DE FUNCIONÁRIOS ---");
        for (Funcionario f : funcionarios) {
            System.out.println(String.format("Nome: %-10s | Data Nasc: %s | Função: %-13s | Salário: R$ %s",
                    f.getNome(),
                    f.getDataNascimento().format(formatadorData),
                    f.getFuncao(),
                    formatadorSalario.format(f.getSalario())));
        }

        BigDecimal multiplicador = new BigDecimal("1.10");


// 3.4 - Aplicar 10% de aumento de salário
        BigDecimal fatorAumento = new BigDecimal("1.10");
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(fatorAumento));
        }

        // 3.5 - Agrupar funcionários por função num Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\n--- FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO (COM 10% DE AUMENTO) ---");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("\nFunção: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println(String.format("  Nome: %-10s | Data Nasc: %s | Salário: R$ %s",
                        f.getNome(),
                        f.getDataNascimento().format(formatadorData),
                        formatadorSalario.format(f.getSalario())));
            }
        }

        // 3.8 - Funcionários que fazem aniversário no mês 10 e 12
System.out.println("\n--- ANIVERSARIANTES DOS MESES 10 E 12 ---");
funcionarios.stream()
        .filter(f -> {
            int mes = f.getDataNascimento().getMonthValue();
            return mes == 10 || mes == 12;
        })
        .forEach(f -> System.out.println(String.format("Nome: %-10s | Data Nasc: %s",
                f.getNome(),
                f.getDataNascimento().format(formatadorData))));

// 3.9 - Funcionário com a maior idade (menor data de nascimento)
System.out.println("\n--- FUNCIONÁRIO MAIS VELHO ---");
funcionarios.stream()
        .min(Comparator.comparing(Funcionario::getDataNascimento))
        .ifPresent(maisVelho -> {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");
        });


        // 3.10 - Imprimir funcionários em ordem alfabética
System.out.println("\n--- FUNCIONÁRIOS EM ORDEM ALFABÉTICA ---");
funcionarios.stream()
        .sorted(Comparator.comparing(Funcionario::getNome))
        .forEach(f -> System.out.println(String.format("Nome: %-10s | Data Nasc: %s | Função: %-13s | Salário: R$ %s",
                f.getNome(),
                f.getDataNascimento().format(formatadorData),
                f.getFuncao(),
                formatadorSalario.format(f.getSalario()))));

    }

    
}