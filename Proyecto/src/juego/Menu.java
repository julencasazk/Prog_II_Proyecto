package juego;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class Menu {

    public static JFrame menuPrincipal;
    public static JFrame menuOpciones;
    private static int ANCHURA = 800;
    private static int ALTURA = 600;
    private static Hashtable<String, int[]> tablaResoluciones = new Hashtable<String, int[]>();
    private static String[] arrayResoluciones = { "2560x1440", "1920x1080", "1280x720", "896x504" };
    private static String resolucionElegida = "1920x1080";
    private static boolean iniciar = false;

    // TODO Poder cerrar AMBOS menus y finalizar el programa con un solo click a la "X"
    public static void main(String[] args) {

        insertarResoluciones();
        initMenuPrincipal();
        initOpciones();


    }

    public static void insertarResoluciones() {
        tablaResoluciones.put("2560x1440", new int[] { 2560, 1440 });
        tablaResoluciones.put("1920x1080", new int[] { 1920, 1080 });
        tablaResoluciones.put("1280x720", new int[] { 1280, 720 });
        tablaResoluciones.put("896x504", new int[] { 896, 504 });
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

        bJugar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Partida.setResolucion(tablaResoluciones.get(resolucionElegida)[0],
                    tablaResoluciones.get(resolucionElegida)[1]);
                menuPrincipal.setVisible(false);
                Partida.iniciarPartida();
                iniciar = true;
                Partida.activar();

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

        menuOpciones = new JFrame( "Ajustes" );
        menuOpciones.setSize(ANCHURA, ALTURA);
        menuOpciones.setResizable(false);
        menuOpciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuOpciones.getContentPane().setLayout( new BoxLayout( menuOpciones.getContentPane(), BoxLayout.Y_AXIS ) );

        JPanel panelResolucion =  new JPanel();
        JLabel labelResolucion = new JLabel( "Resolución:" );
        JComboBox boxResolucion = new JComboBox<>(arrayResoluciones); // Caja para elegir la resolución del juego - NO AFECTA A LOS MENÚS!
        panelResolucion.add(labelResolucion);
        panelResolucion.add(boxResolucion);
        menuOpciones.getContentPane().add(panelResolucion);

        JPanel panelDebug = new JPanel();
        JCheckBox checkDebug = new JCheckBox("Modo Debug");
        panelDebug.add(checkDebug);
        menuOpciones.getContentPane().add(panelDebug);
        

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

        JButton bAplicar = new JButton( "Aplicar Ajustes" );
        bAplicar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Aplicar resolución
                resolucionElegida = (String) boxResolucion.getSelectedItem();
                System.out.println(resolucionElegida);

                //Aplicar modoDebug
                Partida.setModoDebug(checkDebug.isSelected());
            }

        });
        bAplicar.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuOpciones.getContentPane().add(bAplicar);
        menuOpciones.setVisible(false); // Se hace visible desde el menu principal
    }

}