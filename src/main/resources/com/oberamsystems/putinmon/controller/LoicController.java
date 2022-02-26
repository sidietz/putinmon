package com.oberamsystems.putinmon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.oberamsystems.putinmon.loic.Loic;

public class LoicController implements ActionListener {
	
	private JTable table;
	private JLabel selected;

	public LoicController(JTable table, JLabel selected) {
        super();
        this.table = table;
        this.selected = selected;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		 int selectedRow = Integer.parseUnsignedInt(this.selected.getText());
		 
		 String Ipv4Addr = (String) table.getValueAt(selectedRow, 1);
		 String Ipv6Addr = (String) table.getValueAt(selectedRow, 2);
		 
		 Loic loic = new Loic(Ipv4Addr, Ipv6Addr);
		 String isOnline = loic.checkOnline();
		 
		 table.setValueAt(isOnline, selectedRow, 3);
		 
		 JOptionPane.showMessageDialog(null, "Status of " + loic.getIpAddress() +  "? " + isOnline, "Error", JOptionPane.ERROR_MESSAGE);

		 
	}

}
