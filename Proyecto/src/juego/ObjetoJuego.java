package juego;

import java.awt.image.BufferedImage;

public class ObjetoJuego {

    private int[] posicion;

    private BufferedImage imagen;


    public ObjetoJuego(int x, int y, BufferedImage imagen) {
        posicion[0] = x;
        posicion[1] = y;
        this.imagen = imagen;
    }
    
    public ObjetoJuego() {
        posicion[0] = 0;
        posicion[1] = 0;
        imagen = null;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public void setPosicion(int x, int y) {
        posicion[0] = x;
        posicion[1] = y;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen; 
    }

}