package cachitodelivery;

import Excepciones.DataAccessException;
import Excepciones.UsuarioInexistenteException;
import Ventana_clases.Inicio_sesion;
import Ventana_clases.Menu_admin;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.GestorUsuario;
import modelos.Usuario;
/**
 *
 * @author Cusipuma
 */
public class Principal extends javax.swing.JFrame {

    
    
    public String Fecha(){
        Calendar fecha = new GregorianCalendar();
        
        String dia=Integer.toString(fecha.get(Calendar.DAY_OF_MONTH))
                ,mes=Integer.toString(fecha.get(Calendar.MONTH)+1)
                ,anio=Integer.toString(fecha.get(Calendar.YEAR));
        
        if(fecha.get(Calendar.DAY_OF_MONTH)<10)
            dia="0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
        
        if(fecha.get(Calendar.MONTH)+1<10)
            mes="0"+Integer.toString(fecha.get(Calendar.MONTH));
        
        return dia+"/"+mes+"/"+anio;
    }
    
    public String Hora(){
        Calendar fecha = new GregorianCalendar();
        
        String hora=Integer.toString(fecha.get(Calendar.HOUR))
                ,min=Integer.toString(fecha.get(Calendar.MINUTE));
        
        if (fecha.get(Calendar.MINUTE)<10)
            min="0"+Integer.toString(fecha.get(Calendar.MINUTE));
        if(Calendar.PM==1)
            hora=Integer.toString(fecha.get(Calendar.HOUR)+12);
        else
            if(fecha.get(Calendar.HOUR)<10)
                hora="0"+Integer.toString(fecha.get(Calendar.HOUR));

        
        return hora+":"+min;
    }
    
    
    public Principal() {
        initComponents();
        setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        Inicio_sesion fondo = new Inicio_sesion(399,600);
        this.add(fondo, BorderLayout.CENTER);
        
        this.JL_Fecha.setText(this.Fecha());
        this.JL_Hora.setText(this.Hora());
        
        //TESTEANDO OTRAS VENTANAS
        Principal.super.setVisible(false);
        Menu_admin fondo_prueba = new Menu_admin(673,260);
        JF_Menu_admin.add(fondo_prueba, BorderLayout.CENTER);
        JF_Menu_admin.setSize(673,299);
        JF_Menu_admin.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JF_Menu_admin = new javax.swing.JFrame();
        JB_Caja = new javax.swing.JButton();
        JB_Personal = new javax.swing.JButton();
        JB_Clientes = new javax.swing.JButton();
        JB_Pedidos = new javax.swing.JButton();
        JB_Estadisticas = new javax.swing.JButton();
        JB_Zonas = new javax.swing.JButton();
        JB_Comidas = new javax.swing.JButton();
        JB_Cerrar_sesion = new javax.swing.JButton();
        JB_Color_admin = new javax.swing.JButton();
        JL_Usuario_admin = new javax.swing.JLabel();
        JL_Fecha_Admin = new javax.swing.JLabel();
        JL_Hora_Admin = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        JB_Ingresar = new javax.swing.JButton();
        Regla1 = new javax.swing.JLabel();
        JTF_IdUser = new javax.swing.JTextField();
        JL_Fecha = new javax.swing.JLabel();
        Regla2 = new javax.swing.JLabel();
        JL_Hora = new javax.swing.JLabel();
        JPF_PassUser = new javax.swing.JPasswordField();

        JB_Caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Sebas.png"))); // NOI18N
        JB_Caja.setBorder(null);
        JB_Caja.setBorderPainted(false);
        JB_Caja.setContentAreaFilled(false);
        JB_Caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Caja.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Sebas_hover.png"))); // NOI18N

        JB_Personal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Personal.png"))); // NOI18N
        JB_Personal.setBorder(null);
        JB_Personal.setBorderPainted(false);
        JB_Personal.setContentAreaFilled(false);
        JB_Personal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Personal.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Personal_hover.png"))); // NOI18N

        JB_Clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Cleintes.png"))); // NOI18N
        JB_Clientes.setBorder(null);
        JB_Clientes.setBorderPainted(false);
        JB_Clientes.setContentAreaFilled(false);
        JB_Clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Clientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Cleintes_hover.png"))); // NOI18N

        JB_Pedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Pedidos.png"))); // NOI18N
        JB_Pedidos.setBorder(null);
        JB_Pedidos.setBorderPainted(false);
        JB_Pedidos.setContentAreaFilled(false);
        JB_Pedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Pedidos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Pedidos_hover.png"))); // NOI18N

        JB_Estadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estadisticas.png"))); // NOI18N
        JB_Estadisticas.setBorder(null);
        JB_Estadisticas.setBorderPainted(false);
        JB_Estadisticas.setContentAreaFilled(false);
        JB_Estadisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Estadisticas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estadisticas_hover.png"))); // NOI18N

        JB_Zonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Zonas.png"))); // NOI18N
        JB_Zonas.setBorder(null);
        JB_Zonas.setBorderPainted(false);
        JB_Zonas.setContentAreaFilled(false);
        JB_Zonas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Zonas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Zonas_hover.png"))); // NOI18N

        JB_Comidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Comidas.png"))); // NOI18N
        JB_Comidas.setBorder(null);
        JB_Comidas.setBorderPainted(false);
        JB_Comidas.setContentAreaFilled(false);
        JB_Comidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Comidas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Comidas_hover.png"))); // NOI18N

        JB_Cerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Salir.png"))); // NOI18N
        JB_Cerrar_sesion.setBorder(null);
        JB_Cerrar_sesion.setBorderPainted(false);
        JB_Cerrar_sesion.setContentAreaFilled(false);
        JB_Cerrar_sesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Cerrar_sesion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Salir_hover.png"))); // NOI18N

        JB_Color_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Color_admin.png"))); // NOI18N
        JB_Color_admin.setBorder(null);
        JB_Color_admin.setBorderPainted(false);
        JB_Color_admin.setContentAreaFilled(false);
        JB_Color_admin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Color_admin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Color_admin_hover.png"))); // NOI18N

        JL_Usuario_admin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Usuario_admin.setForeground(new java.awt.Color(255, 255, 255));
        JL_Usuario_admin.setText("USUARIO: FACFAC");

        JL_Fecha_Admin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha_Admin.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha_Admin.setText("FF/FF/FFFF");

        JL_Hora_Admin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora_Admin.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora_Admin.setText("HH:HH");

        javax.swing.GroupLayout JF_Menu_adminLayout = new javax.swing.GroupLayout(JF_Menu_admin.getContentPane());
        JF_Menu_admin.getContentPane().setLayout(JF_Menu_adminLayout);
        JF_Menu_adminLayout.setHorizontalGroup(
            JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                        .addComponent(JL_Usuario_admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(214, 214, 214))
                    .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                        .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_Personal)
                            .addComponent(JB_Clientes)
                            .addComponent(JB_Pedidos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Caja, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_Comidas)
                            .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(JB_Estadisticas)
                                .addComponent(JB_Zonas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JB_Color_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JB_Cerrar_sesion))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                        .addComponent(JL_Fecha_Admin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JL_Hora_Admin)
                        .addGap(33, 33, 33))))
        );
        JF_Menu_adminLayout.setVerticalGroup(
            JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(JL_Usuario_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                        .addComponent(JB_Personal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Clientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Pedidos))
                    .addComponent(JB_Caja)
                    .addGroup(JF_Menu_adminLayout.createSequentialGroup()
                        .addComponent(JB_Estadisticas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Zonas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Comidas))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF_Menu_adminLayout.createSequentialGroup()
                        .addComponent(JB_Color_admin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Cerrar_sesion)))
                .addGap(18, 18, 18)
                .addGroup(JF_Menu_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Fecha_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Hora_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(224, Short.MAX_VALUE))
        );

        jButton5.setText("jButton5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);

        JB_Ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Ingresar.png"))); // NOI18N
        JB_Ingresar.setAlignmentX(200.0F);
        JB_Ingresar.setAlignmentY(200.0F);
        JB_Ingresar.setBorder(null);
        JB_Ingresar.setBorderPainted(false);
        JB_Ingresar.setContentAreaFilled(false);
        JB_Ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Ingresar.setPreferredSize(new java.awt.Dimension(184, 48));
        JB_Ingresar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Ingresar_hover.png"))); // NOI18N
        JB_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_IngresarActionPerformed(evt);
            }
        });

        Regla1.setToolTipText("");
        Regla1.setMaximumSize(new java.awt.Dimension(108, 50));

        JL_Fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha.setText("FF/FF/FFFF");

        Regla2.setToolTipText("");

        JL_Hora.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora.setText("HH:HH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Regla1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPF_PassUser, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTF_IdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Regla2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_Fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JL_Hora)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Regla1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JTF_IdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(JPF_PassUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Regla2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_IngresarActionPerformed
        try {
            Usuario user = new Usuario();
            
            String pass;
            
            boolean ban;
            
            user.setDni(Integer.parseInt(JTF_IdUser.getText()));
            pass= JPF_PassUser.getText().trim();
            user.setPass(pass);
            GestorUsuario gesUs =new GestorUsuario();
            ban=gesUs.Acceso(user);
            System.out.println("lalalaban"+ban);
            if(ban){
                
                /*cuentaOficial=  gescuent.buscarCuenta(cuenta.getUsuario());
                
                BotonesAcceso();
                
                
                
                Ventana.setCuenta(cuentaOficial);
                */ JOptionPane.showMessageDialog(rootPane, "INGRESO AL SISTEMA");
                JF_Menu_admin.setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Usuario o Clave Incorrectos");
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JB_IngresarActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Caja;
    private javax.swing.JButton JB_Cerrar_sesion;
    private javax.swing.JButton JB_Clientes;
    private javax.swing.JButton JB_Color_admin;
    private javax.swing.JButton JB_Comidas;
    private javax.swing.JButton JB_Estadisticas;
    private javax.swing.JButton JB_Ingresar;
    private javax.swing.JButton JB_Pedidos;
    private javax.swing.JButton JB_Personal;
    private javax.swing.JButton JB_Zonas;
    private javax.swing.JFrame JF_Menu_admin;
    private javax.swing.JLabel JL_Fecha;
    private javax.swing.JLabel JL_Fecha_Admin;
    private javax.swing.JLabel JL_Hora;
    private javax.swing.JLabel JL_Hora_Admin;
    private javax.swing.JLabel JL_Usuario_admin;
    private javax.swing.JPasswordField JPF_PassUser;
    private javax.swing.JTextField JTF_IdUser;
    private javax.swing.JLabel Regla1;
    private javax.swing.JLabel Regla2;
    private javax.swing.JButton jButton5;
    // End of variables declaration//GEN-END:variables
}
