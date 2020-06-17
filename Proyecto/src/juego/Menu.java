package juego;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import java.awt.Component;

public class Menu {

    public static JFrame menuPrincipal;
    public static JFrame menuOpciones;
    public static JFrame menuPuntuaciones;
    private static int ANCHURA = 800;
    private static int ALTURA = 600;
    private static Hashtable<String, int[]> tablaResoluciones = new Hashtable<String, int[]>();
    private static String[] arrayResoluciones = { "2560x1440", "1920x1080", "1280x720", "896x504" };
    private static String resolucionElegida = "1920x1080";

    //TODO por ahora no puedo cerrar todas las ventanas e hilos activos pulsando el boton "X" en ninguna de ellas
    public static void main(String[] args) {

        insertarResoluciones();
        initMenuPrincipal();
        initOpciones();
        initPuntuaciones();


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
        JButton bPuntuaciones = new JButton("Puntuaciones");
        JButton bOpciones = new JButton("Ajutes");
        JButton bSalir = new JButton("Salir");
        bOpciones.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                menuOpciones.setVisible(true);
                menuPrincipal.setVisible(false);

            }

        });

        bPuntuaciones.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                menuPuntuaciones.setVisible(true);
                menuPrincipal.setVisible(false);
            }

        });

        bSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.dispose();
                menuOpciones.dispose();
                menuPuntuaciones.dispose();
            }

        });

        bJugar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Partida.setResolucion(tablaResoluciones.get(resolucionElegida)[0],
                    tablaResoluciones.get(resolucionElegida)[1]);
                menuPrincipal.setVisible(false);
                Partida.iniciarPartida();
                Partida.activar();

            }
            
        });

        JPanel botoneraCentral = new JPanel();
        botoneraCentral.add(bJugar);
        botoneraCentral.add(bOpciones);
        botoneraCentral.add(bSalir);
        botoneraCentral.add(bPuntuaciones);

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
        JCheckBox checkDebug = new JCheckBox("Modo Debug"); // CheckBox para activar o desactivar el modo Debug, explicado en paintComponent de Partida.java
        panelDebug.add(checkDebug);
        menuOpciones.getContentPane().add(panelDebug);

        JPanel panelUsuario = new JPanel();
        JLabel labelUsuario = new JLabel("Nombre de Jugador:");
        JTextField fieldUsuario = new JTextField(15);
        panelUsuario.add(labelUsuario);
        panelUsuario.add(fieldUsuario);
        menuOpciones.getContentPane().add(panelUsuario);
        

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

                //Aplicar usuario
                Partida.setUsuario(fieldUsuario.getText());
            }

        });
        bAplicar.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuOpciones.getContentPane().add(bAplicar);
        menuOpciones.setVisible(false); // Se hace visible desde el menu principal
    }

    /**
     * Metodo para iniciar la lista de puntuaciones, con las puntuaciones de cada partida
     */
    public static void initPuntuaciones(){

        menuPuntuaciones = new JFrame("Puntuaciones");
        menuPuntuaciones.setSize(ANCHURA, ALTURA);
        menuPuntuaciones.setResizable(false);
        menuPuntuaciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextPane infoPuntuaciones =  new JTextPane();
        StyledDocument doc = infoPuntuaciones.getStyledDocument();
        ArrayList<Puntuacion> scores;
        // Las puntuaciones se actualizan cuando se reinicia el programa
        try {
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream("scores.dat"));
            scores = (ArrayList<Puntuacion>) ois.readObject();
            ois.close();

            try {
                for (Puntuacion p : scores){
                    doc.insertString(doc.getLength(), p.toString() + "\n", null);
                }
            } catch (BadLocationException ble) {
                System.out.println("No se pudo insertar el texto");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se pudo leer el archivo!");
        }



        menuPuntuaciones.getContentPane().add(infoPuntuaciones, BorderLayout.CENTER);
        JPanel botoneraInferior =  new JPanel();
        JButton bVolver = new JButton("Volver"); // Del menu de puntuaciones solo se puede volver al menu principal
        bVolver.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.setVisible(true);
                menuPuntuaciones.setVisible(false);
            }

        });
        botoneraInferior.add(bVolver);
        menuPuntuaciones.getContentPane().add(botoneraInferior, BorderLayout.SOUTH);








    }

}