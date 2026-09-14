package br.com.iniflex;

import br.com.iniflex.model.Funcionario;
import br.com.iniflex.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServiceTest {

    private FuncionarioService service;
    private List<Funcionario> funcionarios;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService();
        funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), "Operador", new BigDecimal("2009.44")));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), "Coordenador", new BigDecimal("9836.14")));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), "Operador", new BigDecimal("1582.72")));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), "Gerente", new BigDecimal("2799.93")));
    }

    @Test
    @DisplayName("Deve aplicar 10% de reajuste no salario corretamente")
    void deveAplicarAumentoDezPorCento() {
        service.aplicarAumento(funcionarios, new BigDecimal("0.10"));

        Funcionario maria = funcionarios.get(0);
        assertEquals(new BigDecimal("2210.3840"), maria.getSalario());
    }

    @Test
    @DisplayName("Deve agrupar funcionarios por funcao")
    void deveAgruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> agrupados = service.agruparPorFuncao(funcionarios);

        assertEquals(3, agrupados.keySet().size());
        assertEquals(2, agrupados.get("Operador").size());
        assertEquals(1, agrupados.get("Coordenador").size());
        assertEquals(1, agrupados.get("Gerente").size());
    }

    @Test
    @DisplayName("Deve filtrar apenas aniversariantes dos meses especificados")
    void deveFiltrarAniversariantes() {
        List<Funcionario> filtrados = service.filtrarAniversariantes(funcionarios, 10, 12);

        assertEquals(1, filtrados.size());
        assertEquals("Maria", filtrados.get(0).getNome());
    }

    @Test
    @DisplayName("Deve identificar corretamente o funcionario com maior idade")
    void deveIdentificarFuncionarioMaisVelho() {
        Optional<Funcionario> maisVelho = service.obterMaisVelho(funcionarios);

        assertTrue(maisVelho.isPresent());
        assertEquals("Caio", maisVelho.get().getNome());
    }

    @Test
    @DisplayName("Deve somar o valor total de todos os salarios")
    void deveCalcularTotalSalarios() {
        BigDecimal totalEsperado = new BigDecimal("16228.23");
        BigDecimal totalCalculado = service.calcularTotalSalarios(funcionarios);

        assertEquals(totalEsperado, totalCalculado);
    }

    @Test
    @DisplayName("Deve calcular a quantidade de salarios minimos com precisao de duas casas decimais")
    void deveCalcularQtdSalariosMinimos() {
        Funcionario funcionario = new Funcionario("Teste", LocalDate.of(1990, 1, 1), "Analista", new BigDecimal("2424.00"));
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        BigDecimal qtd = service.calcularQtdSalariosMinimos(funcionario, salarioMinimo);

        assertEquals(new BigDecimal("2.00"), qtd);
    }
}