package br.edu.ifsp.poos3.class02;

import java.util.Scanner;

/*
Faça um programa que leia dados inteiros da temperatura diária durante uma semana, armazenando em um array.
Na sequência, escreva quantos dias dessa semana a temperatura esteve acima da média. As sete temperaturas
devem ser lidas na mesma linha, separada por espaço.

Exemplos de entrada e saída esperada:

Exemplo 1: Entrada = 2 2 2 2 2 2 3 | Saída = 1
Exemplo 2: Entrada = 21 10 13 34 30 21 34 | Saída = 3
Exemplo 3: Entrada = 2 2 2 2 2 2 1| Saída = 6
Qualquer valor fora do domínio de entrada tem como saída esperada a String "Erro".
* */
class Ex01 {

    public static final int DAYS_OF_WEEK = 7;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] temperatures = new int[DAYS_OF_WEEK];
        int sumOfTemperatures = 0;

        for (int i = 0; i < DAYS_OF_WEEK; i++) {
            temperatures[i] = scanner.nextInt();
            sumOfTemperatures += temperatures[i];
        }

        int daysAboveAverage = 0;
        final double averageTemperature = (double) sumOfTemperatures / DAYS_OF_WEEK;
        for (int temperature : temperatures) {
            if(temperature > averageTemperature){
                daysAboveAverage++;
            }
        }

        System.out.println(daysAboveAverage);
    }
}
