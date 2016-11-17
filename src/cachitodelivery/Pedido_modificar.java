/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;
import Ventana_clases.Fondo_caja_liquidacion;
import Ventana_clases.Fondo_listado_pedidos_admin;
import Ventana_clases.Fondo_listado_pedidos_cajero;
import Ventana_clases.Fondo_pedido_modificar;
import Ventana_clases.Fondo_pedido_nuevo;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelos.Cadena;
import modelos.Cliente;
import modelos.ClienteDAO;
import modelos.Fecha;
import modelos.Pedido;
import modelos.Usuario;
import modelos.UsuarioDAO;

/**
 *
 * @author Cusipuma
 */
public class Pedido_modificar extends javax.swing.JFrame implements Runnable{
    
    Thread h1;
    Fecha fecha = new Fecha();
    Usuario cuentaOficial = new Usuario();
    Cliente clientePedido = new Cliente();
    Pedido ped = new Pedido();
    Object[][] menu;
    Object[][] comidas = new Object[0][2];
    float total=0;
    int demora=0;
    
    public Pedido_modificar(Usuario user, Pedido ped) throws DataAccessException, ClassNotFoundException, SQLException {
        this.ped=ped;
        initComponents();
        h1= new Thread(this);
        h1.start();
        Fondo_pedido_nuevo fondo = new Fondo_pedido_nuevo (1028,600);
        this.add(fondo, BorderLayout.CENTER);
        ClienteDAO dao = new ClienteDAO();
        Cliente cli = dao.buscarClienteCod(ped.getCod_cliente());
        JL_Usuario_admin1.setText("USUARIO: "+user.getApellido().trim()+" "+user.getNombre().trim());
        String nombre ="APPELIDO Y NOMBRE: "+cli.getApellido().trim()+" "+cli.getNombre().trim();
        String domicilio= "DOMICILIO: "+cli.getCalle().trim()+" "+cli.getNumero_calle().trim()+" "+
                cli.getBarrio().trim()+" "+cli.getCasa().trim()+" "+cli.getDepartamento().trim()+" "+cli.getLocalidad().trim();
        Cadena cadena = new Cadena();
        jLabel14.setText(cadena.limitar(nombre, 40));
        jLabel15.setText(cadena.limitar(domicilio, 50));
        cuentaOficial = user;
        clientePedido = cli;
        iniciarPedido();
    }
    
    public void mostrar(boolean b){
        setSize(1028,630);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Nuevo pedido");
        setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        setVisible(b);
    }
    
