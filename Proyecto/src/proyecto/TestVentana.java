package proyecto;

import javax.swing.*;

import java.awt.*;




/**
 * TestVentana
 */
public class TestVentana extends JPanel{

    // Coordenadas centradas en la pantalla
    // private static Punto3D origen = new Punto3D(0,0,0);
    // private static Punto3D v1 = new Punto3D(200, 200, 0);
    // private static Punto3D v2 = new Punto3D(400, 200, 0);
    // private static Punto3D v3 = new Punto3D(200, 400, 0);
    // private static Punto3D v4 = new Punto3D(400, 400, 0);
    // private static Punto3D v5 = new Punto3D(300, 300, 100);
    // private static Punto3D[] piramide = new Punto3D[] {v1,v2,v3,v4,v5};
    // private static Color colorLinea = Color.WHITE;


    // Coordenadas centradas en el origen de coordenadas(0,0,0)
    private static Punto3D origen = new Punto3D(0,0,0);
    private static Punto3D v1 = new Punto3D(-100, -100, -100);
    private static Punto3D v2 = new Punto3D(100, -100, -100);
    private static Punto3D v3 = new Punto3D(-100, 100, -100);
    private static Punto3D v4 = new Punto3D(100, 100, -100);
    private static Punto3D v5 = new Punto3D(0, 0, 200);
    private static Punto3D[] piramide = new Punto3D[] {v1,v2,v3,v4,v5};
    private static Color colorLinea = Color.WHITE;



    /**
     * Pinta gráficos en la pantalla, en este caso una recta
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(colorLinea);
        // Dibujar la base de la piramide
        g.drawLine((int)piramide[0].getX(), (int)piramide[0].getY(), (int)piramide[1].getX(), (int)piramide[1].getY());
        g.drawLine((int)piramide[0].getX(), (int)piramide[0].getY(), (int)piramide[2].getX(), (int)piramide[2].getY());
        g.drawLine((int)piramide[1].getX(), (int)piramide[1].getY(), (int)piramide[3].getX(), (int)piramide[3].getY());
        g.drawLine((int)piramide[2].getX(), (int)piramide[2].getY(), (int)piramide[3].getX(), (int)piramide[3].getY());
        
        // Une la base con el vertice de la punta
        g.drawLine((int)piramide[0].getX(), (int)piramide[0].getY(), (int)piramide[4].getX(), (int)piramide[4].getY());
        g.drawLine((int)piramide[1].getX(), (int)piramide[1].getY(), (int)piramide[4].getX(), (int)piramide[4].getY());
        g.drawLine((int)piramide[2].getX(), (int)piramide[2].getY(), (int)piramide[4].getX(), (int)piramide[4].getY());
        g.drawLine((int)piramide[3].getX(), (int)piramide[3].getY(), (int)piramide[4].getX(), (int)piramide[4].getY());
    }

    /**
     * Un metodo para hacer una pausa entre pintados
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

        JFrame v = new JFrame("Prueba de ventana");
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TestVentana comp = new TestVentana();
        v.add(comp);
        v.setSize(800, 600);
        v.setResizable(true);
        v.setVisible(true);

        // Roto todos los vertices de la piramide antes de dibujarlo
        while (true) {
            for (int i=0; i < piramide.length; i++) {
                Vector3D vectorTemp = piramide[i].restaPuntoAPunto(origen);
                Vector3D vectorRotado = vectorTemp.rotarYZ(2);
                Vector3D vectorRotado2 = vectorRotado.rotarXY(2);
                Vector3D vectorRotado3 = vectorRotado2.rotarXZ(1.50);
                piramide[i] = origen.sumaVectorAPunto(vectorRotado3);
            }

            // Muevo la pirámide a un lugar más visible
            Punto3D[] piramideTemp = piramide.clone();

            for (int j = 0; j < piramide.length; j++){
                piramide[j] = piramide[j].sumaVectorAPunto(new Vector3D(v.getSize().getWidth()/2,v.getSize().getHeight()/2,0)); // Centro la pirámide en la pantalla independientemente de las dimensiones
            }

            // Espero lo sufiente y pinto 60 veces por segundo (60 FPS)
            wait(1000/60);
            v.repaint();

            //Vuelvo a centrar la pirámide en el origen
            piramide = piramideTemp;

        }


    }

}