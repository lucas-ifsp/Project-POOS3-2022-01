package br.edu.ifsp.poos3.class01;

import java.util.Scanner;

public class VariaveisOperadores {

    public static void main(String[] args) {
        int i = 1;
        int j = (int) 3.4;

        float f = 45.4f;
        long l = 10;
        double s_2 = 45.3;

        double $_numeroMuitoGra$_nd21312e = 4_534_252_345_324.3453245243;

        long SPEED_OF_LIGHT = 300_000_000;

        enum ResultadoDisciplinaPOO{APROVADO, REPROVADO}

        ResultadoDisciplinaPOO isa = ResultadoDisciplinaPOO.APROVADO;

        char a = 30;
        char b = 30;

        int c = a + b;
        System.out.println("c = " + c);

        c += 2;

        System.out.println("c = " + c);

        System.out.println("j = " + j);

        int v1 = 10;
        int v2 = 3;

        double result = v1 / v2;
        System.out.println("result = " + result);

        final Scanner scanner = new Scanner(System.in);
        /*System.out.print("Digite seu nome: ");
        final String input = scanner.nextLine();
        System.out.println("input = " + input);*/

        System.out.print("Digite 2 doubles: ");
        //final double inputDouble1 = scanner.nextDouble();
        //final double inputDouble2 = scanner.nextDouble();

        //System.out.println(inputDouble1 + " " + inputDouble2);
        System.out.printf("10/3 %.3f", 10/3.0);

    }
}
