package com.util.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;

public class MainPane extends JPanel {
	
	 TablePanel tablePane = new TablePanel();
	 ResultPane resultPane = new ResultPane();
	 JSplitPane split = new JSplitPane();
	
	public MainPane(){
		//this.setPreferredSize(new Dimension(500,600));
          //		split.add(textPane,JSplitPane.LEFT);
		/*
		split.add(tablePane, JSplitPane.RIGHT);
        split.add(resultPane, JSplitPane.LEFT);

		split.setDividerSize(6); //���÷ָ����Ĵ�ϸ
		split.setDividerLocation(400); //���÷ָ�����λ�ã�����setPreferredSize�����е�ֵ
		split.setOneTouchExpandable(true);
		this.add(split);*/
		this.add(tablePane);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}
	
	

}
