package com.dux.utils;

import com.dux.models.Torneo;


public class Marcador {

    public void game(Integer marcadorJug1, Integer marcadorJug2){
        String marcadorPuntos = "";
        String puntosJug1 = "0";
        String puntosJug2 = "0";
        if (marcadorJug1 == 1) {
            puntosJug1 = "15";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug1 == 2) {
            puntosJug1 = "30";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug1 == 3) {
            puntosJug1 = "40";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug2 == 1) {
            puntosJug2 = "15";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug2 == 2) {
            puntosJug2 = "30";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug2 == 3) {
            puntosJug2 = "40";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug1 > 3  && marcadorJug1.equals(marcadorJug2)) {
            puntosJug1 = "40";
            puntosJug2 = "40";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug1 > 3 && marcadorJug1 > marcadorJug2) {
            puntosJug1 = "AD";
            puntosJug2 = "40";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }
        if (marcadorJug2 > 3 && marcadorJug2 > marcadorJug1) {
            puntosJug1 = "40";
            puntosJug2 = "AD";
            marcadorPuntos = puntosJug1 + " - " + puntosJug2;
        }

        System.out.println(marcadorPuntos);
    }

    public void parciales(Torneo torneo, Integer setsJug1, String puntaje1, Integer setsJug2, String puntaje2 ){

        System.out.println();
        System.out.printf("----------------------------------------------%n");
        System.out.printf("| %-42S |%n", torneo.getNombreTorneo() +" \uD83C\uDFBE ");
        System.out .printf( "| %-42s |%n", "(Resultado parcial)" );
        System.out.printf("----------------------------------------------%n");
        System.out.printf("| %-10S | %-7S | %-7S  | %-7S  |%n", "jUGADOR", "SAQUE", "SETS", "GAMES");
        System.out.printf("----------------------------------------------%n");

        System.out.printf("| %-10S | %-7s | %7s  | %7s  |%n", torneo.getJugadores().get(0).getNombre(),
                torneo.getJugadores().get(0).getSaque()?"✅":"",
                setsJug1, puntaje1);
        System.out.printf("| %-10S | %-7s | %7s  | %7s  |%n", torneo.getJugadores().get(1).getNombre(),
                torneo.getJugadores().get(1).getSaque()?"✅":"",
                setsJug2, puntaje2);

        System.out.printf("----------------------------------------------%n");
        System.out.println();
    }

    public void finales(Torneo torneo){

        System.out.println();
        System.out.printf("----------------------------------------------%n");
        System.out.printf("| %-42S |%n", torneo.getNombreTorneo() +" \uD83C\uDFBE ");
        System.out .printf( "| %-42s |%n", "(Resultado final)");
        System.out.printf("----------------------------------------------%n");
        System.out.printf("| %-10S | %-10S | %-16S |%n", "jUGADOR", "GANADOR", "SETS");
        System.out.printf("----------------------------------------------%n");

        System.out.printf("| %-10S | %-10s | %17s |%n", torneo.getJugadores().get(0).getNombre(),
                torneo.getJugadores().get(0).getWinner()?"\uD83C\uDFC6 ":"",
                torneo.getJugadores().get(0).getResultadosSets());
        System.out.printf("| %-10S | %-10s | %17s |%n", torneo.getJugadores().get(1).getNombre(),
                torneo.getJugadores().get(1).getWinner()?"\uD83C\uDFC6 ":"",
                torneo.getJugadores().get(1).getResultadosSets());

        System.out.printf("----------------------------------------------%n");
        System.out.println();

    }

    public void tieBreak(int marcadorJug1, int marcadorJug2) {
        System.out.println(marcadorJug1 + " - " + marcadorJug2);
    }
}
