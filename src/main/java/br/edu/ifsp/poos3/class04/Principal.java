package br.edu.ifsp.poos3.class04;

public class Principal {
    public static void main(String[] args) {
        Triangulo t1 = new Triangulo(1, 1, 3, 4, 5);
        Circulo c1 = new Circulo(0, 0, 1);
        Retangulo r1 = new Retangulo(3, 3, 10, 5);
        System.out.println(t1);
        System.out.println(c1);
        System.out.println(r1.getArea());

    }
}
