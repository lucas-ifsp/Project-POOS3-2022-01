package br.edu.ifsp.poos3.practical02;

import java.util.ArrayList;
import java.util.List;

public class Consultor extends Funcionario{

    private final List<Funcionario> subordinados;

    public Consultor(String cpf, String nome, int idade, boolean sexo, double valorVendido, Consultor responsavel) {
        super(cpf, nome, idade, sexo, valorVendido, responsavel);
        subordinados = new ArrayList<>();
    }

    public Consultor(Revendedor revendedor){
        this(revendedor.getCpf(), revendedor.getNome(), revendedor.getIdade(),
                revendedor.isSexo(), revendedor.getValorVendido(), revendedor.getResponsavel());
    }

    @Override
    public double calculaComissao() {
        double comissao = getValorVendido() * 0.15;
        for (Funcionario subordinado : subordinados) {
            comissao += subordinado.calculaComissao() * 0.3;
        }
        return comissao;
    }

    public void addSubordinado(Funcionario subordinado){
        subordinados.remove(subordinado); //remove a instância anterior do subordinado, se houver.
        subordinados.add(subordinado);
    }

    public void removeSubordinado(Funcionario subordinado){
        subordinados.remove(subordinado);
    }

    public void promoveSubordinados(){
        for (Funcionario subordinado : subordinados) {
            subordinado.setResponsavel(this.getResponsavel());
        }
    }

    public int numeroDeSubordinados(){
        int resultado = subordinados.size();
        for (Funcionario subordinado : subordinados) {
            if(subordinado instanceof Consultor){
                resultado += ((Consultor) subordinado).numeroDeSubordinados();
            }
        }
        return resultado;
    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder(super.toString());
        builder.append("Total de subordinados: ").append(numeroDeSubordinados()).append("\n");
        builder.append("Subordinados diretos: \n");

        for (Funcionario subordinado : subordinados) {
            builder.append("\t"). append(subordinado.getNome()).append("\n");
        }
        return builder.toString();
    }
}
