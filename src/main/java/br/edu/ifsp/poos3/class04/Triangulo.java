package br.edu.ifsp.poos3.class04;

public class Triangulo extends Figura{

    private final double ladoA;
    private final double ladoB;
    private final double ladoC;

    public Triangulo(int x, int y, double ladoA, double ladoB, double ladoC) {
        super(x, y);
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    @Override
    public double getArea() {
        double p = (ladoA + ladoB + ladoC)/2;
        return Math.sqrt(p*(p-ladoA)*(p-ladoB)*(p-ladoC));
    }

    public double getLadoA() {
        return ladoA;
    }

    public double getLadoB() {
        return ladoB;
    }

    public double getLadoC() {
        return ladoC;
    }
}
