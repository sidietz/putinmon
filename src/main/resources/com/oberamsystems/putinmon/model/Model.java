package com.oberamsystems.putinmon.model;

import javax.swing.table.DefaultTableModel;

import com.oberamsystems.putinmon.Constants;

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {
 
    public Model() {
        super(Constants.DATA, Constants.TABLE_HEADER);
    }
 
}