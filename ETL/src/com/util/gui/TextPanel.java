package com.util.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class TextPanel extends JPanel {

	private JTextArea tx = new JTextArea();
	private JScrollPane js =new JScrollPane(tx);
	
	public TextPanel(){
		//js.add(tx);
		js.setPreferredSize(new Dimension(400,400));
	    tx.setText("test");
	    js.setBorder(new TitledBorder("Ö´ÐÐÇé¿ö"));
		this.add(js);
	}
	
	
	
}
