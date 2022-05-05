package br.edu.ifsp.poos3.prova01;

import java.util.Objects;

public class Gasto {
    private final int id;
    private String descricao;
    private String cnpj;
    private double valor;

    public Gasto(int id, String descricao, String cnpj, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.valor = valor;
    }

    public final int getId() {
        return id;
    }

    public final String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public final String getCnpj() {
        return cnpj;
    }

    public final void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public final double getValor() {
        return valor;
    }

    public final void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gasto gasto = (Gasto) o;
        return id == gasto.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | CNPJ: %s | Descrição: %s | Valor: R$%.2f", id, cnpj, descricao, valor);
    }
}
