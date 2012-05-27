package com.util.gui;

import javax.swing.JFrame;

public class DelFrame extends JFrame {
   
	DelTablePanel delPanel = new DelTablePanel();
	
	public DelFrame(){
		this.setTitle("É¾³ý");
		
		this.getContentPane().add(delPanel);
	}
}
