package br.edu.ifsp.poos3.prova01;

public class DeclaracaoSimplificada extends Declaracao{

    public DeclaracaoSimplificada(int id, double ganhoTributavel, double valorPago) {
        super(id, ganhoTributavel, valorPago);
    }

    @Override
    public double getValorImposto() {
        if(getGanhoTributavel() <= GANHO_ISENTO) return 0.0;
        return (getGanhoTributavel() - GANHO_ISENTO) * 0.2;
    }

    @Override
    public String toString() {
        return "=== Declaração simplificada === \n" + super.toString();
    }
}
