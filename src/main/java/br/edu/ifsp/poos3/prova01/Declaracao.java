package br.edu.ifsp.poos3.prova01;

import java.util.Objects;

public abstract class Declaracao{// implements Entidade<Integer> {

    private final int id;
    private double ganhoTributavel;
    private double valorPago;
    protected final double GANHO_ISENTO = 22_847.88;

    public Declaracao(int id, double ganhoTributavel, double valorPago) {
        this.id = id;
        this.ganhoTributavel = ganhoTributavel;
        this.valorPago = valorPago;
    }

    public final double getValorAPagar(){
        return getValorImposto() - valorPago - getDespesaDeditivel();
    }

    public abstract double getValorImposto();

    public double getDespesaDeditivel() {
        return 0;
    }

    public final double getGanhoTributavel() {
        return ganhoTributavel;
    }

    public final void setGanhoTributavel(double ganhoTributavel) {
        this.ganhoTributavel = ganhoTributavel;
    }

    public final double getValorPago() {
        return valorPago;
    }

    public final void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public final int getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaracao that = (Declaracao) o;
        return id == that.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Ganho tributável: R$%.2f | Valor já pago: R$%.2f | Total deduzido: R$%.2f \n" +
                "Total a pagar: R$%.2f", ganhoTributavel, getValorPago(), getDespesaDeditivel(), getValorAPagar());
    }

   /* @Override
    public Integer chavePrimaria() {
        return id;
    }*/
}
