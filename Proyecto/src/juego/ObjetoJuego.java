package juego;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ObjetoJuego {

    private int[] posicion = new int[2];
    private String imagenPath;
    private BufferedImage imagen;

    public ObjetoJuego(int x, int y, String imagenPath) {
        this.setPosicion(x, y);
        this.imagenPath = imagenPath;
       
        try {
           // imagen = ImageIO.read(archivoimagen); //error
           imagen = ImageIO.read(ObjetoJuego.class.getResource(imagenPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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