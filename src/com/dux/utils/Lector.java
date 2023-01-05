package com.dux.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lector {

    private static Scanner lectorScanner;

    public Lector() {
        lectorScanner= new Scanner(System.in);
    }

    public String lectorString(String condicion){
        String res="";

        while (res.equals("") || !Character.isAlphabetic(res.charAt(0))){
            System.out.println(condicion);
            res= lectorScanner.nextLine();
        }
        return res;
    }

    public Double lectorProbabilidades(String condicion) {
        double res = 0.0;

        while (res < 1 || res > 100) {
            System.out.println(condicion);
            try {
                res = lectorScanner.nextDouble();
            }catch (InputMismatchException e){
                lectorScanner.nextLine();
                System.out.println("ADVERTENCIA: Tipo de dato incorrecto! ");
            }
        }
        return res;
    }

    public Double lectorCantidadSets(String condicion){
        double res = 0.0;

        while (res != 3 && res != 5){
            System.out.println(condicion);
            try {
                res = lectorScanner.nextDouble();
            }catch (InputMismatchException e){
                lectorScanner.nextLine();
                System.out.println("ADVERTENCIA: Tipo de dato incorrecto! ");
            }
        }
        return res;
    }

    public void cerrarLector(){
        lectorScanner.close();
    }
}
