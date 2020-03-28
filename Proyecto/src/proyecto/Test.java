package proyecto;

import javax.swing.JFrame;


/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Punto3D p1 = new Punto3D();
        Punto3D p2 = new Punto3D(3,0,1);
        Vector3D v1 = new Vector3D(3,4,5);
        Vector3D vectResta = p2.restaPuntoAPunto(p1);
        Vector3D vectResta2 = p1.restaPuntoAPunto(p2);
        Punto3D sumavector = p2.sumaVectorAPunto(v1);
        Vector3D rotacionXYVector = v1.rotarXY(90);
        Vector3D rotacionXZVector = v1.rotarXZ(90);
        Vector3D rotacionYZVector = v1.rotarYZ(90);
        Vector3D escaladoVector = v1.escalar(2, 1, 1);

        System.out.println(v1);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("resta" + vectResta);
        System.out.println("resta"+vectResta2);
        System.out.println("Trasladado"+sumavector);
        System.out.println("Rotación en XY:"+rotacionXYVector);
        System.out.println("Rotación en XZ:"+rotacionXZVector);
        System.out.println("Rotación en YZ:"+rotacionYZVector);
        System.out.println("Escalado:"+escaladoVector);
        JFrame ventana = new JFrame("Test1");
        ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setVisible(true);


    }
}