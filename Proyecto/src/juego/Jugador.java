package juego;

import java.io.File;

import javax.imageio.ImageIO;

public class Jugador extends ObjetoJuego{

    private File rutaArchivo = new File("assets/player.png");
    private Image personajeSprite = ImageIO(rutaArchivo);

}