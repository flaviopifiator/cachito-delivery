/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;
import Ventana_clases.Fondo_listado_pedidos_cajero;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelos.Cadena;
import modelos.CajaDAO;
import modelos.Cliente;
import modelos.Fecha;
import modelos.Pedido;
import modelos.PedidoDAO;
import modelos.Render;
import modelos.Usuario;

/**
 *
 * @author Cusipuma
 */
public class Listado_pedidos_cajero extends javax.swing.JFrame implements Runnable{
    
    Thread h1;
    Fecha fecha = new Fecha();
    Usuario cuentaOficial = new Usuario();
    Render r = new Render();
    
    public Listado_pedidos_cajero(Usuario user) {
        initComponents();
        h1= new Thread(this);
        h1.start();
        Fondo_listado_pedidos_cajero fondo = new Fondo_listado_pedidos_cajero(1028,600);
        this.add(fondo, BorderLayout.CENTER);
        cuentaOficial = user;
        JL_Usuario_admin1.setText("USUARIO: "+cuentaOficial.getApellido().trim()+" "+cuentaOficial.getNombre().trim());
    }
    
    public void mostrar(boolean b){
        setSize(1028,630);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Listado de pedidos actuales");
        setIconImage (new ImageIcon(getClass().getResource("/Ventanas/Icono.png")).getImage());
        setVisible(b);
    }
    
