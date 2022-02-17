package br.edu.ifsp.poos3.class01;

/*
Uma livraria está fazendo uma promoção para pagamento a vista.
O comprador pode escolher entre dois critérios de pagamento:
Critério A: R$ 0,25 por livro + R$ 7,50 fixo
Critério B: R$ 0,50 por livro + R$ 2,50 fixo
*/

import java.util.Scanner;

public class Main {

    public static final double FIXED_AMOUNT_A = 7.5;
    public static final double FIXED_AMOUNT_B = 2.5;
    public static final double PER_ITEM_A = 0.25;
    public static final double PER_ITEM_B = 0.5;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int numberOfBooks = scanner.nextInt();

        if (numberOfBooks <= 0) {
            System.out.println("Erro");
            return;
        }

        double criteriaA = PER_ITEM_A * numberOfBooks + FIXED_AMOUNT_A;
        double criteriaB = PER_ITEM_B * numberOfBooks + FIXED_AMOUNT_B;

        if (criteriaA == criteriaB)
            System.out.println("Indiferente");
        else if (criteriaA > criteriaB)
            System.out.println("Criterio B");
        else
            System.out.println("Criterio A");
    }
}
