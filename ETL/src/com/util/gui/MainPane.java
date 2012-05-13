package com.util.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainPane extends JPanel {
	
	private TextPanel textPane = new TextPanel();
	private ResultPane resultPane = new ResultPane();
	private JSplitPane split = new JSplitPane();
	
	public MainPane(){
		//textPane.setPreferredSize(new Dimension(400,500));
          //		split.add(textPane,JSplitPane.LEFT);
		
		split.add(textPane, JSplitPane.LEFT);
        split.add(resultPane, JSplitPane.RIGHT);

		split.setDividerSize(6); //���÷ָ����Ĵ�ϸ
		split.setDividerLocation(400); //���÷ָ�����λ�ã�����setPreferredSize�����е�ֵ
		split.setOneTouchExpandable(true);
		this.add(split);
	}

}
