package com.dux;

import com.dux.models.Jugador;
import com.dux.models.Torneo;
import com.dux.utils.Lector;

import static com.dux.utils.Simulador.jugarSet;

public class Main {

    public static void main(String[] args) {

        Lector lector= new Lector();

        String nombreTorneo = lector.lectorString("Ingrese el nombre del torneo: ");

        String nombreJugador1 = lector.lectorString("Ingrese el nombre para el jugador 1: ");

        String nombreJugador2 = lector.lectorString("Ingrese el nombre para el jugador 2: ");

        double probabilidadDeGanarJ1= lector.lectorProbabilidades("Ingrese las posibilidades del jugador 1 de ganar el juego (Entero del 1 al 100): \n " +
                "- IMPORTANTE: las probabilidades del jugador 2 se calculan en funcion de las del jugador 1 para completar el 100%.");

        Double probabilidadDeGanarJ2 =  100 - probabilidadDeGanarJ1;

        Jugador jugador1 = new Jugador( nombreJugador1, probabilidadDeGanarJ1);
        Jugador jugador2 = new Jugador( nombreJugador2, probabilidadDeGanarJ2);

        Double cantidadSets =  lector.lectorCantidadSets("Ingrese la cantidad de set del juego : \n - 3: para el mejor de 3. \n - 5: para el mejor de 5. ");


        Torneo torneo= new Torneo(nombreTorneo, cantidadSets);
        torneo.agregarJugador(jugador1);
        torneo.agregarJugador(jugador2);

        jugarSet(torneo);

        lector.cerrarLector();

    }
}
