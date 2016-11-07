/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;


import Excepciones.DataAccessException;
import Ventana_clases.Fondo_listado_empleados;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelos.Usuario;
import modelos.UsuarioDAO;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Fecha;
import modelos.Telefono_UsuarioDAO;

/**
 *
 * @author Cusipuma
 */
public class Listado_empleados extends javax.swing.JFrame {
    
    Usuario cuentaOficial = new Usuario();
    Principal vent = null;
    Fecha fecha = new Fecha();
    Render r = new Render();
    
    public Listado_empleados(Principal vt) throws SQLException {
        initComponents();
        Fondo_listado_empleados fondo = new Fondo_listado_empleados(1000,559);
        add(fondo, BorderLayout.CENTER);
        vent = vt;
        cuentaOficial= vt.cuentaOficial;
        JL_Usuario_admin1.setText("USUARIO: "+vent.cuentaOficial.getApellido()+" "+vent.cuentaOficial.getNombre());
        JL_Fecha_Admin1.setText(fecha.getFecha());
        JL_Hora_Admin1.setText(fecha.getHora());
        
        

    }
    public void mostrar(boolean b){
        setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        setSize(1000,582);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Listado de empleados");
               
        setVisible(b);
    }
    
    public void clearBusqueda(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        clearTel();
    }
    
