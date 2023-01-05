package com.dux.models;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private final String nombre;
    private final Double probabilidadDeGanar;
    private boolean saque;
    private boolean winner;
    private List<String>resultadosSets;

    public Jugador(String nombre, Double probabilidadDeGanar) {
        this.nombre = nombre;
        this.probabilidadDeGanar = probabilidadDeGanar;
        this.saque=false;
        this.winner= false;
        this.resultadosSets=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Double getProbabilidadDeGanar() {
        return probabilidadDeGanar;
    }

    public boolean getSaque(){ return saque; }

    public boolean getWinner(){ return winner; }

    public List<String> getResultadosSets() {
        return resultadosSets;
    }

    public void setSaque( boolean saque ){ this.saque= saque; }

    public void setWinner( boolean winner){ this.winner = winner;}

    public void setResultadosSets(List<String> resultadosSets) {
        this.resultadosSets = resultadosSets;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", probabilidadDeGanar=" + probabilidadDeGanar +
                ", saque=" + saque +
                ", winner=" + winner +
                '}';
    }

    public void agregarSet(String set){
        resultadosSets.add(set);
    }

    public void eliminarResultados(){ resultadosSets.clear();}

}

