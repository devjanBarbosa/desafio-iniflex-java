package br.com.iniflex.model;
import java.time.LocalDate;
import java.math.BigDecimal;

public class Funcionario extends Pessoa {

  private String funcao;
  private BigDecimal salario;

public Funcionario(String nome, LocalDate dataNascimento, String funcao, BigDecimal salario) {
    super(nome, dataNascimento);
    this.funcao = funcao;
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  @Override
  public String toString() {
    return "Funcionario{" +
            "nome='" + getNome() + '\'' +
            ", dataNascimento=" + getDataNascimento() +
            ", funcao='" + funcao + '\'' +
            ", salario=" + salario +
            '}';
  }
}