/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulador;

import Fisica.Movimiento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSimular extends JPanel {

    private JTextField txtX0, txtV0, txtA, txtT, txtMasa;
    private JButton btnPosicion, btnVelocidad, btnEnergia, btnAnimar;
    private JLabel lblResultado, lblTipoMovimiento;
    private JPanel panelAnimacion;
    private Timer timer;
    private double tiempoActual;
    private double tFinal;
    private Movimiento movimiento;
    private int xPixel;

    public JSimular() {
        setPreferredSize(new Dimension(350, 330));
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        JLabel lblX0 = new JLabel("x0:"); lblX0.setBounds(10, 20, 40, 25); add(lblX0);
        JLabel lblV0 = new JLabel("v0:"); lblV0.setBounds(10, 50, 40, 25); add(lblV0);
        JLabel lblA  = new JLabel("a:");  lblA.setBounds(10, 80, 40, 25); add(lblA);
        JLabel lblT  = new JLabel("t:");  lblT.setBounds(10, 110, 40, 25); add(lblT);
        JLabel lblMasa = new JLabel("masa:"); lblMasa.setBounds(10, 140, 40, 25); add(lblMasa);

        txtX0 = new JTextField(); txtX0.setBounds(60, 20, 80, 25); add(txtX0);
        txtV0 = new JTextField(); txtV0.setBounds(60, 50, 80, 25); add(txtV0);
        txtA  = new JTextField(); txtA.setBounds(60, 80, 80, 25); add(txtA);
        txtT  = new JTextField(); txtT.setBounds(60, 110, 80, 25); add(txtT);
        txtMasa = new JTextField(); txtMasa.setBounds(60, 140, 80, 25); add(txtMasa);

        btnPosicion = new JButton("Calcular posición"); btnPosicion.setBounds(160, 20, 160, 25); add(btnPosicion);
        btnVelocidad = new JButton("Calcular velocidad"); btnVelocidad.setBounds(160, 50, 160, 25); add(btnVelocidad);
        btnEnergia = new JButton("Calcular energía"); btnEnergia.setBounds(160, 80, 160, 25); add(btnEnergia);
        btnAnimar = new JButton("Iniciar animación"); btnAnimar.setBounds(160, 110, 160, 25); add(btnAnimar);

        lblResultado = new JLabel("Resultado:"); lblResultado.setBounds(10, 180, 310, 25); add(lblResultado);
        lblTipoMovimiento = new JLabel("Tipo de movimiento:"); lblTipoMovimiento.setBounds(10, 210, 310, 25); add(lblTipoMovimiento);

        panelAnimacion = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillOval(xPixel, 20, 20, 20);
            }
        };
        panelAnimacion.setBounds(10, 250, 320, 60);
        panelAnimacion.setBackground(Color.LIGHT_GRAY);
        add(panelAnimacion);

        btnPosicion.addActionListener(e -> calcularPosicion());
        btnVelocidad.addActionListener(e -> calcularVelocidad());
        btnEnergia.addActionListener(e -> calcularEnergia());
        btnAnimar.addActionListener(e -> iniciarAnimacion());
    }

    private void calcularPosicion() {
        try {
            movimiento = construirMovimiento();
            double t = Double.parseDouble(txtT.getText());
            lblResultado.setText("Posición: " + movimiento.posicion(t));
            actualizarTipoMovimiento(movimiento);
        } catch (Exception ex) {
            lblResultado.setText("Error en los datos");
            lblTipoMovimiento.setText("Tipo de movimiento: error");
        }
    }

    private void calcularVelocidad() {
        try {
            movimiento = construirMovimiento();
            double t = Double.parseDouble(txtT.getText());
            lblResultado.setText("Velocidad: " + movimiento.velocidad(t));
            actualizarTipoMovimiento(movimiento);
        } catch (Exception ex) {
            lblResultado.setText("Error en los datos");
            lblTipoMovimiento.setText("Tipo de movimiento: error");
        }
    }

    private void calcularEnergia() {
        try {
            movimiento = construirMovimiento();
            double t = Double.parseDouble(txtT.getText());
            double masa = Double.parseDouble(txtMasa.getText());
            lblResultado.setText("Energía cinética: " + movimiento.energiaCinetica(masa, t));
            actualizarTipoMovimiento(movimiento);
        } catch (Exception ex) {
            lblResultado.setText("Error en los datos");
            lblTipoMovimiento.setText("Tipo de movimiento: error");
        }
    }

    private void iniciarAnimacion() {
        try {
            movimiento = construirMovimiento();
            tFinal = Double.parseDouble(txtT.getText());
            tiempoActual = 0;
            xPixel = 0;

            actualizarTipoMovimiento(movimiento);

            if (timer != null && timer.isRunning()) {
                timer.stop();
            }

            timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double x = movimiento.posicion(tiempoActual);
                    xPixel = (int) (x * 10);
                    if (xPixel > panelAnimacion.getWidth() - 20) {
                        xPixel = panelAnimacion.getWidth() - 20;
                    }
                    panelAnimacion.repaint();
                    tiempoActual += 0.1;

                    if (tiempoActual > tFinal) {
                        timer.stop();
                    }
                }
            });

            timer.start();
        } catch (Exception ex) {
            lblResultado.setText("Error al iniciar animación");
            lblTipoMovimiento.setText("Tipo de movimiento: error");
        }
    }

    private void actualizarTipoMovimiento(Movimiento m) {
        if (!m.esMovimientoValido()) {
            lblTipoMovimiento.setText("Tipo de movimiento: Ninguno");
        } else if (m.esMRU()) {
            lblTipoMovimiento.setText("Tipo de movimiento: MRU");
        } else if (m.esMRUA()) {
            lblTipoMovimiento.setText("Tipo de movimiento: MRUA");
        } else {
            lblTipoMovimiento.setText("Tipo de movimiento: Desconocido");
        }
    }

    private Movimiento construirMovimiento() {
        double x0 = Double.parseDouble(txtX0.getText());
        double v0 = Double.parseDouble(txtV0.getText());
        double a = Double.parseDouble(txtA.getText());
        return new Movimiento(x0, v0, a);
    }
}


