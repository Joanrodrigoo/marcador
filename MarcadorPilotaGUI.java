package codigo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcadorPilotaGUI {
    private int jocsEquipo1 = 0; // Jocs ganados por el equipo 1
    private int jocsEquipo2 = 0; // Jocs ganados por el equipo 2
    private int tantosEquipo1 = 0; // Tantos actuales del equipo 1
    private int tantosEquipo2 = 0; // Tantos actuales del equipo 2
    private final String[][] matriz = new String[6][2];
    private int ultimoPuntoEquipo1 = 0; // Últim punto de Equipo 1
    private int ultimoPuntoEquipo2 = 0; // Últim punto de Equipo 2


  

    // Componentes principales
    private JFrame frame;
    private JLabel marcadorJocsEquipo1;
    private JLabel marcadorJocsEquipo2;
    private JLabel marcadorTantosEquipo1;
    private JLabel marcadorTantosEquipo2;
    private JButton botonEquipo1;
    private JButton botonEquipo2;
    private JButton resetButton;
    private JButton deshacerPuntoButton;


    public MarcadorPilotaGUI() {
        // Crear ventana principal
        frame = new JFrame("Marcador de Pilota Valenciana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout(10, 10));

        // Crear panel para juegos
        JPanel panelJocs = new JPanel(new GridLayout(1, 2, 10, 10));
        marcadorJocsEquipo1 = new JLabel("0", SwingConstants.CENTER);
        marcadorJocsEquipo2 = new JLabel("0", SwingConstants.CENTER);
        

        // Estilo de los marcadores de juegos
        marcadorJocsEquipo1.setFont(new Font("Arial", Font.BOLD, 24));
        marcadorJocsEquipo1.setForeground(Color.RED);
        marcadorJocsEquipo2.setFont(new Font("Arial", Font.BOLD, 24));
        marcadorJocsEquipo2.setForeground(Color.BLUE);

        panelJocs.add(marcadorJocsEquipo1);
        panelJocs.add(marcadorJocsEquipo2);

        // Crear panel para tantos
        JPanel panelTantos = new JPanel(new GridLayout(1, 2, 10, 10));
        marcadorTantosEquipo1 = new JLabel(matriz[tantosEquipo1][0], SwingConstants.CENTER);
        marcadorTantosEquipo2 = new JLabel(matriz[tantosEquipo2][0], SwingConstants.CENTER);

        // Estilo de los marcadores de tantos
        marcadorTantosEquipo1.setFont(new Font("Arial", Font.BOLD, 36));
        marcadorTantosEquipo2.setFont(new Font("Arial", Font.BOLD, 36));

        panelTantos.add(marcadorTantosEquipo1);
        panelTantos.add(marcadorTantosEquipo2);

        // Crear panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        botonEquipo1 = new JButton("Tanto Equipo 1");
        botonEquipo2 = new JButton("Tanto Equipo 2");
        deshacerPuntoButton = new JButton("Deshacer Último Punto");
        resetButton = new JButton("Reiniciar Marcador");

        // Añadir acciones a los botones
        botonEquipo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarPunto(1);
            }
        });

        botonEquipo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarPunto(2);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarMarcador();
            }
        });
        
        deshacerPuntoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshacerPunto();
            }
        });

        panelBotones.add(botonEquipo1);
        panelBotones.add(botonEquipo2);
        panelBotones.add(resetButton);
        panelBotones.add(deshacerPuntoButton);

        // Añadir paneles al frame
        frame.add(panelJocs, BorderLayout.NORTH);  // Juegos arriba
        frame.add(panelTantos, BorderLayout.CENTER); // Tantos en el medio
        frame.add(panelBotones, BorderLayout.SOUTH); // Botones abajo

        // Mostrar ventana
        frame.setVisible(true);
    }

    private void manejarPunto(int equipo) {
    	
    	// Guardem el punt anterior abans de modificar los tantos
        ultimoPuntoEquipo1 = tantosEquipo1;
        ultimoPuntoEquipo2 = tantosEquipo2;
    	
        // Configuración inicial de la matriz
        matriz[0][0] = "00";
        matriz[0][1] = "00";
        matriz[1][0] = "15";
        matriz[1][1] = "15";
        matriz[2][0] = "30";
        matriz[2][1] = "30";
        matriz[3][0] = "Val";
        matriz[3][1] = "30";
        matriz[4][0] = "30";
        matriz[4][1] = "Val";
        matriz[5][0] = "00";
        matriz[5][1] = "00";

        if (equipo == 1) {
            // Equipo 1 (rojo) anota
            if (tantosEquipo1 == 3) { 
                // Si el equipo rojo está en "Val" y el azul está en "30", gana el juego
                if (tantosEquipo2 == 2) {
                    ganarJuego(1);
                } else {
                    // Gana el juego directamente
                    ganarJuego(1);
                }
            } else if (tantosEquipo1 == 2 && tantosEquipo2 == 3) {
                // Si el equipo azul tiene "Val", lo baja a "30"
                tantosEquipo2 = 2;
                tantosEquipo1 = 2; // Ambos quedan en "30"
            } else {
                tantosEquipo1++;
            }
        } else if (equipo == 2) {
            // Equipo 2 (azul) anota
            if (tantosEquipo2 == 3) { 
                // Si el equipo azul está en "Val" y el rojo está en "30", gana el juego
                if (tantosEquipo1 == 2) {
                    ganarJuego(2);
                } else {
                    // Gana el juego directamente
                    ganarJuego(2);
                }
            } else if (tantosEquipo2 == 2 && tantosEquipo1 == 3) {
                // Si el equipo rojo tiene "Val", lo baja a "30"
                tantosEquipo1 = 2;
                tantosEquipo2 = 2; // Ambos quedan en "30"
            } else {
                tantosEquipo2++;
            }
        }

        // Actualizar el marcador
        actualizarMarcador();
    }

    private void ganarJuego(int equipo) {
        if (equipo == 1) {
            jocsEquipo1 += 5; // Els jocs sumen 5 punts
        } else if (equipo == 2) {
            jocsEquipo2 += 5; // Els jocs sumen 5 punts
        }
        reiniciarTantos(); // Reiniciem els punts dels "tantos"
        actualizarMarcador(); // Actualitzem el marcador
    }
    private void actualizarMarcador() {
        // Actualizar tantos para el equipo 1 (rojo)
        marcadorTantosEquipo1.setText(tantosEquipo1 == 3 ? "Val" : matriz[tantosEquipo1][0]);
        
        // Actualizar tantos para el equipo 2 (azul)
        marcadorTantosEquipo2.setText(tantosEquipo2 == 3 ? "Val" : matriz[tantosEquipo2][1]);

        // Actualizar jocs
        marcadorJocsEquipo1.setText(String.valueOf(jocsEquipo1));
        marcadorJocsEquipo2.setText(String.valueOf(jocsEquipo2));
    }
    
//    private void igualarA30() {
//        tantosEquipo1 = 2;
//        tantosEquipo2 = 2;
//    }

    private void reiniciarTantos() {
        tantosEquipo1 = 0;
        tantosEquipo2 = 0;
    }
    private void deshacerPunto() {
        // Restaurar los tantos al último valor guardado
        tantosEquipo1 = ultimoPuntoEquipo1;
        tantosEquipo2 = ultimoPuntoEquipo2;

        // Actualizar el marcador después de deshacer el punto
        actualizarMarcador();
    }

    private void reiniciarMarcador() {
        tantosEquipo1 = 0;
        tantosEquipo2 = 0;
        jocsEquipo1 = 0;
        jocsEquipo2 = 0;
        actualizarMarcador();
    }

    public static void main(String[] args) {
        new MarcadorPilotaGUI();
    }
}
