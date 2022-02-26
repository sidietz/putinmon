package com.oberamsystems.putinmon.controller;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionController implements ListSelectionListener {

	private JTable table;
	private JLabel selected;

	public SelectionController(JTable table, JLabel selected) {
		super();
		this.table = table;
		this.selected = selected;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting()) {
			int selectedRow = 0;
			int[] row = table.getSelectedRows();
			int[] columns = table.getSelectedColumns();
			for (int i = 0; i < row.length; i++) {
				for (int j = 0; j < columns.length; j++) {
					selectedRow = row[i];
				}
			}
			this.selected.setText(Integer.toString(selectedRow));
		}
	}

}
