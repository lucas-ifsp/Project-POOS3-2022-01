package br.edu.ifsp.poos3.class04;

public class Circulo extends Figura{

    private final double raio;

    public Circulo(int x, int y, double raio) {
        super(x, y);
        this.raio = raio;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(raio, 2);
    }

    @Override
    public String toString() {
        return String.format("Circulo (%d, %d: r = %f | área: %f", getX(), getY(), raio, getArea());
    }

    public double getRaio() {
        return raio;
    }
}
