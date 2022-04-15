package br.edu.ifsp.poos3.practical02;

public class Revendedor extends Funcionario{

    public Revendedor(String cpf, String nome, int idade, boolean sexo, double valorVendido, Consultor responsavel) {
        super(cpf, nome, idade, sexo, valorVendido, responsavel);
    }

    @Override
    public double calculaComissao() {
        return getValorVendido() * 0.15;
    }
}
