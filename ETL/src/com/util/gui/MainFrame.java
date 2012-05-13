package com.util.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame {
	
	public static int width = 800;
	public static int height = 600;
	
	private MenuBar menuBar = new MenuBar();
	private MainPane mainPane = new MainPane();
	private ToolBar  toolBar = new ToolBar();
	private ProPanel progress = new ProPanel();
	
	public MainFrame(){
		
		this.setTitle("提取入库系统");
		this.setJMenuBar(menuBar);
		this.setLayout(new BorderLayout());
		
		Container cont = this.getContentPane();
		cont.add(toolBar,BorderLayout.NORTH);
		cont.add(mainPane,BorderLayout.CENTER);
		cont.add(progress,BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(width,height));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
	}
	

}
