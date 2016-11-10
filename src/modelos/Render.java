/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Cusipuma
 */
public class Render extends DefaultTableCellRenderer{

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Color rojo = new Color(250,184,196);
        Color cel = new Color(169,211,254);
        Color rojo_claro = new Color(253,227,228);
        
        boolean valid = String.valueOf(table.getValueAt(row, 3)).equals("Eliminado");
        
        component.setBackground(valid ? rojo_claro : null);
        if(isSelected)
            component.setBackground(cel);
        if(isSelected && String.valueOf(table.getValueAt(row, 3)).equals("Eliminado"))
            component.setBackground(rojo);
        return component;
    }
    
    
}
