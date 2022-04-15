package br.edu.ifsp.poos3.practical2Extra;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Funcionario implements EntidadePersistente<String>{

    private static final Map<String,Funcionario> BD = new LinkedHashMap<>();

    private final String cpf;
    private String nome;
    private int idade;
    private boolean sexo;
    private double valorVendido;
    private Consultor responsavel;

    public Funcionario(String cpf, String nome, int idade, boolean sexo, double valorVendido, Consultor responsavel) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.valorVendido = valorVendido;
        this.responsavel = responsavel;
        if(responsavel != null) responsavel.addSubordinado(this);
    }

    public abstract double calculaComissao();

    public Consultor getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Consultor responsavel) {
        this.responsavel = responsavel;
        if(this.responsavel != null)
            responsavel.addSubordinado(this);
    }

    @Override
    public String primaryKey() {
        return cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public double getValorVendido() {
        return valorVendido;
    }

    public void setValorVendido(double valorVendido) {
        this.valorVendido = valorVendido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return  "cpf = " + cpf +
                " | nome = " + nome +
                " | idade = " + idade +
                " | sexo = " + sexo +
                " | valor vendido = " + valorVendido +
                " | responsável = " + (responsavel!= null ? responsavel.getNome() : "NA") +
                " | comissão = " + calculaComissao() +
                "\n";
    }
}
