/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;
import Excepciones.TelefonoUsuarioInexistenteException;
import Excepciones.UsuarioExistenteException;
import Excepciones.UsuarioInexistenteException;
import Ventana_clases.Agregar_usuario;
import Ventana_clases.Inicio_sesion;
import Ventana_clases.Menu_admin;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelos.CajaDAO;
import modelos.Fecha;
import modelos.GestorUsuario;
import modelos.Telefono_UsuarioDAO;
import modelos.Usuario;
import modelos.UsuarioDAO;
/**
 *
 * @author Cusipuma
 */
public class Principal extends javax.swing.JFrame implements Runnable{

    Usuario cuentaOficial = new Usuario();
    
    Listado_empleados vtListaEmpleados = null;
    Fecha fecha = new Fecha();
    FileInputStream fis;
    int longitudBytes;
    Object[][] telefono = new Object[0][2];
    Thread h1;

    
    
    public Principal() throws DataAccessException {
        initComponents();
        h1= new Thread(this);
//        h1.start();
        this.setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        Inicio_sesion fondo = new Inicio_sesion(399,600);
        this.add(fondo, BorderLayout.CENTER);
        this.setSize(399,629); //DAR 39 PX MÁS DE ALTURA 
        this.setTitle("Cachito Delivery");
        this.setResizable(false);
        
        this.JL_Fecha.setText(fecha.getFecha());
        this.JL_Hora.setText(fecha.getHora());
        
        this.JL_Fecha_Admin.setText(fecha.getFecha());
        this.JL_Hora_Admin.setText(fecha.getHora());
        
        this.JL_Fecha_Admin1.setText(fecha.getFecha());
        this.JL_Hora_Admin1.setText(fecha.getHora());
        
        
        UsuarioDAO ultimo = new UsuarioDAO();
        this.jTUsuario.setText(""+(ultimo.lastUser()+1));
        
        comprobarCaja();
        
        Menu_admin fondo_prueba1 = new Menu_admin(673,260);
        JF_Menu_admin.add(fondo_prueba1, BorderLayout.CENTER);
        JF_Menu_admin.setSize(673,285);
        JF_Menu_admin.setResizable(false);
        JF_Menu_admin.setVisible(false);
        JF_Menu_admin.setLocationRelativeTo(null);
        JF_Menu_admin.setTitle("Opciones de administrador");
        JF_Menu_admin.setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        Principal.super.setVisible(false);
        
        Agregar_usuario fondo_prueba = new Agregar_usuario(1000,667);
        JF_Agregar_Usuario.add(fondo_prueba, BorderLayout.CENTER);
        JF_Agregar_Usuario.setResizable(false);
        JF_Agregar_Usuario.setSize(1000,690);
        JF_Agregar_Usuario.setLocationRelativeTo(null);
        JF_Agregar_Usuario.setTitle("Agregar nuevo usuario");
        JF_Agregar_Usuario.setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());    
        JF_Agregar_Usuario.setVisible(false);
        centrarPantalla(this); 
    }
    
    public void clearAgregar() throws DataAccessException{
        jTApellido.setText("");
        jTNombre.setText("");
        jTDni.setText("");
        foto_usuario.setIcon(null);
        jTDescripcion.setText("");
        jTPais.setText("54");
        jTArea.setText("383");
        jTtelefono.setText("");
        telefono = new Object[0][2];
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    telefono,new String[] {"",""}));
        jTContraseña.setText("");
        UsuarioDAO ultimo = new UsuarioDAO();
        this.jTUsuario.setText(""+(ultimo.lastUser()+1));
        jCCargo.setSelectedIndex(0);
        
        fis = null;
        longitudBytes=0;
    }
    

    private void limpiarLogin(){
        JPF_PassUser.setText("");
        JTF_IdUser.setText("");
    }
    
    public void  MenuAdminVisible(Boolean b, Usuario user){
        cuentaOficial=user;
        JL_Usuario_admin.setText("USUARIO: "+cuentaOficial.getApellido()+" "+cuentaOficial.getNombre());
        JF_Menu_admin.setVisible(b);

    }
    public void  AgregarUsVisible(Boolean b){
        JL_Usuario_admin1.setText("USUARIO: "+cuentaOficial.getApellido()+" "+cuentaOficial.getNombre());
        JF_Agregar_Usuario.setVisible(b);

    }
    
    public void comprobarCaja() throws DataAccessException{
        CajaDAO caja = new CajaDAO();
        if(caja.primera())
            if(caja.seba()){
                JB_Caja.setIcon(new ImageIcon(getClass().getResource("/Botones/Sebas.png")));
                JB_Caja.setRolloverIcon(new ImageIcon(getClass().getResource("/Botones/Sebas_hover.png")));
            }else{
                JB_Caja.setIcon(new ImageIcon(getClass().getResource("/Botones/Cerrado.png")));
                JB_Caja.setRolloverIcon(new ImageIcon(getClass().getResource("/Botones/Cerrado_hover.png")));
            }                  
    }
    
    public void setCuenta(Usuario user){
        this.cuentaOficial=user;
    }
        
    private void centrarPantalla(JFrame vt){
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        vt.setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
        
    }
    
    private void ingresoSistema(){
        try{
            Usuario user = new Usuario();

            String pass;

            boolean ban;

            user.setCod(Integer.parseInt(JTF_IdUser.getText()));
            pass= JPF_PassUser.getText().trim();
            
            user.setPass(pass);
            
            GestorUsuario gesUs =new GestorUsuario();
            ban=gesUs.Acceso(user);
            
            cuentaOficial=  gesUs.buscarUsuario(user.getCod());
            
            if(cuentaOficial==null){
                JOptionPane.showMessageDialog(rootPane, "USUARIO o CONTRASEÑA incorrectos.","Error al iniciar sesión",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(ban && cuentaOficial.getActivo()==true){
                this.h1.stop();
                if(cuentaOficial.getCargo()==1){
                    Menu_cajero menu = new Menu_cajero(cuentaOficial);
                    menu.setVisible(true);
                }else{
                    JF_Menu_admin.setVisible(true);
                    JL_Usuario_admin.setText("USUARIO: "+cuentaOficial.getApellido()+" "+cuentaOficial.getNombre());
                    JL_Usuario_admin1.setText("USUARIO: "+cuentaOficial.getApellido()+" "+cuentaOficial.getNombre());
                    limpiarLogin();
                    this.setVisible(false);
                }

                limpiarLogin();
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(rootPane, "USUARIO o CONTRASEÑA incorrectos.","Error al iniciar sesión",JOptionPane.ERROR_MESSAGE);
            }
        }catch (DataAccessException | UsuarioInexistenteException ex) {JOptionPane.showMessageDialog(rootPane, ex);
        }  
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
        foto_usuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTDescripcion = new javax.swing.JTextField();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        Object[][] n=null;
        jTable2 = new javax.swing.JTable();
        JB_Ingresar = new javax.swing.JButton();
        Regla1 = new javax.swing.JLabel();
        JTF_IdUser = new javax.swing.JTextField();
        JL_Fecha = new javax.swing.JLabel();
        Regla2 = new javax.swing.JLabel();
        JL_Hora = new javax.swing.JLabel();
        JPF_PassUser = new javax.swing.JPasswordField();

        JF_Menu_admin.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        JF_Menu_admin.setResizable(false);

        JB_Caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Cerrado.png"))); // NOI18N
        JB_Caja.setBorder(null);
        JB_Caja.setBorderPainted(false);
        JB_Caja.setContentAreaFilled(false);
        JB_Caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Caja.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Cerrado_hover.png"))); // NOI18N
        JB_Caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CajaActionPerformed(evt);
            }
        });

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
        JB_Pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PedidosActionPerformed(evt);
            }
        });

        JB_Estadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estadisticas.png"))); // NOI18N
        JB_Estadisticas.setBorder(null);
        JB_Estadisticas.setBorderPainted(false);
        JB_Estadisticas.setContentAreaFilled(false);
        JB_Estadisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Estadisticas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estadisticas_hover.png"))); // NOI18N
        JB_Estadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_EstadisticasActionPerformed(evt);
            }
        });

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
        JL_Usuario_admin.setText("USUARIO:");

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

        JF_Agregar_Usuario.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        JF_Agregar_Usuario.setResizable(false);

        jTApellido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTApellido.setToolTipText("");
        jTApellido.setNextFocusableComponent(jTNombre);
        jTApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApellidoActionPerformed(evt);
            }
        });

        jTNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTNombre.setToolTipText("");
        jTNombre.setNextFocusableComponent(jTDni);
        jTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreActionPerformed(evt);
            }
        });

        jTContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTContraseña.setToolTipText("");
        jTContraseña.setNextFocusableComponent(jCCargo);
        jTContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTContraseñaActionPerformed(evt);
            }
        });

        foto_usuario.setToolTipText("");

        jTDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTDescripcion.setNextFocusableComponent(jTPais);

        jTPais.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTPais.setText("54");
        jTPais.setNextFocusableComponent(jTArea);
        jTPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTPaisActionPerformed(evt);
            }
        });
        jTPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTPaisKeyTyped(evt);
            }
        });

        jTArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTArea.setText("383");
        jTArea.setNextFocusableComponent(jTtelefono);
        jTArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTAreaKeyTyped(evt);
            }
        });

        jTtelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTtelefono.setNextFocusableComponent(jBAgregarTel);
        jTtelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTtelefonoKeyTyped(evt);
            }
        });

        jBAgregarTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Telefono.png"))); // NOI18N
        jBAgregarTel.setBorder(null);
        jBAgregarTel.setBorderPainted(false);
        jBAgregarTel.setContentAreaFilled(false);
        jBAgregarTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBAgregarTel.setNextFocusableComponent(jTContraseña);
        jBAgregarTel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Telefono_hover.png"))); // NOI18N
        jBAgregarTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarTelActionPerformed(evt);
            }
        });
        jBAgregarTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBAgregarTelKeyPressed(evt);
            }
        });

        jBQuitarTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar.png"))); // NOI18N
        jBQuitarTel.setBorder(null);
        jBQuitarTel.setBorderPainted(false);
        jBQuitarTel.setContentAreaFilled(false);
        jBQuitarTel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBQuitarTel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar_hover.png"))); // NOI18N
        jBQuitarTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBQuitarTelActionPerformed(evt);
            }
        });

        jBExaminarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Examinar.png"))); // NOI18N
        jBExaminarFoto.setBorder(null);
        jBExaminarFoto.setBorderPainted(false);
        jBExaminarFoto.setContentAreaFilled(false);
        jBExaminarFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBExaminarFoto.setNextFocusableComponent(jTDescripcion);
        jBExaminarFoto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Examinar_hover.png"))); // NOI18N
        jBExaminarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExaminarFotoActionPerformed(evt);
            }
        });
        jBExaminarFoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBExaminarFotoKeyPressed(evt);
            }
        });

        jBQuitarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar.png"))); // NOI18N
        jBQuitarFoto.setBorder(null);
        jBQuitarFoto.setBorderPainted(false);
        jBQuitarFoto.setContentAreaFilled(false);
        jBQuitarFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBQuitarFoto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar_hover.png"))); // NOI18N
        jBQuitarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBQuitarFotoActionPerformed(evt);
            }
        });

        jTDni.setColumns(8);
        jTDni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTDni.setNextFocusableComponent(jBExaminarFoto);
        jTDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTDniFocusLost(evt);
            }
        });
        jTDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTDniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTDniKeyTyped(evt);
            }
        });

        jTUsuario.setEditable(false);
        jTUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jCCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Cajero" }));
        jCCargo.setNextFocusableComponent(jBRegistrar);

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
        jBRegistrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBRegistrarKeyPressed(evt);
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
        JL_Usuario_admin1.setText("USUARIO:");

        JL_Fecha_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha_Admin1.setText("FF/FF/FFFF");

        JL_Hora_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora_Admin1.setText("HH:HH");

        jTable2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;}
        };
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            n,
            new String [] { "", ""
            }
        ));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setTableHeader(null);
        jScrollPane3.setViewportView(jTable2);

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
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
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
                                                .addComponent(foto_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jBQuitarFoto)
                                                    .addComponent(jBExaminarFoto))))
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
                                                .addComponent(jBAtras))
                                            .addGroup(JF_Agregar_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                                    .addComponent(jBQuitarTel)
                                                    .addGap(112, 112, 112)
                                                    .addComponent(jBAgregarTel)))))
                                    .addGroup(JF_Agregar_UsuarioLayout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                    .addComponent(foto_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

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
        JB_Ingresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JB_IngresarKeyPressed(evt);
            }
        });

        Regla1.setToolTipText("");
        Regla1.setMaximumSize(new java.awt.Dimension(108, 50));

        JTF_IdUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTF_IdUserKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTF_IdUserKeyTyped(evt);
            }
        });

        JL_Fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha.setText("FF/FF/FFFF");

        Regla2.setToolTipText("");

        JL_Hora.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora.setText("HH:HH");

        JPF_PassUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JPF_PassUserKeyPressed(evt);
            }
        });

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
        ingresoSistema();
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
        this.h1.stop();
        try {        
            vtListaEmpleados= new Listado_empleados(cuentaOficial);
            vtListaEmpleados.mostrar(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JB_PersonalActionPerformed

    private void jBAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtrasActionPerformed
        try {
            clearAgregar();
            this.h1.stop();
            JF_Agregar_Usuario.dispose();
            vtListaEmpleados = new Listado_empleados(cuentaOficial);
            vtListaEmpleados.clearBusqueda();
            vtListaEmpleados.iniciarListado();
            vtListaEmpleados.mostrar(true);
        } catch (DataAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBAtrasActionPerformed

    private void jBRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarActionPerformed
        if(jTApellido.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ingresó un APELLIDO de usuario.");
            return;
        }if(jTNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ingresó un NOMBRE de usuario.");
            return;
        }if(jTDni.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ingresó número de DOCUMENTO de usuario.");
            return;
        }if(jTContraseña.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ingresó una CONTRASEÑA de usuario.");
            return;
        }        
        Usuario user = new Usuario();
        int dni; 
        
        try{
            dni = Integer.parseInt(jTDni.getText().trim());
            user.setDni(dni);
            user.setNombre(jTNombre.getText().trim());
            user.setApellido(jTApellido.getText().trim());
            user.setPass(jTContraseña.getText().trim());
            user.setCargo(jCCargo.getSelectedIndex());
            user.setActivo(true); 
            user.setFis(fis);
            user.setLongitud(longitudBytes);        
            GestorUsuario gesus = new GestorUsuario();         
            Telefono_UsuarioDAO tel = new Telefono_UsuarioDAO();
            UsuarioDAO usuario = new UsuarioDAO();
            int codigo = usuario.lastUser();
            Object[][] aux = new Object[telefono.length][3];
            for(int i=0; i<telefono.length;i++){
                aux[i][0]=codigo+1;
                aux[i][1]=telefono[i][0];
                aux[i][2]=telefono[i][1]; 
            }
            tel.agregarGabe(aux);
            gesus.agregarNuevoUsuario(user); 
            
        
            try {
                clearAgregar();
            }catch (DataAccessException ex) {Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }catch(NumberFormatException ex ){JOptionPane.showMessageDialog(null, "Solo debe ingresar numeros en el DOCUMENTO.");
        }catch(TelefonoUsuarioInexistenteException ex){JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch(DataAccessException ex){JOptionPane.showMessageDialog(null, "Error al Insertar "+ex);
        }catch(UsuarioExistenteException ex){JOptionPane.showMessageDialog(null, "EL DOCUMENTO de usuario ya fue registrado.");
        JOptionPane.showMessageDialog(null, "El usuario fue agregado con éxito.");
        }         
    }//GEN-LAST:event_jBRegistrarActionPerformed

    private void jTDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTDniFocusLost

    }//GEN-LAST:event_jTDniFocusLost

    private void JB_Cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Cerrar_sesionActionPerformed
        this.setVisible(true);
        JF_Menu_admin.dispose();
    }//GEN-LAST:event_JB_Cerrar_sesionActionPerformed

    private void jBExaminarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExaminarFotoActionPerformed
        JFileChooser j=new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        j.setFileFilter(filtro);
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado=j.showOpenDialog(null);
        if(estado== JFileChooser.APPROVE_OPTION){
            String exten="";
            for (int i=j.getSelectedFile().getName().length()-1; i>=0; i--)
                exten=exten+j.getSelectedFile().getName().charAt(i);
            String extension ="";
            for (int k=0; k<4; k++)
                extension=extension+exten.charAt(k);
            
            if(extension.toLowerCase().equals("gnp.") || extension.toLowerCase().equals("gpj.") || extension.toLowerCase().equals("gepj") || extension.toLowerCase().equals("fig.")){
                try{
                    fis=new FileInputStream(j.getSelectedFile());
                    this.longitudBytes=(int)j.getSelectedFile().length();
                    try {
                        Image icono=ImageIO.read(j.getSelectedFile()).getScaledInstance
                                (foto_usuario.getWidth(),foto_usuario.getHeight(),Image.SCALE_DEFAULT);
                        foto_usuario.setIcon(new ImageIcon(icono));
                        foto_usuario.updateUI();
                    }catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPane, "imagen: "+ex);
                    }
                }catch(FileNotFoundException ex){
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Solo se permiten archivos del tipo .png, .gif y .jpg","No se pudo agregar la imagen",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBExaminarFotoActionPerformed

    private void jBQuitarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBQuitarFotoActionPerformed
        foto_usuario.setIcon(null);
        fis=null;
        longitudBytes=0;
    }//GEN-LAST:event_jBQuitarFotoActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void JB_IngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JB_IngresarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            ingresoSistema();
    }//GEN-LAST:event_JB_IngresarKeyPressed

    private void JPF_PassUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JPF_PassUserKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            ingresoSistema();
    }//GEN-LAST:event_JPF_PassUserKeyPressed

    private void JTF_IdUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTF_IdUserKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            ingresoSistema();  

    }//GEN-LAST:event_JTF_IdUserKeyPressed

    private void jTPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPaisActionPerformed

    private void jBAgregarTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarTelActionPerformed
        if(jTtelefono.getText().isEmpty() || jTPais.getText().isEmpty() || jTArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "No se ingreso un numero de telefono válido.");
            return;
        }
        if(telefono.length==0){
           telefono = new Object[telefono.length+1][2];
           telefono[0][0]="+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText();
           telefono[0][1]=jTDescripcion.getText();
        }else{
            for(int i=0;i<telefono.length;i++){
                if(Objects.equals("+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText(),telefono[i][0])){
                    JOptionPane.showMessageDialog(rootPane, "El telefono "+"+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText()+
                            ".\nYa ha sido ingresado.");
                    return; 
                }
            }

            Object[][] tel = telefono;

            telefono = new Object[telefono.length+1][2];
            for(int i=0; i<telefono.length;i++){

                if(i==telefono.length-1){
                    telefono[i][0]="+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText();
                    telefono[i][1]=jTDescripcion.getText();
                }else{
                    telefono[i][1]=tel[i][1];
                    telefono[i][0]=tel[i][0];
                }    
            }
        }
        
        Object[][] aux = new Object[telefono.length][2];
        for(int i=0;i<telefono.length;i++){
            aux[i][0]=telefono[i][1];
            aux[i][1]=telefono[i][0];
        }
        
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        aux,new String[] {"",""}));
        jTDescripcion.setText("");
        jTPais.setText("54");
        jTArea.setText("383");
        jTtelefono.setText("");
        
    }//GEN-LAST:event_jBAgregarTelActionPerformed

    private void jBQuitarTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBQuitarTelActionPerformed
        if(jTable2.getSelectedRow()==-1)
            return;
            
        String numero=jTable2.getValueAt(jTable2.getSelectedRow(),1).toString();
        Object[][] tel = telefono;
        telefono = new Object[telefono.length][2];
         for(int i=0; i<telefono.length;i++){
             if(Objects.equals(numero,tel[i][0])){
                telefono[i][0]=null;
                telefono[i][1]=null;
             }else{
                 telefono[i][0]=tel[i][0];
                 telefono[i][1]=tel[i][1];
             }
         }
        tel = telefono;
        telefono = new Object[telefono.length-1][2];

        int i=0, j=0;
        while(i<tel.length){
            if(tel[i][1]==null){
                i++;
            }else{
                telefono[j][0]=tel[i][0];
                telefono[j][1]=tel[i][1];
                i++;j++;
            }
        }
        
        Object[][] aux = new Object[telefono.length][2];
        for(int k=0;k<telefono.length;k++){
            aux[k][0]=telefono[k][1];
            aux[k][1]=telefono[k][0];
        }  
        
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    aux,new String[] {"",""}));
    }//GEN-LAST:event_jBQuitarTelActionPerformed

    private void jTDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTDniKeyReleased

    }//GEN-LAST:event_jTDniKeyReleased

    private void jTDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTDniKeyTyped
        if((evt.getKeyChar()>'9' || evt.getKeyChar()<'0') && (evt.getKeyChar() != evt.VK_BACK_SPACE))
            evt.consume();
        if(jTDni.getText().length()>7)
            evt.consume();
        
    }//GEN-LAST:event_jTDniKeyTyped

    private void jTPaisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTPaisKeyTyped
        if((evt.getKeyChar()>'9' || evt.getKeyChar()<'0') && (evt.getKeyChar() != evt.VK_BACK_SPACE))
            evt.consume();
    }//GEN-LAST:event_jTPaisKeyTyped

    private void jTAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAreaKeyTyped
        if((evt.getKeyChar()>'9' || evt.getKeyChar()<'0') && (evt.getKeyChar() != evt.VK_BACK_SPACE))
            evt.consume();
    }//GEN-LAST:event_jTAreaKeyTyped

    private void jTtelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTtelefonoKeyTyped
        if((evt.getKeyChar()>'9' || evt.getKeyChar()<'0') && (evt.getKeyChar() != evt.VK_BACK_SPACE))
            evt.consume();
    }//GEN-LAST:event_jTtelefonoKeyTyped

    private void JB_CajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CajaActionPerformed
        try {
            CajaDAO caja = new CajaDAO();
            if(!caja.primera()){
                Abrir_caja ventana;
                ventana = new Abrir_caja(cuentaOficial);
                JF_Menu_admin.setVisible(false);
                ventana.setVisible(true);
            }else{
                if(!caja.seba()){
                    Abrir_caja ventana;
                    ventana = new Abrir_caja(cuentaOficial);
                    JF_Menu_admin.setVisible(false);
                    ventana.setVisible(true);
                }else{
                    Cierre_liquidacion ventana;
                    ventana = new Cierre_liquidacion(cuentaOficial);
                    JF_Menu_admin.setVisible(false);
                    ventana.setVisible(true);
                }
            }
            
        
        } catch (DataAccessException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_JB_CajaActionPerformed

    private void jBExaminarFotoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBExaminarFotoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            JFileChooser j=new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
            j.setFileFilter(filtro);
            j.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int estado=j.showOpenDialog(null);
            if(estado== JFileChooser.APPROVE_OPTION){
                String exten="";
                for (int i=j.getSelectedFile().getName().length()-1; i>=0; i--)
                    exten=exten+j.getSelectedFile().getName().charAt(i);
                String extension ="";
                for (int k=0; k<4; k++)
                    extension=extension+exten.charAt(k);

                if(extension.toLowerCase().equals("gnp.") || extension.toLowerCase().equals("gpj.") || extension.toLowerCase().equals("gepj") || extension.toLowerCase().equals("fig.")){
                    try{
                        fis=new FileInputStream(j.getSelectedFile());
                        this.longitudBytes=(int)j.getSelectedFile().length();
                        try {
                            Image icono=ImageIO.read(j.getSelectedFile()).getScaledInstance
                                    (foto_usuario.getWidth(),foto_usuario.getHeight(),Image.SCALE_DEFAULT);
                            foto_usuario.setIcon(new ImageIcon(icono));
                            foto_usuario.updateUI();
                        }catch (IOException ex) {
                            JOptionPane.showMessageDialog(rootPane, "imagen: "+ex);
                        }
                    }catch(FileNotFoundException ex){
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Solo se permiten archivos del tipo .png, .gif y .jpg","No se pudo agregar la imagen",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBExaminarFotoKeyPressed

    private void jBAgregarTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBAgregarTelKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){        
            if(jTtelefono.getText().isEmpty() || jTPais.getText().isEmpty() || jTArea.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "No se ingreso un numero de telefono válido.");
                return;
            }
            if(telefono.length==0){
               telefono = new Object[telefono.length+1][2];
               telefono[0][0]="+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText();
               telefono[0][1]=jTDescripcion.getText();
            }else{
                for(int i=0;i<telefono.length;i++){
                    if(Objects.equals("+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText(),telefono[i][0])){
                        JOptionPane.showMessageDialog(rootPane, "El telefono "+"+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText()+
                                ".\nYa ha sido ingresado.");
                        return; 
                    }
                }

                Object[][] tel = telefono;

                telefono = new Object[telefono.length+1][2];
                for(int i=0; i<telefono.length;i++){

                    if(i==telefono.length-1){
                        telefono[i][0]="+"+jTPais.getText()+jTArea.getText()+jTtelefono.getText();
                        telefono[i][1]=jTDescripcion.getText();
                    }else{
                        telefono[i][1]=tel[i][1];
                        telefono[i][0]=tel[i][0];
                    }    
                }
            }

            Object[][] aux = new Object[telefono.length][2];
            for(int i=0;i<telefono.length;i++){
                aux[i][0]=telefono[i][1];
                aux[i][1]=telefono[i][0];
            }

            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                            aux,new String[] {"",""}));
            jTDescripcion.setText("");
            jTPais.setText("54");
            jTArea.setText("383");
            jTtelefono.setText("");
        }
    }//GEN-LAST:event_jBAgregarTelKeyPressed

    private void jBRegistrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBRegistrarKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(jTApellido.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ingresó un APELLIDO de usuario.");
                return;
            }if(jTNombre.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ingresó un NOMBRE de usuario.");
                return;
            }if(jTDni.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ingresó número de DOCUMENTO de usuario.");
                return;
            }if(jTContraseña.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ingresó una CONTRASEÑA de usuario.");
                return;
            }        
            Usuario user = new Usuario();
            int dni; 

            try{
                dni = Integer.parseInt(jTDni.getText().trim());
                user.setDni(dni);
                user.setNombre(jTNombre.getText().trim());
                user.setApellido(jTApellido.getText().trim());
                user.setPass(jTContraseña.getText().trim());
                user.setCargo(jCCargo.getSelectedIndex());
                user.setActivo(true); 
                user.setFis(fis);
                user.setLongitud(longitudBytes);        
                GestorUsuario gesus = new GestorUsuario();         
                Telefono_UsuarioDAO tel = new Telefono_UsuarioDAO();
                UsuarioDAO usuario = new UsuarioDAO();
                int codigo = usuario.lastUser();
                Object[][] aux = new Object[telefono.length][3];
                for(int i=0; i<telefono.length;i++){
                    aux[i][0]=codigo;
                    aux[i][1]=telefono[i][0];
                    aux[i][2]=telefono[i][1]; 
                }
                tel.agregarGabe(aux);
                gesus.agregarNuevoUsuario(user); 


                try {
                    clearAgregar();
                }catch (DataAccessException ex) {Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }            
            }catch(NumberFormatException ex ){JOptionPane.showMessageDialog(null, "Solo debe ingresar numeros en el DOCUMENTO.");
            }catch(TelefonoUsuarioInexistenteException ex){JOptionPane.showMessageDialog(null, ex.getMessage());
            }catch(DataAccessException ex){JOptionPane.showMessageDialog(null, "Error al Insertar "+ex);
            }catch(UsuarioExistenteException ex){JOptionPane.showMessageDialog(null, "EL DOCUMENTO de usuario ya fue registrado.");
            JOptionPane.showMessageDialog(null, "El usuario fue agregado con éxito.");
            }         
        }
    }//GEN-LAST:event_jBRegistrarKeyPressed

    private void JB_EstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_EstadisticasActionPerformed
        JF_Menu_admin.setVisible(false);
        
        try {        
            this.h1.stop();
            Estadisticas est= new Estadisticas(cuentaOficial);
            est.mostrar(true);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JB_EstadisticasActionPerformed

    private void JB_PedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PedidosActionPerformed
        JF_Menu_admin.setVisible(false);
        this.h1.stop();
        Listado_pedidos_admin vent = new Listado_pedidos_admin(cuentaOficial);
        vent.mostrar(true);
        
    }//GEN-LAST:event_JB_PedidosActionPerformed

    private void JTF_IdUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTF_IdUserKeyTyped
        if((evt.getKeyChar()>'9' || evt.getKeyChar()<'0') && (evt.getKeyChar() != evt.VK_BACK_SPACE))
            evt.consume();
    }//GEN-LAST:event_JTF_IdUserKeyTyped

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
                try {
                    new Principal().setVisible(true);
                } catch (DataAccessException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JLabel JL_Hora;
    private javax.swing.JLabel JL_Hora_Admin;
    private javax.swing.JLabel JL_Hora_Admin1;
    private javax.swing.JLabel JL_Usuario_admin;
    private javax.swing.JLabel JL_Usuario_admin1;
    private javax.swing.JPasswordField JPF_PassUser;
    private javax.swing.JTextField JTF_IdUser;
    private javax.swing.JLabel Regla1;
    private javax.swing.JLabel Regla2;
    private javax.swing.JLabel foto_usuario;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTArea;
    private javax.swing.JTextField jTContraseña;
    private javax.swing.JTextField jTDescripcion;
    private javax.swing.JTextField jTDni;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTPais;
    private javax.swing.JTextField jTUsuario;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTtelefono;
    // End of variables declaration//GEN-END:variables

 @Override
    public void run() {
        Fecha f = new Fecha();
        fecha=f;
        Thread ct = Thread.currentThread();
        while(ct==h1){
            this.JL_Fecha.setText(fecha.getFecha());
            this.JL_Hora.setText(fecha.getHora());

            this.JL_Fecha_Admin.setText(fecha.getFecha());
            this.JL_Hora_Admin.setText(fecha.getHora());

            this.JL_Fecha_Admin1.setText(fecha.getFecha());
            this.JL_Hora_Admin1.setText(fecha.getHora());
        }
    }
}
