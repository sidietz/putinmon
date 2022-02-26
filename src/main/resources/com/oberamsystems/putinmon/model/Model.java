package com.oberamsystems.putinmon.model;

import javax.swing.table.DefaultTableModel;

import com.oberamsystems.putinmon.Constants;

public class Model extends DefaultTableModel {

	private static final long serialVersionUID = -3243370514958445955L;

	public Model() {
		super(Constants.DATA, Constants.TABLE_HEADER);
	}
}