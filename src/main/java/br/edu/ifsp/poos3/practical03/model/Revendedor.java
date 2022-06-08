package br.edu.ifsp.poos3.practical03.model;

public class Revendedor extends Funcionario {

    public Revendedor(String cpf) {
        this(cpf, null, 0, null, 0.0, null);
    }

    public Revendedor(String cpf, String nome, int idade, Sexo sexo, double valorVendido, Consultor responsavel) {
        super(cpf, nome, idade, sexo, valorVendido, responsavel);
    }

    @Override
    public double calculaComissao() {
        return getValorVendido() * 0.15;
    }
}
