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



public class TestObjetos extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JFrame ventana;
    private static final int ALTURA = 600;
    private static final int ANCHURA = 800;
    private static ObjetoJuego personaje = new ObjetoJuego(ANCHURA/2, ALTURA/2, "player.png"); 
    private Teclado entradaTeclado;
    
    public TestObjetos(){
        this.addKeyListener(entradaTeclado);
        this.repaint();
    }

    public static void main(String[] args) {

        TestObjetos panel = new TestObjetos();
        panel.setBackground(Color.BLACK);

        ventana = new JFrame("prueba");
        ventana.setSize(ANCHURA, ALTURA);
        ventana.setDefaultCloseOperation(3);
        ventana.setVisible(true);
        ventana.setResizable(false);

        ventana.getContentPane().add(panel);
        ventana.repaint();
    }

    /**
     * Superposición del metodo paintComponent de JPanel, para dibujar sobre el panel principal del juego
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(personaje.getImagen().getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0,0, this);
        g.drawLine(100 , 200, 200, 200);
        //g.drawImage(imagenPrueba, 0, 0, this);
    }

    /**
     * Asignaciones de teclado para el movimiento y acciones del ObjetoJuego personaje
     */
    private class Teclado implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }
        
    }
}
