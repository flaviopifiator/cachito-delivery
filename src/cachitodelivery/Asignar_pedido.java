/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;
import Ventana_clases.Fondo_asignar;
import Ventana_clases.Fondo_modificar_usuario;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelos.Cadena;
import modelos.PedidoDAO;
import modelos.Usuario;
import modelos.UsuarioDAO;

/**
 *
 * @author Gabe50
 */
public class Asignar_pedido extends javax.swing.JFrame {

    Object[][] pedidos;
    Object[][] mochila = new Object[0][0];
    
    public Asignar_pedido() {
        initComponents();
        Fondo_asignar fondo = new Fondo_asignar(1161,600);
        this.add(fondo, BorderLayout.CENTER);
    }
    
    public void mostrar(boolean b){
        setResizable(false);
        setSize(1161,620);
        setLocationRelativeTo(null);
        setTitle("Asignar pedido");
        setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());    
        setVisible(b);
    }
    public void pedidos() throws DataAccessException{

        PedidoDAO ped = new PedidoDAO();

        pedidos = ped.pedidosListos();

    }
    
    public void iniciarListado(){

        jTable1.setTableHeader(null);
        jTable2.setTableHeader(null);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    pedidos,new String[] {"","","",""}));

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(90);

    }
    public void iniciarMochila(){

        jTable1.setTableHeader(null);
        jTable2.setTableHeader(null);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    mochila,new String[] {"","","",""}));

        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(90);

    }
    
    public void agregar(){
        if (jTable1.getSelectedRow()==-1){
            
        }
        if (jTable1.getValueAt(jTable1.getSelectedRow(),0)==null){

        }else{
            int i = jTable1.getSelectedRow();
            
            if(mochila.length==0){
                Object[][] primero = new Object[1][4];
                primero[0][0] = pedidos[i][0];
                primero[0][1] = pedidos[i][1];
                primero[0][2] = pedidos[i][2];
                primero[0][3] = pedidos[i][3];
                mochila=new Object[1][4];
                mochila=primero;
            }else{
                int pos = mochila.length;
                Object[][] primero = new Object[pos+1][4];
                for (int j = 0; j < mochila.length; j++) {
                    primero[j][0] = mochila[j][0];
                    primero[j][1] = mochila[j][1];
                    primero[j][2] = mochila[j][2];
                    primero[j][3] = mochila[j][3];
                }
                primero[pos][0] = pedidos[i][0];
                primero[pos][1] = pedidos[i][1];
                primero[pos][2] = pedidos[i][2];
                primero[pos][3] = pedidos[i][3];
                mochila = new Object[pos+1][4];
                mochila=primero;
            }
            
            Object[][] menor = new Object[pedidos.length-1][4];
            int k=0;
            for (int j = 0; j < pedidos.length; j++) {
                if(j!=i){
                    menor[k][0] = pedidos[j][0];
                    menor[k][1] = pedidos[j][1];
                    menor[k][2] = pedidos[j][2];
                    menor[k][3] = pedidos[j][3];
                    k++;
                }
            }
            pedidos = new Object[menor.length][4];
            pedidos = menor;
            iniciarListado();
            iniciarMochila();
        }
    }
    public void quitar(){
        if (jTable2.getSelectedRow()==-1){
            
        }
        if (jTable2.getValueAt(jTable2.getSelectedRow(),0)==null){

        }else{
            int i = jTable2.getSelectedRow();
            
            if(pedidos.length==0){
                Object[][] primero = new Object[1][4];
                primero[0][0] = mochila[i][0];
                primero[0][1] = mochila[i][1];
                primero[0][2] = mochila[i][2];
                primero[0][3] = mochila[i][3];
                pedidos=new Object[1][4];
                pedidos=primero;
            }else{
                int pos = pedidos.length;
                Object[][] primero = new Object[pos+1][4];
                for (int j = 0; j < pedidos.length; j++) {
                    primero[j][0] = pedidos[j][0];
                    primero[j][1] = pedidos[j][1];
                    primero[j][2] = pedidos[j][2];
                    primero[j][3] = pedidos[j][3];
                }
                primero[pos][0] = mochila[i][0];
                primero[pos][1] = mochila[i][1];
                primero[pos][2] = mochila[i][2];
                primero[pos][3] = mochila[i][3];
                pedidos = new Object[pos+1][4];
                pedidos=primero;
            }
            
            Object[][] menor = new Object[mochila.length-1][4];
            int k=0;
            for (int j = 0; j < mochila.length; j++) {
                if(j!=i){
                    menor[k][0] = mochila[j][0];
                    menor[k][1] = mochila[j][1];
                    menor[k][2] = mochila[j][2];
                    menor[k][3] = mochila[j][3];
                    k++;
                }
            }
            mochila = new Object[menor.length][4];
            mochila = menor;
            iniciarListado();
            iniciarMochila();
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JL_Usuario_admin1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        JL_Fecha_Admin1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        JL_Hora_Admin1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JL_Usuario_admin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Usuario_admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Usuario_admin1.setText("USUARIO: ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        try
        {
            pedidos();
            iniciarListado();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Agregar_caja.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Agregar_caja_hover.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar2.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar2_hover.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        JL_Fecha_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha_Admin1.setText("FF/FF/FFFF");

        JL_Hora_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora_Admin1.setText("HH:HH");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Atras2.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Atras2_hover.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jButton3)))))
                .addGap(0, 254, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(JL_Fecha_Admin1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JL_Hora_Admin1)))
                        .addGap(0, 259, Short.MAX_VALUE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)
                        .addGap(121, 121, 121))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Hora_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_Fecha_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(187, 187, 187))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        quitar();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Asignar_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asignar_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asignar_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asignar_pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asignar_pedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_Fecha_Admin1;
    private javax.swing.JLabel JL_Hora_Admin1;
    private javax.swing.JLabel JL_Usuario_admin1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
