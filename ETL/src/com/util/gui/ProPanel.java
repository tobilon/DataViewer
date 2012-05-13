package com.util.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProPanel extends JPanel {
	
	private JButton excuteButton = new JButton("go");
	private JProgressBar progress = new JProgressBar();
    private JButton cancelButton= new JButton("cancel");	
    
    public ProPanel(){
    	
    	this.add(progress);
    	progress.setPreferredSize(new Dimension(300,30));
    	this.add(excuteButton);
    	this.add(cancelButton);
    }
	
	

}