    public void iniciarListado() {
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jTable1.setTableHeader(null);
        
        jLabel14.setText("PEDIDO NO SELECCIONADO ");
        jLabel15.setText("");
        jLabel9.setText("");
        jLabel10.setText("");
        jLabel12.setText("");
        jLabel11.setText("");
        
        try{
            PedidoDAO pedido =new PedidoDAO();
            Object [][] real = new Object[15][4];
            
            Object [][] n = pedido.listadoPedidosActivos();
            if (n.length<15){
                int i=0;
                for (i=0; i<n.length; i++){
                    real[i][0]=n[i][0];
                    real[i][1]=n[i][1];
                    real[i][2]=n[i][2];
                    real[i][3]=n[i][3];
                    
                }
                for (i=n.length; i<15; i++){
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
    
    public void seleccionarTabla(){
        try{
            if (jTable1.getSelectedRow()==-1){
                Object[][] detalle =null;
                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        detalle, new String[] {"",""}));
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
                return; 
            }
            if (jTable1.getValueAt(jTable1.getSelectedRow(),0)==null){
                Object[][] detalle =null;
                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        detalle, new String[] {"",""}));
                jLabel14.setText("PEDIDO NO SELECCIONADO ");
                jLabel15.setText("");
                jLabel9.setText("");
                jLabel10.setText("");
                jLabel12.setText("");
                jLabel11.setText("");
                jTextArea2.setText("");
                jButton3.setEnabled(false);
                jButton4.setEnabled(false);
            }else{
                String estado=jTable1.getValueAt(jTable1.getSelectedRow(),3).toString();
                    
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);
                
                if(!estado.equals("En espera"))
                    jButton3.setEnabled(false);
                
                
                
                PedidoDAO pedidos = new PedidoDAO();
                Object[][] lista = pedidos.buscarPedidoCod(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString()),
                        jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());

                Cadena cad = new Cadena();
                jLabel14.setText("APELLIDOS Y NOMBRES: "+cad.limitar(lista[0][3].toString().trim(), 30));
                jLabel15.setText("DOMICILIO: "+cad.limitar(lista[0][4].toString().trim(), 40));
                
                int cod=Integer.parseInt(lista[0][1].toString());
                Object[][] comidas=pedidos.buscarComidasPedidoCod(cod);
                
                float total=0;
                int demora=0;
                for(int i=0; i<comidas.length;i++){
                    total=total+(Float.valueOf(comidas[i][2].toString())*Integer.parseInt(comidas[i][1].toString()));
                    demora=demora+(Integer.parseInt(comidas[i][3].toString().trim()));
                }
                
                jLabel9.setText("SUBTOTAL: "+total);
                jLabel10.setText("TOTAL: "+(total+Float.valueOf(lista[0][13].toString())));
                jLabel11.setText("DEMORA: "+demora);
                jLabel12.setText("HORA: "+lista[0][12]);
                jTextArea2.setText(lista[0][0].toString().trim());
                
                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    comidas,new String[] {"",""}));
                 jTable2.getColumnModel().getColumn(0).setPreferredWidth(250);
                 jTable2.getColumnModel().getColumn(1).setPreferredWidth(30);
            }
        }catch (DataAccessException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void buscar(){
        try{
            PedidoDAO pedidos = new PedidoDAO();
            Object [][] real = new Object[14][4];
            int tipo=0;
            
            Object [][] n = pedidos.buscarPedidoText(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText(),1);
            if (n.length<14){
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
            
            jButton3.setEnabled(false);
            jButton4.setEnabled(false);
            
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
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
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
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

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
        iniciarListado();

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Modificar.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Modificar_hover.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estado.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Estado_hover.png"))); // NOI18N
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
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ));
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.setTableHeader(null);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable2.setPreferredSize(new java.awt.Dimension(310, 64));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(53, 95, 123));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(53, 95, 123));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(53, 95, 123));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(53, 95, 123));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Ticket.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Ticket_hover.png"))); // NOI18N

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Pedido_nuevo.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Botones/Pedido_nuevo_hover.png"))); // NOI18N
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

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(53, 94, 122));
        jLabel14.setText("USUARIO NO SELECCIONADO");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(53, 94, 122));

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton6))
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(jButton7)
                                                .addGap(0, 0, Short.MAX_VALUE)))))))
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jButton5)
                                                    .addComponent(jButton6))))
                                        .addGap(0, 10, Short.MAX_VALUE))))))
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
     try {
            Menu_cajero vent;
            this.h1.stop();
            vent = new Menu_cajero(cuentaOficial);
            vent.setVisible(true);
        this.dispose();
        } catch (DataAccessException ex) {
            Logger.getLogger(Listado_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        CajaDAO caja = new CajaDAO();
        try {
            if(!caja.seba()){
                JOptionPane.showMessageDialog(jButton6,"Aun no se abrió la caja","Error al generar pedido",JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
        this.h1.stop();
        Listado_clientes vent = new Listado_clientes(cuentaOficial);
        vent.mostrar();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                PedidoDAO dao = new PedidoDAO();
        try {
            Pedido ped = dao.buscarPedido(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().trim()));
            setVisible(false);
            this.h1.stop();
            Pedido_modificar vent = new Pedido_modificar(cuentaOficial, ped);
            vent.mostrar(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataAccessException ex) {
            Logger.getLogger(Listado_pedidos_cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        seleccionarTabla();
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        seleccionarTabla();
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        buscar();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            if(jTable1.getValueAt(jTable1.getSelectedRow(), 3).equals("Listo para enviar")){
                    Asignar_pedido vent = new Asignar_pedido(cuentaOficial);
                    vent.mostrar(true);
                    this.dispose();
                    return;
                }
            
            int estado=0;
            if(jTable1.getValueAt(jTable1.getSelectedRow(), 3).equals("En espera"))
                estado=0;
            if(jTable1.getValueAt(jTable1.getSelectedRow(), 3).equals("En preparacion"))
                estado=1;             
            if(jTable1.getValueAt(jTable1.getSelectedRow(), 3).equals("Enviado"))
                estado=2;
            Object seleccion=null;
            switch (estado){
                case 0:{
                    seleccion = JOptionPane.showInputDialog(
                    jButton4,
                    "Seleccione el nuevo estado del pedido",
                    "Estados",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] { "En preparacion", "Listo para enviar", "Enviado", "Entregado", "No entregado", "Cancelado" }, 
                    "En preparacion");
                    break;
                    }
                case 1:{
                    seleccion = JOptionPane.showInputDialog(
                    jButton4,
                    "Seleccione el nuevo estado del pedido",
                    "Estados",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] { "Listo para enviar", "Enviado", "Entregado", "No entregado", "Cancelado" }, 
                    "Listo para enviar");
                    break;
                    }
                case 2:{
                    seleccion = JOptionPane.showInputDialog(
                    jButton4,
                    "Seleccione el nuevo estado del pedido",
                    "Estados",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] {"Entregado", "No entregado", "Cancelado" }, 
                    "Entregado");
                    break;
                    }
                }
            if(seleccion==null)
                return;
            if(seleccion.equals("En espera"))
                estado=0;
            if(seleccion.equals("En preparacion"))
                estado=1;
            if(seleccion.equals("Listo para enviar"))
                estado=2;
            if(seleccion.equals("Enviado"))
                estado=3;
            if(seleccion.equals("Entregado"))
                estado=4;
            if(seleccion.equals("No entregado"))
                estado=5;
            if(seleccion.equals("Cancelado"))
                estado=9;



            int cod= Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            PedidoDAO pedido = new PedidoDAO(); 
            pedido.actualizarEstadoPedido(cod, estado);
            iniciarListado();

       } catch (SQLException ex) {
           Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
       } catch (DataAccessException ex) {
            Logger.getLogger(Listado_pedidos_admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(rootPane, "Se ha actualizado satisfactoriamente\n el estado del pedido","Actualizar estado pedido",JOptionPane.INFORMATION_MESSAGE);     
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
            java.util.logging.Logger.getLogger(Listado_pedidos_cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listado_pedidos_cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listado_pedidos_cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listado_pedidos_cajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