    public void menu(){
        try{
            ClienteDAO user =new ClienteDAO();
            Object [][] real = new Object[13][4];
            
            Object [][] n = user.comidasMenu();
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
            
            menu=real;
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void iniciarListado() {
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);

        jTable1.setTableHeader(null);
        

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                menu,new String[] {"","","",""}));

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
    }
    
       
    
    public void seleccionarTabla1(){
        if (jTable1.getSelectedRow()==-1 || jTable1.getValueAt(jTable1.getSelectedRow(),0)==null){
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
                return; 
            }
        jButton3.setEnabled(true);
        jButton4.setEnabled(false);
    }
    
        public void seleccionarTabla2(){
        if (jTable2.getSelectedRow()==-1){
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
                return; 
            }
        jButton4.setEnabled(true);
        jButton3.setEnabled(false);
    }
        public void iniciarPedido() throws ClassNotFoundException, SQLException{
            ClienteDAO cliente = new ClienteDAO();
            comidas=cliente.obtenerDetalle(ped.getCod_detalle());
            total=0;
            demora=0;
            for(int i=0;i<comidas.length;i++){
                total=total+(cliente.obtenerPrecio(cliente.obtenerCodComida(comidas[i][0].toString().trim()))*Integer.parseInt(comidas[i][1].toString().trim()));
                demora=demora+(cliente.obtenerDemora(cliente.obtenerCodComida(comidas[i][0].toString().trim()))*Integer.parseInt(comidas[i][1].toString().trim()));
            }
            float tarifa=cliente.obtenerTarifa(ped.getCod_cliente());
            jLabel9.setText("SUBTOTAL: "+total);
            jLabel10.setText("TOTAL: "+(total+tarifa));
            jLabel11.setText("DEMORA: "+demora);
            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        comidas,new String[] {"",""}));
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JL_Usuario_admin1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        JL_Fecha_Admin1 = new javax.swing.JLabel();
        JL_Hora_Admin1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        JL_Usuario_admin1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Usuario_admin1.setForeground(new java.awt.Color(255, 255, 255));
        JL_Usuario_admin1.setText("USUARIO: ");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;}
        };
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        menu();
        iniciarListado();

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Buscar.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Buscar_hover.png"))); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Agregar.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Agregar_hover.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Quitar_hover.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int columnIndex){
                return false;}
        };
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable2.setPreferredSize(new java.awt.Dimension(310, 64));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.setTableHeader(null);
        try{iniciarPedido();}
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(53, 95, 123));
        jLabel9.setText("SUBTOTAL: 1000,41");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(53, 95, 123));
        jLabel10.setText("TOTAL: 1000,41");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(53, 95, 123));
        jLabel11.setText("DEMORA: 15 min");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Guardar2.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Guardar2_hover.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(53, 94, 122));
        jLabel14.setText("APELLIDO Y NOMBRE:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(53, 94, 122));
        jLabel15.setText("DOMICILIO:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton6)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jButton7)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Fecha_Admin1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JL_Hora_Admin1))
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 217, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(526, 526, 526)
                            .addComponent(jButton1))
                        .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(581, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton6)))
                                        .addGap(0, 9, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Hora_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_Fecha_Admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(533, 533, 533))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(JL_Usuario_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(92, 92, 92)
                    .addComponent(jButton1)
                    .addContainerGap(949, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Listado_pedidos_admin vent1;
        Listado_pedidos_cajero vent2;
        if(cuentaOficial.getCargo()==0){
            vent1 = new Listado_pedidos_admin(cuentaOficial);
            vent1.mostrar(true);
        }
        else{
            vent2 = new Listado_pedidos_cajero(cuentaOficial);
            vent2.mostrar(true);
        }            
        this.h1.stop();
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
                try {
            
            if(comidas.length==0){
                JOptionPane.showMessageDialog (null, "No se agregaron comidas", "Pedido sin comidas", JOptionPane.ERROR_MESSAGE); 
                return;
            }
 
            ClienteDAO cliente = new ClienteDAO();
            int cod_detalle=ped.getCod_detalle();
            
            Object[][] aux = new Object[comidas.length][3];
            
            for(int i=0; i<comidas.length;i++){
                aux[i][0]=cod_detalle;
                aux[i][1]=cliente.obtenerCodComida(comidas[i][0].toString());
                aux[i][2]=comidas[i][1]; 
            }
            cliente.borrarDetalle(cod_detalle);
            cliente.insertarDetalle(aux);
            
            cliente.actualizarComidas(menu);
          
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataAccessException ex) {
            Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(jButton6,"Pedido modificado con exito");

        Listado_pedidos_admin vent1;
        Listado_pedidos_cajero vent2;
        if(cuentaOficial.getCargo()==0){
            vent1 = new Listado_pedidos_admin(cuentaOficial);
            vent1.mostrar(true);
        }
        else{
            vent2 = new Listado_pedidos_cajero(cuentaOficial);
            vent2.mostrar(true);
        }            
        setVisible(false);
        this.h1.stop();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        seleccionarTabla1();
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        seleccionarTabla1();
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        seleccionarTabla2();
    }//GEN-LAST:event_jTable2KeyReleased

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        seleccionarTabla2();
    }//GEN-LAST:event_jTable2MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        String seleccion = JOptionPane.showInputDialog(jButton3,"Cantidad de comidas",1);
        
        if(seleccion==null)
            return;
        
        int cant=0;
        try{
            cant = Integer.parseInt(seleccion);
        }
        catch(NumberFormatException e){ 
            JOptionPane.showMessageDialog (null, "El valor ingresado no es un número entero", "Valor invalido", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        int cantMenu=(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()))-cant;
        if(cantMenu<0){
            JOptionPane.showMessageDialog (null, "Cantidad insuficiente", "Valor invalido", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        if(comidas.length==0){
           comidas = new Object[comidas.length+1][2];
           comidas[0][0]=jTable1.getValueAt(jTable1.getSelectedRow(), 1);
           comidas[0][1]=cant;    
        }
        else{
            
            for(int i=0; i<comidas.length;i++){
                if(comidas[i][0].toString().trim().equals(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString().trim())){
                    JOptionPane.showMessageDialog (null, "Comida ya agregada", "Comida repetida", JOptionPane.ERROR_MESSAGE); 
                    return;
                }       
            }
            
            Object[][] com = comidas;

            comidas = new Object[comidas.length+1][2];
            
            for(int i=0; i<comidas.length;i++){
                
                if(i==comidas.length-1){
                    comidas[i][0]=jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                    comidas[i][1]=cant;
                }else{
                    comidas[i][1]=com[i][1];
                    comidas[i][0]=com[i][0];
                }    
            }
        }
        
        total=total+(Float.parseFloat(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString()))*cant;
        jLabel9.setText("SUBTOTAL: "+total);
        ClienteDAO cliente = new ClienteDAO();
        try {
            float tarifa=cliente.obtenerTarifa(clientePedido.getCodigo());
            int dem=cliente.obtenerDemora(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim()))*cant;
            demora=demora+dem;
            jLabel10.setText("TOTAL: "+(total+tarifa));
            jLabel11.setText("DEMORA: "+demora);
        } catch (SQLException ex) {
            Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for(int i=0; i<menu.length;i++)
         if(menu[i][0] == jTable1.getValueAt(jTable1.getSelectedRow(), 0))
             menu[i][3]=cantMenu; 
        
        iniciarListado();
                
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        comidas,new String[] {"",""}));
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(
        jButton4,
        "¿Quitar la comida seleccionada?");

     if (JOptionPane.OK_OPTION != confirmado)
        return;

    
            Object[][] com = comidas;
            comidas = new Object[com.length-1][2];
            int cant = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
            int j=0;
            int k=0;
            for(int i=0; i<com.length;i++){
                if(com[i][0].toString().trim().equals(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString().trim())){
                    j++;
                }else{
                    comidas[k][1]=com[i][1];
                    comidas[k][0]=com[i][0];
                    k++;
                }    
            }
            
            int codigo=0;

            for(int i=0; i<menu.length;i++){
                if(menu[i][1].toString().trim().equals(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString().trim())){
                    menu[i][3]=Integer.parseInt(menu[i][3].toString())+cant;
                    codigo=Integer.parseInt(menu[i][0].toString().trim());
                    i=menu.length;
                }
                    
            }
            iniciarListado();
            ClienteDAO cliente = new ClienteDAO();
            float precio=0;
            if(comidas.length>0){
                try {

                precio=cliente.obtenerPrecio(cliente.obtenerCodComida(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString().trim()));
                total=total-precio*cant;
                jLabel9.setText("SUBTOTAL: "+total);

                float tarifa=cliente.obtenerTarifa(clientePedido.getCodigo());
                int dem=cliente.obtenerDemora(codigo)*cant;   //pasar codigo comida
                demora=demora-dem;
                jLabel10.setText("TOTAL: "+(total+tarifa));
                jLabel11.setText("DEMORA: "+demora);
                } catch (SQLException ex) {
                    Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Pedido_nuevo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                jLabel9.setText("SUBTOTAL: ");
                jLabel10.setText("TOTAL: ");
                jLabel11.setText("DEMORA: ");
            }
 
            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        comidas,new String[] {"",""}));
         
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Pedido_modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pedido_modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pedido_modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pedido_modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Listado_pedidos_admin().setVisible(true);
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
 @Override
    public void run() {
        Fecha f = new Fecha();
        fecha=f;
        Thread ct = Thread.currentThread();
        while(ct==h1){
            JL_Fecha_Admin1.setText(fecha.getFecha());
            JL_Hora_Admin1.setText(fecha.getHora());
        }
    }

}
