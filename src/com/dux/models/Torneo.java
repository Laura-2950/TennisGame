package com.dux.models;

import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private final String nombreTorneo;
    private final Double cantidadSets;
    private final List<Jugador> jugadores;

    public Torneo(String nombreTorneo, Double cantidadSets) {
        this.nombreTorneo = nombreTorneo;
        this.cantidadSets = cantidadSets;
        this.jugadores=new ArrayList<>();
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public Double getCantidadSets() {
        return cantidadSets;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "nombreTorneo='" + nombreTorneo + '\'' +
                ", cantidadSets=" + cantidadSets +
                ", jugadores=" + jugadores +
                '}';
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

}
