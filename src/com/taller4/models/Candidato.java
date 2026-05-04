package com.taller4.models;

public class Candidato {
    private int id;
    private String nombre;
    private int acumuladorPuntos ;
    public Candidato(int id , String nombre){
        this.id = id;
        this.nombre = nombre;
        this.acumuladorPuntos = 0;
    }
    public void añadirVotos(int cantidad){
        this.acumuladorPuntos += cantidad;
    }

    public int getAcumuladorPuntos() {
        return this.acumuladorPuntos;
    }
}
