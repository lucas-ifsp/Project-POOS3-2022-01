package br.edu.ifsp.poos3.prova01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DeclaracaoCompleta extends Declaracao{

    private final List<Gasto> deducoes;

    public DeclaracaoCompleta(int id, double ganhoTributavel, double valorPago) {
        super(id, ganhoTributavel, valorPago);
        deducoes = new ArrayList<>();
    }

    @Override
    public double getValorImposto() {
        double ganho = getGanhoTributavel();
        double imposto = 0.0;

        if(ganho > 55_976.16){
            imposto += (ganho - 55_976.16) * 0.275;
            ganho = 55_976.16;
        }
        if(ganho > 45_012.73){
            imposto += (ganho - 45_012.73) * 0.225;
            ganho = 45_012.73;
        }
        if(ganho > 33_919.93){
            imposto += (ganho - 33_919.93) * 0.15;
            ganho = 55_976.16;
        }
        if(ganho > 22_847.88){
            imposto += (ganho - 22_847.88) * 0.075;
        }
        return imposto;
    }

    @Override
    public double getDespesaDeditivel() {
        final double gastoEducacao = deducoes.stream()
                .filter(gasto -> gasto instanceof GastoEducacao)
                .map(Gasto::getValor)
                .reduce(0.0, Double::sum);

        final double gastoSaude = deducoes.stream()
                .filter(gasto -> gasto instanceof GastoSaude)
                .map(Gasto::getValor)
                .reduce(0.0, Double::sum);

        return Math.min(GastoEducacao.DEDUCAO_MAX_EDUCACAO, gastoEducacao)
                + Math.min(GastoSaude.DEDUCAO_MAX_SAUDE, gastoSaude);
    }

    public void addGasto(Gasto gasto){
        final Gasto gastoDedutivel = Objects.requireNonNull(gasto, "Gasto não pode ser nulo");
        final boolean novoGasto = deducoes.stream().noneMatch(g -> g.getId() == gasto.getId());
        if (novoGasto){
            deducoes.add(gastoDedutivel);
            return;
        }
        final int indiceDoGasto = deducoes.indexOf(gasto);
        deducoes.set(indiceDoGasto, gasto);
    }

    public void removeGasto(Gasto gasto){
        deducoes.remove(Objects.requireNonNull(gasto, "Gasto não pode ser nulo"));
    }

    public Optional<Gasto> getGasto(int id){
        return deducoes.stream().filter(gasto -> gasto.getId() == id).findFirst();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gastos Dedutíveis:").append("\n");
        deducoes.forEach(deducao -> sb.append(deducao).append("\n"));
        return "=== Declaração Completa ===\n" + super.toString() + "\n" + sb;
    }
}
