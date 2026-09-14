package br.com.service;

import br.com.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FuncionarioService {

    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual);
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(fator));
        }
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> filtrarAniversariantes(List<Funcionario> funcionarios, int... meses) {
        return funcionarios.stream()
                .filter(f -> {
                    int mesNasc = f.getDataNascimento().getMonthValue();
                    for (int m : meses) {
                        if (mesNasc == m) return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public Optional<Funcionario> obterMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public int calcularIdade(Funcionario funcionario) {
        return Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    public BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularQtdSalariosMinimos(Funcionario funcionario, BigDecimal salarioMinimo) {
        return funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN);
    }
}
