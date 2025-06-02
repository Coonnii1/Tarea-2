package frontend;

import backend.BloomLevel;
import backend.Item;
import backend.MultipleChoiceItem;
import backend.TestManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import backend.ItemFileReader;

public class MainFrame extends JFrame implements PropertyChangeListener {

    private TestManager testManager = new TestManager();
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel inicioPanel;
    private JPanel pruebaPanel;
    private JPanel resultadosPanel;
    private JButton cargarArchivoButton;
    private JLabel infoPruebaLabel;
    private JButton iniciarPruebaButton;
    private JTextArea preguntaTextArea;
    private JPanel opcionesPanel;
    private JButton volverButton;
    private JButton avanzarButton;
    private JLabel preguntaNumeroLabel;
    private JTextArea resumenResultadosTextArea;
    private JButton revisarRespuestasButton;
    private JButton volverAlResumenButton;
    private JScrollPane preguntaScrollPane;

    private List<Item> items; // Almacena los ítems cargados
    private String selectedFilePath; // Almacena la ruta del archivo seleccionado

    public MainFrame() {
        setTitle("Sistema de Administración de Pruebas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        inicioPanel = createInicioPanel();
        pruebaPanel = createPruebaPanel();
        resultadosPanel = createResultadosPanel();

        mainPanel.add(inicioPanel, "inicio");
        mainPanel.add(pruebaPanel, "prueba");
        mainPanel.add(resultadosPanel, "resultados");

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

        testManager.addPropertyChangeListener(this); // Registrar el MainFrame como listener
    }

    private JPanel createInicioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Espacio entre componentes

        cargarArchivoButton = new JButton("Cargar Archivo de Ítems");
        cargarArchivoButton.addActionListener(e -> cargarArchivo());

        infoPruebaLabel = new JLabel("Seleccione un archivo para iniciar la prueba.");
        infoPruebaLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        iniciarPruebaButton = new JButton("Iniciar Prueba");
        iniciarPruebaButton.setEnabled(false); // Deshabilitado hasta que se cargue el archivo
        iniciarPruebaButton.addActionListener(e -> iniciarPrueba());

        panel.add(cargarArchivoButton);
        panel.add(infoPruebaLabel);
        panel.add(iniciarPruebaButton);

        return panel;
    }

    private JPanel createPruebaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        preguntaNumeroLabel = new JLabel();
        preguntaNumeroLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(preguntaNumeroLabel);
        panel.add(headerPanel, BorderLayout.NORTH);

        preguntaTextArea = new JTextArea();
        preguntaTextArea.setWrapStyleWord(true);
        preguntaTextArea.setLineWrap(true);
        preguntaTextArea.setEditable(false);
        preguntaTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

        preguntaScrollPane = new JScrollPane(preguntaTextArea);
        panel.add(preguntaScrollPane, BorderLayout.CENTER);


        opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new GridLayout(0, 1)); // 1 columna, filas dinámicas
        panel.add(opcionesPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        volverButton = new JButton("Volver Atrás");
        volverButton.addActionListener(e -> volverAtras());
        volverButton.setEnabled(false); // Deshabilitado al inicio

        avanzarButton = new JButton("Avanzar");
        avanzarButton.addActionListener(e -> avanzar());

        buttonPanel.add(volverButton);
        buttonPanel.add(avanzarButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createResultadosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        resumenResultadosTextArea = new JTextArea();
        resumenResultadosTextArea.setEditable(false);
        resumenResultadosTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(new JScrollPane(resumenResultadosTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        revisarRespuestasButton = new JButton("Revisar Respuestas");
        revisarRespuestasButton.addActionListener(e -> revisarRespuestas());

        volverAlResumenButton = new JButton("Volver al Resumen");
        volverAlResumenButton.addActionListener(e -> mostrarResumen());