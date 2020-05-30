package juego;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestObjetos extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JFrame ventana;
    private static final int ALTURA = 1080;
    private static final int ANCHURA = 1920;
    private static BufferedImage fondo;
    private static int fondoFrame = 1;
    private static final double FACTOR_ESCALADO = (ALTURA*0.25)/504;
    private static ObjetoJuego personaje = new ObjetoJuego(0, 0, "assets\\ship.png");
    private static boolean modoDebug = true;

    private static int movimientoX = 0;
    private static int movimientoY = 0;

    /**
     * Un metodo para hacer una pausa entre pintados, para obtener un "framerate"
     * constante
     * 
     * @param ms
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        TestObjetos panel = new TestObjetos();
        panel.setBackground(Color.BLACK);
        ventana = new JFrame("prueba");

        // Entrada de teclado para el personaje
        ventana.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                int tecla = e.getKeyCode();

                if (tecla == KeyEvent.VK_SPACE) {
                    // TODO disparar arma
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

                int tecla = e.getKeyCode();

                if (tecla == KeyEvent.VK_D) {
                    movimientoX = 3;
                }
                if (tecla == KeyEvent.VK_A) {
                    movimientoX = -3;
                }
                if (tecla == KeyEvent.VK_W) {
                    movimientoY = -10;
                }
                if (tecla == KeyEvent.VK_S) {
                    movimientoY = 10;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                int tecla = e.getKeyCode();

                if (tecla == KeyEvent.VK_D) {
                    movimientoX = 0;
                }
                if (tecla == KeyEvent.VK_A) {
                    movimientoX = 0;
                }
                if (tecla == KeyEvent.VK_W) {
                    movimientoY = 0;
                }
                if (tecla == KeyEvent.VK_S) {
                    movimientoY = 0;
                }

            }

        });
        ventana.setSize(ANCHURA, ALTURA);
        ventana.setDefaultCloseOperation(3);
        ventana.setVisible(true);
        ventana.setResizable(true);

        ventana.getContentPane().add(panel);

        while (true) {

            //Obtencion del fondo, total de 25 fotogramas
            try {
                fondo = ImageIO.read(TestObjetos.class.getResource("assets\\background\\fondo ("+String.valueOf(fondoFrame)+").png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Limites de movimiento respecto a la resolución de la pantalla

            // Limite izquierdo de la pantalla
            if (personaje.getPosicion()[0] < 0) {
                personaje.setPosicion(0, personaje.getPosicion()[1]);
            }
            // Limite derecho de la pantalla
            if (personaje.getPosicion()[0] > ANCHURA - (personaje.getImagen().getWidth() / 2)) {
                personaje.setPosicion(ANCHURA - (personaje.getImagen().getWidth() / 2), personaje.getPosicion()[1]);
            }
            // Limite superior de la pantalla
            if (personaje.getPosicion()[1] < 0) {
                personaje.setPosicion(personaje.getPosicion()[0], 0);
            }
            // Limite inferior de la pantalla
            if (personaje.getPosicion()[1] > (ALTURA - (personaje.getImagen().getHeight()))) {
                personaje.setPosicion(personaje.getPosicion()[0], ALTURA - (personaje.getImagen().getHeight()));
            }
            personaje.setPosicion(personaje.getPosicion()[0] + movimientoX, personaje.getPosicion()[1] + movimientoY);
            wait(1000 / 60); // 1000 milisegundos por cada segundo / 60 segundos -> 60 fps
            panel.repaint();

            // Proceso para saltar al siguiente forograma del fondo, o resetearlo
            if (fondoFrame >= 25) {
                fondoFrame = 1;
            } else {
                ++fondoFrame;
            }

        }

    }

    /**
     * Superposición del metodo paintComponent de JPanel, para dibujar sobre el
     * panel principal del juego
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getScaledInstance(ANCHURA, ALTURA, Image.SCALE_SMOOTH), 0, 0, this);
        g.drawImage(personaje.getImagen().getScaledInstance((int) Math.round(personaje.getImagen().getWidth()*FACTOR_ESCALADO),
            (int) Math.round(personaje.getImagen().getHeight()*FACTOR_ESCALADO), Image.SCALE_SMOOTH),
            personaje.getPosicion()[0],
            personaje.getPosicion()[1], this);
        
        //Si el modo DEBUG esta activado, serán visibles los outline de los Hitboxes de cada ObjetoJuego
        if (modoDebug){
            //TODO que se visualicen TODOS los hitboxes
            g.setColor(Color.GREEN);
            g.drawRect(personaje.getPosicion()[0], personaje.getPosicion()[1],
             (int) Math.round(personaje.getImagen().getWidth()*FACTOR_ESCALADO),
             (int) Math.round(personaje.getImagen().getHeight()*FACTOR_ESCALADO));    
        }
        
    }
}
