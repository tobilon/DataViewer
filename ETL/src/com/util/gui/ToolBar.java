package com.util.gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import com.util.db.DbConnTest;
import com.util.tools.Log;

public class ToolBar extends JToolBar implements ActionListener {
	
	private JButton dirbutton = new JButton("ѡ����Ŀ¼");
	private JButton delbutton = new JButton("ɾ �� ");
	private JButton impButton = new JButton("��  ��");
	private MainFrame mf ;
	
	public ToolBar(MainFrame mf){
		this.mf = mf;
		dirbutton.addActionListener((ActionListener) this);
		impButton.addActionListener(this);
		delbutton.addActionListener(this);
		
	    
		this.add(dirbutton);
		this.add(impButton);
		this.add(delbutton);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
         if(e.getSource() == delbutton){
        	 DelFrame df = new DelFrame();
        	 df.setSize(600,400);
        	 df.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        	 df.setVisible(true);
         }
         else if(e.getSource() == dirbutton){
			 JFileChooser chooser = new JFileChooser(".");
			 chooser.setDialogTitle("ѡ����Ҫ�����ļ���Ŀ¼");
			 
			 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int returnVal = chooser.showOpenDialog(null);
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
				String startDir = chooser.getSelectedFile().getAbsolutePath();
				System.out.println("====:"+startDir);
				Log.error("you choose the starting dir: "+startDir);
				FileBrower.fileBrower(startDir);
				mf.mainPane.tablePane.fillTable(FileBrower.map);
			
			 }
		}
	  
		else if(impButton == e.getSource()){
			new Thread(){
				public void run(){
		        	try {
		        	  String pro = "�������ݿ�����...";
		    		  mf.progress.progress.setString(pro);
		        	  if(false == DbConnTest.dbtest()){
		        		  System.out.println("1111111111");
		        		  JOptionPane.showMessageDialog(mf.getContentPane(),
		           		       "���ݿ����Ӵ��� !", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
		           	   return;
		        	  }
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
