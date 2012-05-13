package com.util.gui;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	
	private JButton bu = new JButton("open");
	
	public ToolBar(){
		this.add(bu);
	}

}
