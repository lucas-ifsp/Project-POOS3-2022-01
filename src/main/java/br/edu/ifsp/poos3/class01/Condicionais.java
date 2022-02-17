package br.edu.ifsp.poos3.class01;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Condicionais {

    public static void main(String[] args) {
        final LocalDate today = LocalDate.now();
        final DayOfWeek dayOfWeek = today.getDayOfWeek();

        /*if(dayOfWeek == DayOfWeek.THURSDAY) {
            System.out.println("POOS3 class");
        } else if (dayOfWeek == DayOfWeek.SATURDAY){
            System.out.println("Saturday");
        } else {
            System.out.println("Other");
        }*/

        System.out.println(switch (dayOfWeek) {
            case FRIDAY, SATURDAY -> "\\o/";
            case SUNDAY -> "<o/";
            default -> "=|";
        });

        System.out.println(dayOfWeek == DayOfWeek.THURSDAY? "=|" : "\\o/");


    }
}
