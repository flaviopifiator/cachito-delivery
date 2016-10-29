/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;
import Excepciones.UsuarioExistenteException;
import Excepciones.UsuarioInexistenteException;
import Ventana_clases.Agregar_usuario;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.GestorUsuario;
import modelos.Usuario;
/**
 *
 * @author Cusipuma
 */
public class Principal extends javax.swing.JFrame {

    Usuario cuentaOficial = new Usuario();
    
    Listado_empleados vtListaEmpleados = null;
    
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
   private void limpiarLogin(){
        JPF_PassUser.setText("");
        JTF_IdUser.setText("");
    }
    
    public Principal() {
        initComponents();
        setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        Inicio_sesion fondo = new Inicio_sesion(399,600);
        this.add(fondo, BorderLayout.CENTER);
        
        this.JL_Fecha.setText(this.Fecha());
        this.JL_Hora.setText(this.Hora());
        
        //TESTEANDO OTRAS VENTANAS
        
        Menu_admin fondo_prueba1 = new Menu_admin(673,260);
        JF_Menu_admin.add(fondo_prueba1, BorderLayout.CENTER);
        JF_Menu_admin.setSize(673,299);
        JF_Menu_admin.setVisible(false);
        JF_Menu_admin.setLocation(346, 234);        
        Principal.super.setVisible(false);
        Agregar_usuario fondo_prueba = new Agregar_usuario(1000,667);
        JL_Foto_usuario.setIcon(new ImageIcon(getClass().getResource("/Prueba_GUI/Pipita.png")));
        JF_Agregar_Usuario.add(fondo_prueba, BorderLayout.CENTER);               
        JF_Agregar_Usuario.setSize(1000,706);        
        JF_Agregar_Usuario.setVisible(false);
        centrarPantalla(this);
        
        
    }
        public void  MenuAdminVisible(Boolean b){
            JF_Menu_admin.setVisible(b);
            
        }
        public void  AgregarUsVisible(Boolean b){
            JF_Agregar_Usuario.setVisible(b);
            JF_Agregar_Usuario.setLocation(183, 31);
            
        }
        
    private void centrarPantalla(JFrame vt){
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        vt.setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
        
    }
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
        JF_Agregar_Usuario = new javax.swing.JFrame();
        jTApellido = new javax.swing.JTextField();
        jTNombre = new javax.swing.JTextField();
        jTContraseña = new javax.swing.JTextField();
        JL_Foto_usuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTDescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTPais = new javax.swing.JTextField();
        jTArea = new javax.swing.JTextField();
        jTtelefono = new javax.swing.JTextField();
        jBAgregarTel = new javax.swing.JButton();
        jBQuitarTel = new javax.swing.JButton();
        jBExaminarFoto = new javax.swing.JButton();
        jBQuitarFoto = new javax.swing.JButton();
        jTDni = new javax.swing.JTextField();
        jTUsuario = new javax.swing.JTextField();
        jCCargo = new javax.swing.JComboBox();
        jBRegistrar = new javax.swing.JButton();
        jBAtras = new javax.swing.JButton();
        JL_Usuario_admin1 = new javax.swing.JLabel();
        JL_Fecha_Admin1 = new javax.swing.JLabel();
        JL_Hora_Admin1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        JB_Ingresar = new javax.swing.JButton();
        Regla1 = new javax.swing.JLabel();
        JTF_IdUser = new javax.swing.JTextField();
        JL_Fecha = new javax.swing.JLabel();
        Regla2 = new javax.swing.JLabel();
        JL_Hora = new javax.swing.JLabel();
        JPF_PassUser = new javax.swing.JPasswordField();

