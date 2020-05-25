package juego;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu {

    private static JFrame menuPrincipal;
    private static JFrame menuOpciones;
    private static int ANCHURA = 800;
    private static int ALTRUA = 600;

    public static void main(String[] args) {

        initMenuPrincipal();
        initOpciones();

    }

    public static void initMenuPrincipal() {
        menuPrincipal = new JFrame("Menu principal");
        menuPrincipal.setSize(ANCHURA, ALTRUA);
        menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton bJugar = new JButton("Comenzar partida");
        JButton bOpciones = new JButton("Ajutes");
        JButton bSalir = new JButton("Salir");
        bOpciones.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menuOpciones.setVisible(true);
                menuPrincipal.setVisible(false);

            }
            
        });
        bSalir.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.dispose();
                menuOpciones.dispose();
            }
            
        });
        JPanel botoneraCentral = new JPanel();
        botoneraCentral.add(bJugar);
        botoneraCentral.add(bOpciones);
        botoneraCentral.add(bSalir);

        menuPrincipal.add(botoneraCentral, BorderLayout.CENTER);
        menuPrincipal.setVisible(true);


    }


    public static void initOpciones() {
        menuOpciones = new JFrame( "Ajustes" );
        menuOpciones.setSize(ANCHURA, ALTRUA);
        menuOpciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton bVolver = new JButton("Atrás");
        bVolver.addActionListener( new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.setVisible(true);
                menuOpciones.setVisible(false);
            }

        });
        menuOpciones.add(bVolver, BorderLayout.SOUTH);
        menuOpciones.setVisible(false);
    }
    
    
}