package br.edu.ifsp.poos3.class09;

import java.util.Objects;

public class Sale {
    private long id;
    private String seller;
    private int qtd;
    private Double total;
    private Product product;

    public Sale() {
    }

    public Sale(long id, String seller, int qtd, double total, Product product) {
        this.id = id;
        this.seller = seller;
        this.qtd = qtd;
        this.product = product;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", seller='" + seller + '\'' +
                ", qtd=" + qtd +
                ", product=" + product +
                '}';
    }
}
