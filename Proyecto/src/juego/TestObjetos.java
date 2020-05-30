package juego;


import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class TestObjetos extends JPanel {

    private static JFrame ventana;
    private static final int ALTURA = 600;
    private static final int ANCHURA = 800;
    private static ObjetoJuego personaje = new ObjetoJuego(ANCHURA/2, ALTURA/2, "player.png"); // NO FUNCIONA -> Can't read input file
    private static Graphics2D graficos;
    private KeyListener entradaTeclado;
    private static BufferedImage imagenPrueba;
    
    public TestObjetos(){
        this.addKeyListener(entradaTeclado);
        this.repaint();
    }

    // Metodo para probar a inicializar un BufferedImage desde un archivo png en la misma ruta de la clase
    public static void cargarImagen() {

        try {
            imagenPrueba = ImageIO.read(TestObjetos.class.getResource("player.png"));
            System.out.println(imagenPrueba);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        cargarImagen();

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(personaje.getImagen().getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0,0, this);
        g.drawLine(100 , 200, 200, 200);
        //g.drawImage(imagenPrueba, 0, 0, this);
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
