
package proyectolenguajes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;

public class Salas extends javax.swing.JFrame {
    
    private int contadorSalas = 0;
    private ArrayList<SalaCine> listaSalas = new ArrayList<>();

    /**
     * Creates new form Salas
     */
    public Salas() {
        initComponents();
        
        //DISEÑO DE LA VENTANA
        setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(btnAgregarSala);

        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout(10, 10));

        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        getContentPane().add(scrollSalas, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregarSala = new javax.swing.JButton();
        scrollSalas = new javax.swing.JScrollPane();
        panelSalas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregarSala.setText("Agregar sala");
        btnAgregarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSalaActionPerformed(evt);
            }
        });

        scrollSalas.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelSalas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelSalas.setLayout(new javax.swing.BoxLayout(panelSalas, javax.swing.BoxLayout.Y_AXIS));
        scrollSalas.setViewportView(panelSalas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarSala)
                .addContainerGap(973, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollSalas, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarSala)
                .addContainerGap(534, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(44, Short.MAX_VALUE)
                    .addComponent(scrollSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSalaActionPerformed
        // DISEÑO DEL PANEL PARA AÑADIR SALA
        JPanel panelDatos = new JPanel();
        
        panelDatos.setLayout(new GridLayout(2, 2, 10, 10));
        
        JTextField txtFilas = new JTextField();
        JTextField txtColumnas = new JTextField();
        
        panelDatos.add(new JLabel("Cantidad de filas:"));
        panelDatos.add(txtFilas);

        panelDatos.add(new JLabel("Cantidad de columnas:"));
        panelDatos.add(txtColumnas);
        
        int respuesta = JOptionPane.showConfirmDialog(
        this,
        panelDatos,
        "Agregar sala",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE       
        );
        
        //VALIDACION DE DATOS
        
        if (respuesta == JOptionPane.OK_OPTION) {
            
            if (txtFilas.getText().isEmpty() || txtColumnas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Debe completar las filas y columnas."
            );
            return;
            }
            
            String textoFilas = txtFilas.getText().trim();
            String textoColumnas = txtColumnas.getText().trim();
            
            int filas;
            int columnas;
            
            try {
                filas = Integer.parseInt(textoFilas);
                columnas = Integer.parseInt(textoColumnas);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Solo debe ingresar números enteros."
                );
                return;
            }
            
            if (filas <= 0 || columnas <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Las filas y columnas deben ser mayores que cero."
                );
                return;
            } else if (filas > 26 || columnas > 26){
                JOptionPane.showMessageDialog(this, "El maximo de filas y columnas es 27");
                return;
            }
            
            contadorSalas++;
            
            //CREACION DEL OBJETO SalaCine
            
            SalaCine nuevaSala = new SalaCine(
                contadorSalas,
                filas,
                columnas
            );

            listaSalas.add(nuevaSala);

            JButton botonSala = new JButton(
                "Sala " + nuevaSala.getNumero()
            );
            
            //CREACION DEL BOTON SALA CON SUS PROPIEDADES
            
            botonSala.putClientProperty("sala", nuevaSala);
            botonSala.addActionListener(this::seleccionarSala);
            botonSala.setMinimumSize(
                new Dimension(100, 60)
            );

            botonSala.setPreferredSize(
                new Dimension(300, 60)
            );

            botonSala.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 60)
            );
            
            panelSalas.add(botonSala);
            panelSalas.revalidate();
            panelSalas.repaint();
        }
    }//GEN-LAST:event_btnAgregarSalaActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarSala;
    private javax.swing.JPanel panelSalas;
    private javax.swing.JScrollPane scrollSalas;
    // End of variables declaration//GEN-END:variables

    //METODO DE CADA BOTON SALA AL PULSAR
    private void seleccionarSala(ActionEvent e) {
        JButton botonSeleccionado = (JButton) e.getSource();

        SalaCine salaSeleccionada =
        (SalaCine) botonSeleccionado.getClientProperty("sala");
        
        VistaSala ventana = new VistaSala(salaSeleccionada);
        ventana.setVisible(true);
        this.dispose();

    }
}
