package proyecto;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class TestMath extends JPanel{

    public void paint(Graphics g){
        
        g.setColor(Color.white);
        g.drawLine(10, 10, 200, 300);		
    }
    
    public static void main(String[] args){
        JFrame ventana= new JFrame("Prueba de dibujar línea");	
        ventana.getContentPane().add(new TestMath());
        ventana.setBackground(Color.black);
        ventana.setSize(600, 400);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        
        
    }
}