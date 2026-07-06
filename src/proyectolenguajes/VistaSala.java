/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectolenguajes;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class VistaSala extends javax.swing.JFrame {

    //OBJETOS
    private SalaCine sala;
    private JPanel panelLetras;
    private JPanel panelNumeros;
    private Butaca butacaSeleccionada;
    private JButton botonButacaSeleccionada;
    /**
     * Creates new form VistaSala
     */
    public VistaSala(SalaCine sala) {
        initComponents();
        
        setLocationRelativeTo(null);
        
        this.sala = sala;
        
        //CREACION DE LOS COMPONENTES DENTRO DE PanelAsientos
        panelLetras = new JPanel();
        panelNumeros = new JPanel();
        
        scrollAsientos.setRowHeaderView(panelLetras);
        scrollAsientos.setColumnHeaderView(panelNumeros);
        
        System.out.println("Sala recibida: " + sala.getNumero());
        System.out.println("Filas: " + sala.getFilas());
        System.out.println("Columnas: " + sala.getColumnas());

        generarAsientos();
        actualizarContadores();
    }
    
    //METODO PARA GENERAR ASIENTOS
    private void generarAsientos(){
        
        int filas = sala.getFilas();
        int columnas = sala.getColumnas();
        
        panelAsientos.removeAll();
        panelLetras.removeAll();
        panelNumeros.removeAll();
        
        panelAsientos.setLayout(new GridLayout(filas, columnas, 5, 5));
        panelLetras.setLayout(new GridLayout(filas, 1, 5, 5));
        panelNumeros.setLayout(new GridLayout (1, columnas, 5, 5));
        
        int anchoButaca = 55;
        int altoButaca = 35;
        
        //COLOCAR NUMEROS
        for(int columna=0; columna<columnas; columna++){
            JLabel lblNumero = new JLabel(String.valueOf(columna+1), SwingConstants.CENTER);
            lblNumero.setPreferredSize(new Dimension(anchoButaca, 25));
            panelNumeros.add(lblNumero);
        }
        
        //COLOCAR LETRAS Y BOTONES
        for (int fila = 0; fila < filas; fila++) {
            
            char letraFila = (char) ('A' + fila);

            JLabel lblLetra = new JLabel(String.valueOf(letraFila), SwingConstants.CENTER);
            lblLetra.setPreferredSize(new Dimension(35, altoButaca));
            panelLetras.add(lblLetra);
            
            //GENERACION DE BOTONES
            for (int columna = 0; columna < columnas; columna++) {
                Butaca butaca = sala.getButaca(fila, columna);

                JButton botonButaca = new JButton("R");
                botonButaca.setPreferredSize(new Dimension(anchoButaca, altoButaca));
                
                botonButaca.addActionListener(this::seleccionarButaca);

                botonButaca.putClientProperty("butaca", butaca);

                panelAsientos.add(botonButaca);
            }
        }
        
        //ACTUALIZAR PANELASIENTOS
        int anchoTotal = columnas * (anchoButaca + 5);
        int altoTotal = filas * (altoButaca + 5);
        panelAsientos.setPreferredSize(new Dimension(anchoTotal, altoTotal));
        panelNumeros.setPreferredSize(new Dimension(anchoTotal, 25));
        panelLetras.setPreferredSize(new Dimension(35, altoTotal));

        panelAsientos.revalidate();
        panelAsientos.repaint();

        panelLetras.revalidate();
        panelLetras.repaint();

        panelNumeros.revalidate();
        panelNumeros.repaint();
    }
    
    //EVENTO SELECCIONAR BUTACA
    private void seleccionarButaca(java.awt.event.ActionEvent e) {

        JButton botonSeleccionado = (JButton) e.getSource();
        
        butacaSeleccionada = (Butaca) botonSeleccionado.getClientProperty("butaca");
        botonButacaSeleccionada = botonSeleccionado;
        
        int fila = butacaSeleccionada.getFila();
        int columna = butacaSeleccionada.getColumna();
        
        char letraFila = (char) ('A' + fila);
        int numeroColumna = columna + 1;
        
        lblFila.setText("Fila: " + letraFila);
        lblColumna.setText("Columna: " + numeroColumna);
        lblButaca.setText("Butaca: " + letraFila + numeroColumna);
        lblEstado.setText("Estado: " + butacaSeleccionada.getEstado());
    }
    
    //ACTUALIZA EL LABEL DE ESTADO Y EL BOTON VISUAL DE LA BUTACA SELECCIONADA
    private void actualizarButacaSeleccionadaUI() {
        lblEstado.setText("Estado: " + butacaSeleccionada.getEstado());

        if (botonButacaSeleccionada == null) {
            return;
        }

        switch (butacaSeleccionada.getEstado()) {
            case LIBRE:
                botonButacaSeleccionada.setText("L");
                botonButacaSeleccionada.setBackground(null);
                break;
            case RESERVADA:
                botonButacaSeleccionada.setText("R");
                botonButacaSeleccionada.setBackground(java.awt.Color.YELLOW);
                break;
            case OCUPADA:
                botonButacaSeleccionada.setText("O");
                botonButacaSeleccionada.setBackground(java.awt.Color.decode("#FF6B6B"));
                break;
        }
    }
    
    //RECALCULA LIBRES / RESERVADAS / OCUPADAS / TOTAL RECORRIENDO LA SALA
    private void actualizarContadores() {
        int libres = 0, reservadas = 0, ocupadas = 0;

        int filas = sala.getFilas();
        int columnas = sala.getColumnas();

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                switch (sala.getButaca(fila, columna).getEstado()) {
                    case LIBRE:
                        libres++;
                        break;
                    case RESERVADA:
                        reservadas++;
                        break;
                    case OCUPADA:
                        ocupadas++;
                        break;
                }
            }
        }

        lblButacasLibres.setText("Libres: " + libres);
        lblButacasReservadas.setText("Reservadas: " + reservadas);
        lblButacasOcupadas.setText("Ocupadas: " + ocupadas);
        lblTotalButacas.setText("Total: " + (libres + reservadas + ocupadas));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollAsientos = new javax.swing.JScrollPane();
        panelAsientos = new javax.swing.JPanel();
        panelDerecho = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblFila = new javax.swing.JLabel();
        lblColumna = new javax.swing.JLabel();
        lblButaca = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jBCancelarReserva = new javax.swing.JButton();
        jBLiberarButaca = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblButacasLibres = new javax.swing.JLabel();
        lblButacasReservadas = new javax.swing.JLabel();
        lblButacasOcupadas = new javax.swing.JLabel();
        lblTotalButacas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelAsientos.setPreferredSize(new java.awt.Dimension(590, 492));

        javax.swing.GroupLayout panelAsientosLayout = new javax.swing.GroupLayout(panelAsientos);
        panelAsientos.setLayout(panelAsientosLayout);
        panelAsientosLayout.setHorizontalGroup(
            panelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        panelAsientosLayout.setVerticalGroup(
            panelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        scrollAsientos.setViewportView(panelAsientos);

        panelDerecho.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jPanel1.setLayout(new java.awt.GridLayout(5, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("BUTACA SELECCIONADA");
        jPanel1.add(jLabel1);

        lblFila.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFila.setText("Fila: -");
        jPanel1.add(lblFila);

        lblColumna.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblColumna.setText("Columna: -");
        jPanel1.add(lblColumna);

        lblButaca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblButaca.setText("Butaca: -");
        jPanel1.add(lblButaca);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEstado.setText("Estado: -");
        jPanel1.add(lblEstado);

        panelDerecho.add(jPanel1);

        jPanel3.setLayout(new java.awt.GridLayout(4, 0, 0, 10));

        jButton1.setText("jButton1");
        jPanel3.add(jButton1);

        jButton2.setText("jButton2");
        jPanel3.add(jButton2);

        jBCancelarReserva.setText("Cancelar Reserva");
        jBCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarReservaActionPerformed(evt);
            }
        });
        jPanel3.add(jBCancelarReserva);

        jBLiberarButaca.setText("Liberar Butaca");
        jBLiberarButaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLiberarButacaActionPerformed(evt);
            }
        });
        jPanel3.add(jBLiberarButaca);

        panelDerecho.add(jPanel3);

        jPanel2.setLayout(new java.awt.GridLayout(4, 0, 0, 10));

        lblButacasLibres.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblButacasLibres.setText("Libres: -");
        jPanel2.add(lblButacasLibres);

        lblButacasReservadas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblButacasReservadas.setText("Reservadas: -");
        jPanel2.add(lblButacasReservadas);

        lblButacasOcupadas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblButacasOcupadas.setText("Ocupadas: -");
        jPanel2.add(lblButacasOcupadas);

        lblTotalButacas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotalButacas.setText("Total: -");
        jPanel2.add(lblTotalButacas);

        panelDerecho.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollAsientos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDerecho, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollAsientos)
                    .addComponent(panelDerecho, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //EVENTO CANCELAR RESERVA (RESERVADA -> LIBRE)
    private void jBCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarReservaActionPerformed

        if (butacaSeleccionada == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una butaca primero.");
            return;
        }

        boolean exito = butacaSeleccionada.cancelarReserva();

        if (exito) {
            actualizarButacaSeleccionadaUI();
            actualizarContadores();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "La butaca no está reservada, no se puede cancelar la reserva.");
        }
    }//GEN-LAST:event_jBCancelarReservaActionPerformed

    //EVENTO LIBERAR BUTACA (OCUPADA -> LIBRE)
    private void jBLiberarButacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLiberarButacaActionPerformed

        if (butacaSeleccionada == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una butaca primero.");
            return;
        }

        boolean exito = butacaSeleccionada.liberar();

        if (exito) {
            actualizarButacaSeleccionadaUI();
            actualizarContadores();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "La butaca no está ocupada, no se puede liberar.");
        }
    }//GEN-LAST:event_jBLiberarButacaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancelarReserva;
    private javax.swing.JButton jBLiberarButaca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblButaca;
    private javax.swing.JLabel lblButacasLibres;
    private javax.swing.JLabel lblButacasOcupadas;
    private javax.swing.JLabel lblButacasReservadas;
    private javax.swing.JLabel lblColumna;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFila;
    private javax.swing.JLabel lblTotalButacas;
    private javax.swing.JPanel panelAsientos;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JScrollPane scrollAsientos;
    // End of variables declaration//GEN-END:variables
}
