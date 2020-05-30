package juego;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Partida extends JPanel {

    private static final long serialVersionUID = 1L;
    public static JFrame ventana;
    private static int ALTURA = 1080;
    private static int ANCHURA = 1920;
    private static BufferedImage fondo;
    private static int fondoFrame = 1;
    private static final double FACTOR_ESCALADO = (ALTURA * 0.25) / 504;
    private static ObjetoJuego personaje = new ObjetoJuego(0, 0, "assets\\ship.png");
    private static boolean modoDebug = true;
    private static CopyOnWriteArrayList<ObjetoJuego> proyectilesAmigo = new CopyOnWriteArrayList<ObjetoJuego>();
    private static int movimientoX = 0;
    private static int movimientoY = 0;

    /**
     * Crea una partida desde cero con resolución x*y
     * @param x Anchura de la resolución
     * @param y Altura de la resolución
     */
    
    public static void setResolucion(int x, int y){
        ANCHURA = x;
        ALTURA = y;
    }

    /**
     * Por defecto la partida se creará con resolución FullHD (1920x1080)
     */
    public Partida(){
    }

    /**
     * Un metodo para hacer una pausa entre pintados, para obtener un "framerate"
     * constante
     * 
     * @param ms Milisegundos a esperar entre pintados -> Para framerate:
     *           1000/(framerate)
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Originalmente el main(), ahora intento lanzarlo desde la clase Menu.java 
     */
    public static void iniciarPartida() {

        Partida panel = new Partida();
        panel.setBackground(Color.BLACK);
        ventana = new JFrame("prueba");
        ventana.addWindowListener(new WindowListener(){

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowClosing(WindowEvent e) {

                Menu.menuPrincipal.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }
            
        });

        // Entrada de teclado para el personaje
        ventana.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                int tecla = e.getKeyCode();

                if (tecla == KeyEvent.VK_SPACE) {
                    // TODO disparar arma
                    ObjetoJuego disparoAmigo = new ObjetoJuego(
                            personaje.getPosicion()[0] + personaje.getImagen().getWidth(),
                            personaje.getPosicion()[1] / 2, "assets\\blastFriendly.png");
                    proyectilesAmigo.add(disparoAmigo);
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
                if (tecla == KeyEvent.VK_SPACE) {
                    ObjetoJuego disparoAmigo = new ObjetoJuego(
                            (int) Math.round(
                                    personaje.getPosicion()[0] + personaje.getImagen().getWidth() * FACTOR_ESCALADO),
                            (int) Math.round(personaje.getPosicion()[1]
                                    + (personaje.getImagen().getHeight() * FACTOR_ESCALADO / 2)),
                            "assets\\blastFriendly.png");
                    proyectilesAmigo.add(disparoAmigo);
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
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setResizable(true);

        ventana.getContentPane().add(panel);

        while (true) {

            // Obtencion del fondo, total de 25 fotogramas
            try {
                fondo = ImageIO.read(Partida.class
                        .getResource("assets\\background\\fondo (" + String.valueOf(fondoFrame) + ").png"));
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

            //Si un disparo sale de la pantalla se elimina
            for (ObjetoJuego proyectil : proyectilesAmigo){


                if ( (proyectil.getPosicion()[0]+proyectil.getImagen().getWidth()*FACTOR_ESCALADO) > ANCHURA ){
                    proyectilesAmigo.remove(proyectil);
                } else {

                    proyectil.setPosicion(proyectil.getPosicion()[0]+20, proyectil.getPosicion()[1]);

                }
                
            }

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

        for (ObjetoJuego proyectil : proyectilesAmigo) {
            g.drawImage(proyectil.getImagen().getScaledInstance((int) Math.round(proyectil.getImagen().getWidth()*FACTOR_ESCALADO),
            (int) Math.round(proyectil.getImagen().getHeight()*FACTOR_ESCALADO), Image.SCALE_SMOOTH), proyectil.getPosicion()[0], proyectil.getPosicion()[1], this);
        }
        
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