package com.util.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private JMenu fileMenu = new JMenu("file");
	private JMenuItem openItem = new JMenuItem("open");
	
	public MenuBar(){
		this.add(fileMenu);
		fileMenu.add(openItem);
	}
}
