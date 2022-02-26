package com.oberamsystems.putinmon;

import java.awt.Color;
import java.awt.Component;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import com.oberamsystems.putinmon.model.Model;
import com.oberamsystems.putinmon.service.Consumer;
import com.oberamsystems.putinmon.service.Producer;
import com.oberamsystems.putinmon.service.TaskRecord;
import com.oberamsystems.putinmon.view.View;

public class Main {

	public static void main(String[] args) {

		Model model = new Model();
		JTable table = new JTable() {
			/**
			* 
			*/
			private static final long serialVersionUID = 210673343633087422L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);

				if (getValueAt(rowIndex, 3).toString().equalsIgnoreCase("online")) {
					component.setBackground(Color.GREEN);
				} else if (getValueAt(rowIndex, 3).toString().equalsIgnoreCase("offline")) {
					component.setBackground(Color.RED);
				}

				return component;
			}
		};

		table.setModel(model);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI(table);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		LinkedBlockingQueue<TaskRecord> workQueue = new LinkedBlockingQueue<TaskRecord>();
		LinkedBlockingQueue<TaskRecord> resultQueue = new LinkedBlockingQueue<TaskRecord>();

		Producer prod = new Producer(table, workQueue);
		Consumer con = new Consumer(table, workQueue, resultQueue);

		new Thread(prod).start();
		new Thread(con).start();
	}

	public static void createAndShowGUI(JTable table) throws Exception {
		new View(table);
	}
}
