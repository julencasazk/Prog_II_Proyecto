package juego;

public class Entidad  extends ObjetoJuego{

    private float velocidad;

    public Entidad(int x, int y, BufferedImage imagen, float velocidad) {
        super(x, y, imagen);
        this.velocidad = velocidad;
    }

    public  Entidad() {
        super(0,0, null);
        velocidad = 0;
    }


}