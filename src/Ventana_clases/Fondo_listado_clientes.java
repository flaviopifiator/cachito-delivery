package Ventana_clases;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo_listado_clientes extends JPanel{
    
    public Fondo_listado_clientes(int a, int b){
        this.setSize(a,b);
    }
    
    public void paintComponent(Graphics g){
        Dimension tam = getSize();
        ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/Ventanas/Listado_clientes.png")).getImage());
        g.drawImage(img.getImage(), 0, 0, tam.width, tam.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}