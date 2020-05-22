package juego;
// NO FUNCIONA
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

public class TestObjetos extends JPanel {

    private static JFrame ventana;
    private static final int ALTURA = 600;
    private static final int ANCHURA = 800;
    private static ObjetoJuego personaje = new ObjetoJuego(ANCHURA/2, ALTURA/2, "../Project/Prog_II_Proyecto/Proyecto/assets/player.png");
    private static Graphics2D graficos;
    private KeyListener entradaTeclado;

    public TestObjetos(){
        this.addKeyListener(entradaTeclado);
        this.repaint();
    }

    public static void main(String[] args) {

        TestObjetos panel = new TestObjetos();

        ventana = new JFrame("prueba");
        ventana.setSize(ANCHURA, ALTURA);
        ventana.setBackground(Color.WHITE);
        ventana.setDefaultCloseOperation(3);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.add(panel);
        ventana.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(personaje.getImagen(), personaje.getPosicion()[0],personaje.getPosicion()[1], this);
    }

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