        JF_Menu_admin.setUndecorated(true);
        JF_Menu_admin.setResizable(false);

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
        JB_Personal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PersonalActionPerformed(evt);
            }
        });

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
        JB_Cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Cerrar_sesionActionPerformed(evt);
            }
        });

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

        JF_Agregar_Usuario.setUndecorated(true);
        JF_Agregar_Usuario.setResizable(false);

        jTApellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTApellido.setToolTipText("");
        jTApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApellidoActionPerformed(evt);
            }
        });

        jTNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTNombre.setToolTipText("");
        jTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreActionPerformed(evt);
            }
        });

        jTContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTContraseña.setToolTipText("");
        jTContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTContraseñaActionPerformed(evt);
            }
        });

        JL_Foto_usuario.setToolTipText("");

        jTDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jList1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jTPais.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTtelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jBAgregarTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Telefono.png"))); // NOI18N
        jBAgregarTel.setBorder(null);
        jBAgregarTel.setBorderPainted(false);
        jBAgregarTel.setContentAreaFilled(false);
        jBAgregarTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBAgregarTel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Telefono_hover.png"))); // NOI18N

        jBQuitarTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar.png"))); // NOI18N
        jBQuitarTel.setBorder(null);
        jBQuitarTel.setBorderPainted(false);
        jBQuitarTel.setContentAreaFilled(false);
        jBQuitarTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBQuitarTel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar_hover.png"))); // NOI18N

        jBExaminarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Examinar.png"))); // NOI18N
        jBExaminarFoto.setBorder(null);
        jBExaminarFoto.setBorderPainted(false);
        jBExaminarFoto.setContentAreaFilled(false);
        jBExaminarFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBExaminarFoto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Examinar_hover.png"))); // NOI18N

        jBQuitarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar.png"))); // NOI18N
        jBQuitarFoto.setBorder(null);
        jBQuitarFoto.setBorderPainted(false);
        jBQuitarFoto.setContentAreaFilled(false);
        jBQuitarFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBQuitarFoto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar_hover.png"))); // NOI18N

        jTDni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTDniFocusLost(evt);
            }
        });

        jTUsuario.setEditable(false);
        jTUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jCCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Usuario Normal" }));

        jBRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Agregar.png"))); // NOI18N
        jBRegistrar.setBorder(null);
        jBRegistrar.setBorderPainted(false);
        jBRegistrar.setContentAreaFilled(false);
        jBRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBRegistrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Agregar_hover.png"))); // NOI18N
        jBRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarActionPerformed(evt);
            }
        });

        jBAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Atras.png"))); // NOI18N
        jBAtras.setBorder(null);
        jBAtras.setBorderPainted(false);
        jBAtras.setContentAreaFilled(false);
        jBAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBAtras.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Atras_hover.png"))); // NOI18N
        jBAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtrasActionPerformed(evt);
            }
        });

        JL_Usuario_admin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Usuario_admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Usuario_admin1.setText("USUARIO: FACFAC");

        JL_Fecha_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha_Admin1.setText("FF/FF/FFFF");

        JL_Hora_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora_Admin1.setText("HH:HH");

        javax.swing.GroupLayout JF_Agregar_UsuarioLayout = new javax.swing.GroupLayout(JF_Agregar_Usuario.getContentPane());
        JF_Agregar_Usuario.getContentPane().setLayout(JF_Agregar_UsuarioLayout);
        JF_Agregar_UsuarioLayout.setHorizontalGroup(
            JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                        .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addComponent(jTDni, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addComponent(JL_Foto_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jBQuitarFoto)
                                            .addComponent(jBExaminarFoto)))
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(4, 4, 4)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addComponent(jTDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jTPais, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTArea, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTtelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                            .addComponent(jBQuitarTel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jBAgregarTel)))
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JF_Agregar_UsuarioLayout.createSequentialGroup()
                                                    .addComponent(jTUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JF_Agregar_UsuarioLayout.createSequentialGroup()
                                                    .addComponent(jCCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jBRegistrar))
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBAtras))))
                            .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                        .addComponent(JL_Fecha_Admin1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 711, Short.MAX_VALUE)
                        .addComponent(JL_Hora_Admin1)
                        .addGap(57, 57, 57))))
        );
        JF_Agregar_UsuarioLayout.setVerticalGroup(
            JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JF_Agregar_UsuarioLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBAtras)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                        .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JL_Foto_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addComponent(jBQuitarFoto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBExaminarFoto))))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTPais, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTArea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTtelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBQuitarTel)
                                    .addComponent(jBAgregarTel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBRegistrar)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Fecha_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Hora_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(160, 160, 160)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

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
                .addContainerGap(189, Short.MAX_VALUE))
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
                
                cuentaOficial=  gesUs.buscarUsuario(user.getDni());
                
                JOptionPane.showMessageDialog(rootPane, "INGRESO AL SISTEMA");
                JF_Menu_admin.setVisible(true);
                JL_Usuario_admin.setText("USUARIO:"+cuentaOficial.getNombre()+" "+cuentaOficial.getApellido());
                JL_Usuario_admin1.setText("USUARIO:"+cuentaOficial.getNombre()+" "+cuentaOficial.getApellido());
                limpiarLogin();
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Usuario o Clave Incorrectos");
            }
        } catch (DataAccessException ex) {JOptionPane.showMessageDialog(rootPane, ex);}        
          catch (UsuarioInexistenteException ex) { }           
        
    }//GEN-LAST:event_JB_IngresarActionPerformed

    private void jTContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTContraseñaActionPerformed

    private void jTNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNombreActionPerformed

    private void jTApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTApellidoActionPerformed

    private void JB_PersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PersonalActionPerformed
        
        JF_Menu_admin.setVisible(false);
      
         vtListaEmpleados= new Listado_empleados(this);        
         vtListaEmpleados.setSize(1000, 559);
    }//GEN-LAST:event_JB_PersonalActionPerformed

    private void jBAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtrasActionPerformed
        JF_Agregar_Usuario.dispose();
        vtListaEmpleados.setVisible(true);
        
    }//GEN-LAST:event_jBAtrasActionPerformed

    private void jBRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarActionPerformed
        if(jTDni.getText().isEmpty() || jTApellido.getText().isEmpty() || jTNombre.getText().isEmpty()
               /* || jTtelefono.getText().isEmpty()*/){
            JOptionPane.showMessageDialog(null, "No se permiten campos vacíos");
            return;
        }
        
        
        Usuario user = new Usuario();
        int dni; 
        try
        {
        dni = Integer.parseInt(jTDni.getText().trim());
        user.setDni(dni);
        user.setNombre(jTNombre.getText().trim());
        user.setApellido(jTApellido.getText().trim());
        user.setPass(jTContraseña.getText().trim());
        user.setCargo(jCCargo.getSelectedIndex());
        user.setFoto("");
        user.setActivo(true); 
        
            GestorUsuario gesus = new GestorUsuario();
            gesus.agregarNuevoUsuario(user);
            
        }catch(NumberFormatException ex ){JOptionPane.showMessageDialog(null, "Solo debe ingresar numeros en el campo dni");return;}
        
        catch(DataAccessException ex){JOptionPane.showMessageDialog(null, "Error al Insertar"+ex);return;}
        catch(UsuarioExistenteException ex){JOptionPane.showMessageDialog(null, "Error el Usuario ya existe");return;}
        
        JOptionPane.showMessageDialog(null, "El Cliente fue agregado con exito puto");
         //Limpiar();
         
    }//GEN-LAST:event_jBRegistrarActionPerformed

    private void jTDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTDniFocusLost
        try{
        int dni = Integer.parseInt(jTDni.getText().trim());
        jTUsuario.setText(""+dni);
       }catch(NumberFormatException ex){JOptionPane.showMessageDialog(rootPane, "Solo se permiten numeros en este campo");}
        
    }//GEN-LAST:event_jTDniFocusLost

    private void JB_Cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Cerrar_sesionActionPerformed
        this.setVisible(true);
        JF_Menu_admin.dispose();
    }//GEN-LAST:event_JB_Cerrar_sesionActionPerformed

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
    public static javax.swing.JFrame JF_Agregar_Usuario;
    private static javax.swing.JFrame JF_Menu_admin;
    private javax.swing.JLabel JL_Fecha;
    private javax.swing.JLabel JL_Fecha_Admin;
    private javax.swing.JLabel JL_Fecha_Admin1;
    private javax.swing.JLabel JL_Foto_usuario;
    private javax.swing.JLabel JL_Hora;
    private javax.swing.JLabel JL_Hora_Admin;
    private javax.swing.JLabel JL_Hora_Admin1;
    private javax.swing.JLabel JL_Usuario_admin;
    private javax.swing.JLabel JL_Usuario_admin1;
    private javax.swing.JPasswordField JPF_PassUser;
    private javax.swing.JTextField JTF_IdUser;
    private javax.swing.JLabel Regla1;
    private javax.swing.JLabel Regla2;
    private javax.swing.JButton jBAgregarTel;
    private javax.swing.JButton jBAtras;
    private javax.swing.JButton jBExaminarFoto;
    private javax.swing.JButton jBQuitarFoto;
    private javax.swing.JButton jBQuitarTel;
    private javax.swing.JButton jBRegistrar;
    private javax.swing.JComboBox jCCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTArea;
    private javax.swing.JTextField jTContraseña;
    private javax.swing.JTextField jTDescripcion;
    private javax.swing.JTextField jTDni;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTPais;
    private javax.swing.JTextField jTUsuario;
    private javax.swing.JTextField jTtelefono;
    // End of variables declaration//GEN-END:variables
}
