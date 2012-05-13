package com.util.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPane extends JPanel {

	private JLabel logLabel = new JLabel("result");
	
	public ResultPane(){
		this.setPreferredSize(new Dimension(380,400));
		this.add(logLabel);
	}
	
	
}
