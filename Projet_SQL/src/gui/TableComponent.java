package gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//CTRL + SHIFT + O pour g�n�rer les imports
public class TableComponent extends DefaultTableCellRenderer {

public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
  //Si la valeur de la cellule est un JButton, on transtype cette valeur
  if (value instanceof JButton)
    return (JButton) value;
  else
    return this;
}
}