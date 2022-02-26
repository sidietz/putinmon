package com.oberamsystems.putinmon.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.oberamsystems.putinmon.controller.LoicController;
import com.oberamsystems.putinmon.controller.SelectionController;

public class View {

	public View(JTable table) {

		JButton checkButton = new JButton("Check availability");
		JLabel selected = new JLabel("0");

		table.setCellSelectionEnabled(true);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		LoicController lc = new LoicController(table, selected);
		checkButton.addActionListener(lc);
		SelectionController controller = new SelectionController(table, selected);
		select.addListSelectionListener(controller);

		JPanel ctrlPane = new JPanel();
		ctrlPane.add(checkButton);
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(800, 450));
		tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Targets",
				TitledBorder.CENTER, TitledBorder.TOP));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
		splitPane.setDividerLocation(35);
		splitPane.setEnabled(false);

		JFrame frame = new JFrame("putinmon");

		JMenu menu = new JMenu("Help");
		menu.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				JOptionPane.showMessageDialog(null,
						"This program is intended to monitor the Anonymous actions against Russia.", "Help",
						JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				//
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				//
			}
		});

		JMenuBar mb = new JMenuBar();
		mb.add(menu);

		frame.setJMenuBar(mb);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(splitPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
