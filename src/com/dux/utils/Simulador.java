package com.dux.utils;

import com.dux.models.Jugador;
import com.dux.models.Torneo;

import java.util.Random;
import java.util.stream.Collectors;

public class Simulador {

    private static final Lector lector = new Lector();
    private static final Marcador marcador= new Marcador();
    private static Integer resultadoTieBreakJug1;
    private static Integer resultadoTieBreakJug2;


    public static boolean jugarJuego(Jugador j1, Jugador j2, Boolean isTieBreak) {

        int maxPuntaje= isTieBreak? 6 : 3;
        int marcadorJug1 = 0;
        int marcadorJug2 = 0;
        boolean ganador = true;
        boolean finJuego = false;

        while (!finJuego) {

            double randomGolpe = Math.random();

            double probabilidad= j1.getProbabilidadDeGanar() / 100;


            if (randomGolpe <= probabilidad) {
                marcadorJug1 = marcadorJug1 + 1;
                if (isTieBreak){
                    marcador.tieBreak(marcadorJug1, marcadorJug2);
                }

                if ((marcadorJug1 > maxPuntaje) && (Math.abs(marcadorJug1 - marcadorJug2) > 1)) {
                    if (isTieBreak){
                        System.out.println();
                        System.out.println(j1.getNombre() + " gana el Tie-Break!!!");
                        System.out.println();
                        resultadoTieBreakJug2 =marcadorJug2;
                        resultadoTieBreakJug1= marcadorJug1;
                    }else {
                        System.out.println();
                        System.out.println(j1.getNombre() + " gana el game!!!");
                        System.out.println();
                    }
                    marcadorJug1 = 0;
                    marcadorJug2 = 0;
                    finJuego = true;
                }
            }else{
                marcadorJug2 = marcadorJug2 + 1;
                if (isTieBreak){
                    marcador.tieBreak(marcadorJug1, marcadorJug2);
                }

                if ((marcadorJug2 > maxPuntaje) && (Math.abs(marcadorJug2 - marcadorJug1) > 1)) {
                    if (isTieBreak){
                        System.out.println();
                        System.out.println(j2.getNombre() + " gana el Tie-Break!!!");
                        System.out.println();
                        resultadoTieBreakJug2 =marcadorJug2;
                        resultadoTieBreakJug1= marcadorJug1;

                    }else {
                        System.out.println();
                        System.out.println(j2.getNombre() + " gana el game!!!");
                        System.out.println();
                    }
                    marcadorJug1 = 0;
                    marcadorJug2 = 0;
                    ganador = false;
                    finJuego = true;
                }
            }
            if (!isTieBreak){
                marcador.game(marcadorJug1, marcadorJug2);
            }
        }
        return ganador;
    }

    public static void definirSaque(boolean primerPartido, Torneo torneo){
        if (primerPartido){
            Random rnd = new Random();
            int randomSaque = rnd.nextInt(2);
            Jugador primerSaque= torneo.getJugadores().get( randomSaque);
            primerSaque.setSaque(true);
            torneo.getJugadores().get( 1 - randomSaque).setSaque(false);
            System.out.println(primerSaque.getNombre()+ " tiene el saque.");

        } else {
            Jugador ultimoSaque= torneo.getJugadores().stream().filter(Jugador::getSaque).collect(Collectors.toList()).get(0);
            Jugador nuevoSaque = torneo.getJugadores().get(1 - torneo.getJugadores().indexOf(ultimoSaque));
            ultimoSaque.setSaque(false);
            nuevoSaque.setSaque(true);
            System.out.println(nuevoSaque.getNombre()+ " tiene el saque.");
        }
    }

