package com.util.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainPane extends JPanel {
	
	 TablePanel tablePane = new TablePanel();
	 ResultPane resultPane = new ResultPane();
	 JSplitPane split = new JSplitPane();
	
	public MainPane(){
		//textPane.setPreferredSize(new Dimension(400,500));
          //		split.add(textPane,JSplitPane.LEFT);
		/*
		split.add(tablePane, JSplitPane.RIGHT);
        split.add(resultPane, JSplitPane.LEFT);

		split.setDividerSize(6); //���÷ָ����Ĵ�ϸ
		split.setDividerLocation(400); //���÷ָ�����λ�ã�����setPreferredSize�����е�ֵ
		split.setOneTouchExpandable(true);
		this.add(split);*/
		this.add(tablePane);
	}

}
