package br.edu.ifsp.poos3.class04;

public class Retangulo extends Figura{

    private final double largura;
    private final double comprimento;

    public Retangulo(int x, int y, double comprimento, double largura) {
        super(x, y);
        this.comprimento = comprimento;
        this.largura = largura;
    }

    @Override
    public double getArea() {
        return largura * comprimento;
    }

    public double getLargura() {
        return largura;
    }

    public double getComprimento() {
        return comprimento;
    }
}
