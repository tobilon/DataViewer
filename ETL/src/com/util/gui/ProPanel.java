package com.util.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProPanel extends JPanel implements ActionListener{
	

	private JButton excuteButton = new JButton("go");
	public static JProgressBar progress = new JProgressBar();
    private JButton cancelButton= new JButton("cancel");	
    
    public ProPanel(){
    	
    	this.add(progress);
    	progress.setPreferredSize(new Dimension(600,30));
    	progress.setString("Ö´ÐÐ½ø¶È 0%");
    	progress.setStringPainted(true);
    	//this.add(excuteButton);
    	cancelButton.addActionListener(this);
    	this.add(cancelButton);
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancelButton){
			System.exit(0);
		}
		
	}
	
    
	

}
