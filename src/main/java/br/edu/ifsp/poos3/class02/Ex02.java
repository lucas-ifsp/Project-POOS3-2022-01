package br.edu.ifsp.poos3.class02;

import java.util.Scanner;

/*
Faça um programa que construa dois vetores A e B de 5 posições, lendo e adicionando valores inteiros a esses vetores.
Crie um terceiro vetor C, composto pela soma dos elementos de A e B. Por exemplo:

C[0] = A[0] + B[0]
C[1] = A[1] + B[1]

Após isso, escreva o conteúdo do vetor C, separados por vírgula.
Qualquer situação fora do domínio de entrada resulta em saída uma “Erro”.

Exemplos de entrada e saída esperada:

Entrada = 2 5 8 34 5                 | Saída = 10, 56, 10, 50, 10
          8 51 2 16 5
Entrada = -10 0 10 20 30         | Saída = 90, 50, 10, -30, -70
          100 50 0 -50 -100*/
public class Ex02 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] arrayA = new int[5];
        final int[] arrayB = new int[5];
        final int[] arrayC = new int[5];

        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = scanner.nextInt();
        }
        for (int i = 0; i < arrayB.length; i++) {
            arrayB[i] = scanner.nextInt();
        }

        for (int i = 0; i < arrayA.length; i++) {
            if(sumOverflows(arrayA[i], arrayB[i]) || sumUnderflows(arrayA[i], arrayB[i])){
                System.out.println("Erro");
                return;
            }
            arrayC[i] = arrayA[i] + arrayB[i];
        }

        String output = formatOutput(arrayC);
        System.out.println(output);
    }

    private static boolean sumUnderflows(int a, int b ) {
        return (long) a + b < -2147483648;
    }

    private static boolean sumOverflows(int a, int b) {
        return (long) a + b  > 2147483647;
    }
    private static String formatOutput(int[] arrayC) {
        String output = "";
        for (int i = 0; i < arrayC.length; i++) {
            output += "" + arrayC[i];
            if(i < arrayC.length - 1){
                output += ", ";
            }
        }
        return output;
    }

    
}
