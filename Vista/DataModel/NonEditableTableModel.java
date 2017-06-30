/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.DataModel;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alulab14
 */
public class NonEditableTableModel extends DefaultTableModel {

    public NonEditableTableModel(String [][] data, String [] cols) {
        super(data, cols);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
    
}