    public static void jugarSet(Torneo torneo) {

        Jugador j1= torneo.getJugadores().get(0);
        Jugador j2= torneo.getJugadores().get(1);
        Double sets= torneo.getCantidadSets();

        int juegosJug1 = 0;
        int juegosJug2 = 0;
        int setJug1 = 0;
        int setJug2 = 0;
        double set1 = (sets/2);
        double set = set1 + 0.5;
        boolean finPartido = false;
        boolean primerPartido=true;

        while (!finPartido) {

            definirSaque(primerPartido , torneo);

            boolean resultadoJuego = jugarJuego(j1, j2, false);

            primerPartido=false;

            if (resultadoJuego) {
                juegosJug1 = juegosJug1 + 1;
            }else {
                juegosJug2 = juegosJug2 + 1;
            }

            if (juegosJug1 == 6 && juegosJug2 == 6) {
                System.out.println("Tie-Break");
                definirSaque(false, torneo);
                boolean resultadoTieBreak =jugarJuego(j1, j2, true);
                if (resultadoTieBreak) {
                    juegosJug1 = juegosJug1 + 1 ;
                }else {
                    juegosJug2 = juegosJug2 + 1;
                }
            }

            if (juegosJug1 >= 6 && (Math.abs(juegosJug1 - juegosJug2) > 1) || juegosJug1 == 7 && juegosJug2 == 6) {
                setJug1 = setJug1 + 1;
                String resultadoJug1;
                String resultadoJug2;

                if (juegosJug1 == 7 && juegosJug2 == 6 || juegosJug1 == 6 && juegosJug2 == 7){
                    resultadoJug1= juegosJug1+" ( " + resultadoTieBreakJug1 + " )";
                    resultadoJug2= juegosJug2+" ( " + resultadoTieBreakJug2 + " )";
                }else {
                    resultadoJug1= Integer.toString(juegosJug1);
                    resultadoJug2= Integer.toString(juegosJug2);
                }
                j1.agregarSet(resultadoJug1);
                j2.agregarSet(resultadoJug2);
                marcador.parciales(torneo, setJug1, resultadoJug1, setJug2, resultadoJug2);
                juegosJug1 = 0;
                juegosJug2 = 0;
                if (setJug1 == set && setJug1 > setJug2) {
                    j1.setWinner(true);
                    marcador.finales(torneo);
                    if ( lector.lectorString("Desea jugar la revancha? si / no ").equalsIgnoreCase("no")){
                        finPartido = true;
                    }else {
                        setJug1 = 0;
                        setJug2 = 0;
                        primerPartido=true;
                        j1.setWinner(false);
                        j2.setWinner(false);
                        j1.eliminarResultados();
                        j2.eliminarResultados();
                    }
                }
            }
            if (juegosJug2 >= 6 && (Math.abs(juegosJug2 - juegosJug1) > 1) || juegosJug2 == 7 && juegosJug1 == 6) {
                setJug2 = setJug2 + 1;
                String resultadoJug1;
                String resultadoJug2;

                if (juegosJug1 == 7 && juegosJug2 == 6 || juegosJug1 == 6 && juegosJug2 == 7){
                    resultadoJug1= juegosJug1+" ( " + resultadoTieBreakJug1 + " )";
                    resultadoJug2= juegosJug2+" ( " + resultadoTieBreakJug2 + " )";
                }else {
                    resultadoJug1= Integer.toString(juegosJug1);
                    resultadoJug2= Integer.toString(juegosJug2);
                }
                j1.agregarSet(resultadoJug1);
                j2.agregarSet(resultadoJug2);
                marcador.parciales(torneo, setJug1, resultadoJug1, setJug2, resultadoJug2);

                juegosJug1 = 0;
                juegosJug2 = 0;
                if (setJug2 == set && setJug2 > setJug1) {
                    j2.setWinner(true);
                    marcador.finales(torneo);
                    if (lector.lectorString("Desea jugar la revancha? si / no ").equalsIgnoreCase("no")){
                        finPartido = true;
                    }else {
                        setJug1 = 0;
                        setJug2 = 0;
                        primerPartido=true;
                        j1.setWinner(false);
                        j2.setWinner(false);
                        j1.eliminarResultados();
                        j2.eliminarResultados();
                    }
                }
            }
        }
    }
}