    public void clearTel(){
        Object[][] tel=null;
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                        tel, new String[] {"",""}));
    }
    
    public void iniciarListado() {
        jBModificarUsuario.setEnabled(false);
        jBEliminarUsuario.setEnabled(false);

        
        jRadioButton1.setSelected(false);
        
        jTable1.setTableHeader(null);
        jLabel7.setText("USUARIO NO SELECCIONADO");
                jLabel8.setText("");
                jLabel9.setText("");
                jLabel10.setText("");
                JL_Foto_empleado.setIcon(null);
        try{
            UsuarioDAO user =new UsuarioDAO();
            Object [][] real = new Object[13][4];
            
            Object [][] n = user.listadoUsuariosActivo();
            if (n.length<13){
                int i=0;
                for (i=0; i<n.length; i++){
                    real[i][0]=n[i][0];
                    real[i][1]=n[i][1];
                    real[i][2]=n[i][2];
                    real[i][3]=n[i][3];
                    
                }
                for (i=n.length; i<13; i++){
                    real[i][0]=null;
                    real[i][1]=null;
                    real[i][2]=null;
                    real[i][3]=null;
                }      
            }else
                real=n;
            
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    real,new String[] {"","","",""}));
            
            jTable1.setDefaultRenderer(Object.class, r);
            
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(166);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(166);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void checkListado(boolean b) throws DataAccessException {
        jTable1.setTableHeader(null);
        jLabel7.setText("USUARIO NO SELECCIONADO");
                jLabel8.setText("");
                jLabel9.setText("");
                jLabel10.setText("");
                JL_Foto_empleado.setIcon(null);
        try{
            UsuarioDAO user =new UsuarioDAO();
            Object [][] real = new Object[13][4];
            Object [][] n;
            
            
            if(b==true)
                n = user.listadoUsuariosCusi();
            else
                n = user.listadoUsuariosActivo();
            
                
            if (n.length<13){
                int i=0;
                for (i=0; i<n.length; i++){
                    real[i][0]=n[i][0];
                    real[i][1]=n[i][1];
                    real[i][2]=n[i][2];
                    real[i][3]=n[i][3];
                    
                }
                for (i=n.length; i<13; i++){
                    real[i][0]=null;
                    real[i][1]=null;
                    real[i][2]=null;
                    real[i][3]=null;
                }      
            }else
                real=n;
            
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    real,new String[] {"","","",""}));

            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(166);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(166);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private void headerTel(){
        jTable3.setTableHeader(null);
    }
    
    private void iniciarTel() throws SQLException, ClassNotFoundException{
        jTable3.setTableHeader(null);
        if(jTable1.getSelectedRow()==-1)
            return;
        Telefono_UsuarioDAO telefonos = new Telefono_UsuarioDAO();
        Object[][] tel = telefonos.devolverTel(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()));
        
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                        tel, new String[] {"",""}));
    }
    
    private void seleccionarTabla(){
        try{
            if (jTable1.getSelectedRow()==-1){
                Object[][] tel =null;
                jTable3.setModel(new javax.swing.table.DefaultTableModel(
                        tel, new String[] {"",""}));
                jBModificarUsuario.setEnabled(false);
                jBEliminarUsuario.setEnabled(false);
                return; 
            }
            if (jTable1.getValueAt(jTable1.getSelectedRow(),0)==null){
                Object[][] tel =null;
                jTable3.setModel(new javax.swing.table.DefaultTableModel(
                        tel, new String[] {"",""}));
                jBModificarUsuario.setEnabled(false);
                jBEliminarUsuario.setEnabled(false);
                jLabel7.setText("USUARIO NO SELECCIONADO");
                jLabel8.setText("");
                jLabel9.setText("");
                jLabel10.setText("");
                JL_Foto_empleado.setIcon(null);
                jBModificarUsuario.setEnabled(false);
                jBEliminarUsuario.setEnabled(false);                
            }else{
                UsuarioDAO users = new UsuarioDAO();
                Usuario user = null;
                user=users.buscarUsuarioCod(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()));
                jLabel7.setText("APELLIDOS: "+user.getApellido());
                jLabel8.setText("NOMBRES: "+user.getNombre());
                jLabel9.setText("D.N.I.: "+user.getDni());
                if (user.getCargo()==1)
                    jLabel10.setText("CARGO: Cajero");
                else
                    jLabel10.setText("CARGO: Aministrador");
                
                if(user.getCodFoto()==null)
                    JL_Foto_empleado.setIcon(null);
                else{
                    InputStream binario;
                    ImageIcon foto;
                
                    binario=user.getCodFoto();


                    BufferedImage bi = ImageIO.read(binario);
                    foto = new ImageIcon(bi);


                    Image img = foto.getImage();
                    Image newimg = img.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);

                    ImageIcon newicon = new ImageIcon(newimg);


                    JL_Foto_empleado.setIcon(newicon);
                }
                iniciarTel();
                
                if(jTable1.getValueAt(jTable1.getSelectedRow(),3)=="Eliminado"){
                    jBModificarUsuario.setEnabled(false);
                    jBEliminarUsuario.setIcon(new ImageIcon(getClass().getResource("/Botones/reac.png")));
                    jBEliminarUsuario.setRolloverIcon(new ImageIcon(getClass().getResource("/Botones/reac_hover.png")));
                    jBEliminarUsuario.setEnabled(true);
                }else{
                    jBModificarUsuario.setEnabled(true);
                    jBEliminarUsuario.setIcon(new ImageIcon(getClass().getResource("/Botones/Eliminar.png")));
                    jBEliminarUsuario.setRolloverIcon(new ImageIcon(getClass().getResource("/Botones/Eliminar_hover.png")));
                    jBEliminarUsuario.setEnabled(true);
                }
                
                
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private void buscar(){
        jTable1.setTableHeader(null);
        try{
            UsuarioDAO user =new UsuarioDAO();
            Object [][] real = new Object[13][4];
            boolean bo = jRadioButton1.isSelected();
            
            Object [][] n = user.buscarUsuarioText(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText(),bo);
            if (n.length<13){
                int i=0;
                for (i=0; i<n.length; i++){
                    real[i][0]=n[i][0];
                    real[i][1]=n[i][1];
                    real[i][2]=n[i][2];
                    real[i][3]=n[i][3];
                    
                }
                for (i=n.length; i<13; i++){
                    real[i][0]=null;
                    real[i][1]=null;
                    real[i][2]=null;
                    real[i][3]=null;
                }      
            }else
                real=n;
                
                
            
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    real,new String[] {"","","",""}));
            
            jBModificarUsuario.setEnabled(false);
            jBEliminarUsuario.setEnabled(false);
            
            jTable1.changeSelection(0, 0, false, false);
            seleccionarTabla();
            
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(166);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(166);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        JL_Usuario_admin1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jBEliminarUsuario = new javax.swing.JButton();
        jBModificarUsuario = new javax.swing.JButton();
        jBAgregarUsuario = new javax.swing.JButton();
        jBAgregarCadete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JL_Foto_empleado = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        JL_Fecha_Admin1 = new javax.swing.JLabel();
        JL_Hora_Admin1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        JL_Usuario_admin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Usuario_admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Usuario_admin1.setText("USUARIO:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;}
        };
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "Título 3", "Título 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }
        iniciarListado();

        jTable3 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;}
        };
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
            }
        ));
        jScrollPane3.setViewportView(jTable3);
        headerTel();

        jTable4 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;}
        };
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Buscar.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Buscar_hover.png"))); // NOI18N
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        jBEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Eliminar.png"))); // NOI18N
        jBEliminarUsuario.setBorder(null);
        jBEliminarUsuario.setBorderPainted(false);
        jBEliminarUsuario.setContentAreaFilled(false);
        jBEliminarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBEliminarUsuario.setEnabled(false);
        jBEliminarUsuario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Eliminar_hover.png"))); // NOI18N
        jBEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarUsuarioActionPerformed(evt);
            }
        });

        jBModificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Modificar.png"))); // NOI18N
        jBModificarUsuario.setBorder(null);
        jBModificarUsuario.setBorderPainted(false);
        jBModificarUsuario.setContentAreaFilled(false);
        jBModificarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBModificarUsuario.setEnabled(false);
        jBModificarUsuario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Modificar_hover.png"))); // NOI18N
        jBModificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarUsuarioActionPerformed(evt);
            }
        });

        jBAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Usuario.png"))); // NOI18N
        jBAgregarUsuario.setBorder(null);
        jBAgregarUsuario.setBorderPainted(false);
        jBAgregarUsuario.setContentAreaFilled(false);
        jBAgregarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBAgregarUsuario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Usuario_hover.png"))); // NOI18N
        jBAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarUsuarioActionPerformed(evt);
            }
        });

        jBAgregarCadete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Cadete.png"))); // NOI18N
        jBAgregarCadete.setBorder(null);
        jBAgregarCadete.setBorderPainted(false);
        jBAgregarCadete.setContentAreaFilled(false);
        jBAgregarCadete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBAgregarCadete.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Cadete_hover.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(53, 94, 122));
        jLabel7.setText("USUARIO NO SELECCIONADO");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(53, 94, 122));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(53, 94, 122));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(53, 94, 122));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estado.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setEnabled(false);
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estado_hover.png"))); // NOI18N

        jRadioButton1.setBorder(null);
        jRadioButton1.setContentAreaFilled(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        JL_Fecha_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Fecha_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Fecha_Admin1.setText("FF/FF/FFFF");

        JL_Hora_Admin1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JL_Hora_Admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Hora_Admin1.setText("HH:HH");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Atras2.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Atras2_hover.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jBEliminarUsuario)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jBModificarUsuario))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jBAgregarCadete)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jBAgregarUsuario))))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JL_Foto_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JL_Fecha_Admin1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_Hora_Admin1)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jButton6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(JL_Foto_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jBAgregarUsuario)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBModificarUsuario))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jBAgregarCadete)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBEliminarUsuario))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Hora_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_Fecha_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       vent.MenuAdminVisible(true, cuentaOficial);
       this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jBAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarUsuarioActionPerformed
         
        vent.AgregarUsVisible(true);
        this.setVisible(false);
       
    }//GEN-LAST:event_jBAgregarUsuarioActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        seleccionarTabla();
    }//GEN-LAST:event_jTable1MousePressed
    
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        seleccionarTabla();
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorMoved
        
    }//GEN-LAST:event_jTable1AncestorMoved

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased

    }//GEN-LAST:event_jButton1KeyReleased

    private void jBModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarUsuarioActionPerformed
        UsuarioDAO usuario = new UsuarioDAO();
        
        Modificar_usuario ventana;
        try {
            ventana = new Modificar_usuario(this,
                    usuario.buscarUsuarioCod(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString())));

            ventana.mostrar(true);
        this.setVisible(false);
        } catch (DataAccessException ex) {
            Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBModificarUsuarioActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        try {
            clearBusqueda();
            checkListado(jRadioButton1.isSelected());
        } catch (DataAccessException ex) {
            Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jBEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarUsuarioActionPerformed
        try {
            UsuarioDAO u = new UsuarioDAO();
            
            if(jTable1.getValueAt(jTable1.getSelectedRow(),3)=="Eliminado"){
                u.eliminar(true,Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()));
                JOptionPane.showMessageDialog(rootPane, "Usuario reactivado.","Alta usuario",JOptionPane.INFORMATION_MESSAGE);
            }else{
                UsuarioDAO users = new UsuarioDAO();
                Usuario user = null;
                user=users.buscarUsuarioCod(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()));
                
                int confirmado = JOptionPane.showConfirmDialog(rootPane,"El usuario "+user.getApellido()+" "+user.getNombre()
                        + " será eliminado\n\n¿Confirmar ésta acción?");

                if (JOptionPane.OK_OPTION == confirmado){
                    u.eliminar(false,Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()));
                    JOptionPane.showMessageDialog(rootPane, "El usuario fue eliminado con éxito..","Usuario eliminado",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            iniciarListado();
            clearBusqueda();
        } catch (DataAccessException ex) {
                Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_jBEliminarUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Listado_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listado_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listado_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listado_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new Listado_empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_Fecha_Admin1;
    private javax.swing.JLabel JL_Foto_empleado;
    private javax.swing.JLabel JL_Hora_Admin1;
    private javax.swing.JLabel JL_Usuario_admin1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jBAgregarCadete;
    private javax.swing.JButton jBAgregarUsuario;
    private javax.swing.JButton jBEliminarUsuario;
    private javax.swing.JButton jBModificarUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
