package juego;

import java.io.Serializable;

public class Puntuacion implements Serializable{

    private int puntos;
    private String jugador;

    public Puntuacion(int puntos, String jugador){
        this.puntos = puntos;
        this.jugador = jugador;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
    
    @Override
    public String toString() {
        return jugador + " -> " + puntos;
    }
}