package com.util.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener {
	
	private JButton dirbutton = new JButton("选择导入目录");
	private JButton impButton = new JButton("导  入");
	private MainFrame mf ;
	
	public ToolBar(MainFrame mf){
		this.mf = mf;
		dirbutton.addActionListener((ActionListener) this);
		impButton.addActionListener(this);
		this.add(dirbutton);
		this.add(impButton);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dirbutton){
			 JFileChooser chooser = new JFileChooser(".");
			 chooser.setDialogTitle("选择需要导入文件的目录");
			 
			 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int returnVal = chooser.showOpenDialog(null);
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
				String startDir = chooser.getSelectedFile().getAbsolutePath();
				System.out.println("====:"+startDir);
				FileBrower.fileBrower(startDir);
				mf.mainPane.tablePane.fillTable(FileBrower.map);
			
			 }
		}
	  
		else if(impButton == e.getSource()){
			new Thread(){
				public void run(){
		        	try {
				
				      FileBrower.importDB();
			          } catch (SQLException e1) {
				      // TODO Auto-generated catch block
				       e1.printStackTrace();
			          } catch (IOException e1) {
			        	// TODO Auto-generated catch block
			      	  e1.printStackTrace();
			         }
			}}.start();
		}
	}

}
