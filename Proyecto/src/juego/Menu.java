package juego;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;


public class Menu {

    private static JFrame menuPrincipal;
    private static JFrame menuOpciones;
    private static int ANCHURA = 800;
    private static int ALTURA = 600;
    private static String[] arrayResoluciones = { "1920x1080", "1280x720", "800x600" };

    public static void main(String[] args) {

        initMenuPrincipal();
        initOpciones();

    }

    /**
     * Metodo para inicializar el menu principal del juego
     */
    public static void initMenuPrincipal() {

        menuPrincipal = new JFrame("Menu principal");
        menuPrincipal.setSize(ANCHURA, ALTURA);
        menuPrincipal.setResizable(false);
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
        bSalir.addActionListener(new ActionListener() {

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

    /**
     * Metodo para inicializae el menu de ajustes, se inicia a la vez que el menu principal, pero no es visible
     */
    public static void initOpciones() {

        menuOpciones = new JFrame("Ajustes");
        menuOpciones.setSize(ANCHURA, ALTURA);
        menuOpciones.setResizable(false);
        menuOpciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuOpciones.getContentPane().setLayout( new BoxLayout( menuOpciones.getContentPane(), BoxLayout.Y_AXIS ) );

        JPanel panelResolucion =  new JPanel();
        JLabel labelResolucion = new JLabel("Resolución:");
        JComboBox boxResolucion = new JComboBox<>(arrayResoluciones); // Caja para elegir la resolución del juego - NO AFECTA A LOS MENÚS!!
        panelResolucion.add(labelResolucion);
        panelResolucion.add(boxResolucion);
        menuOpciones.getContentPane().add(panelResolucion);
        

        JButton bVolver = new JButton( "Atrás" );
        bVolver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.setVisible(true);
                menuOpciones.setVisible(false);
            }

        });
        bVolver.setAlignmentX(Component.CENTER_ALIGNMENT);


        menuOpciones.getContentPane().add(bVolver);
        menuOpciones.setVisible(false); // Se hace visible desde el menu principal
    }

}