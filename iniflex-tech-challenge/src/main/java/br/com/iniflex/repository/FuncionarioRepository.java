package br.com.iniflex.repository;

import br.com.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    public List<Funcionario> buscarTodos() {
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
}