package com.util.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel {
	
	static JTable table = null;
	 public static int fileNum = 0;
	
	public TablePanel(){
		String[] headers = { "文件类型", "文件名", "导入状态" };
		Object[][] cellData = null;

		DefaultTableModel model = new DefaultTableModel(cellData, headers) {

		  public boolean isCellEditable(int row, int column) {
		    return false;
		  }
		};

		table = new JTable(model);
	
		JScrollPane js =new JScrollPane(table);
		this.add(js);
	}
	
	public void fillTable(HashMap<String,ArrayList<String>> map){
		  DefaultTableModel tableModel = (DefaultTableModel) table
		  .getModel();
		  tableModel.setRowCount(0);// 清除原有行
		  table.setRowHeight(20);
		  
		  
		  // 填充数据
		  Iterator it = map.entrySet().iterator();
	      while (it.hasNext()) {
	    	    String[] arr = new String[3];
				java.util.Map.Entry en = (java.util.Map.Entry) it.next();
				// 返回与此项对应的键
				String dirname = (String) en.getKey();
				System.out.println("--->" + dirname);
				// 返回与此项对应的值
				ArrayList<String> list = (ArrayList<String>) en.getValue();
				fileNum += list.size();
				for(int loop = 0; loop < list.size(); loop++){
					String[] templist = list.get(loop).split("\\\\");
					String filename = templist[templist.length - 1];
					arr[0] = dirname;
					arr[1] = filename;
					arr[2] = "未导入";
					// 添加数据到表格
				    tableModel.addRow(arr);
				}
	 }
          // 更新表格
	 table.invalidate();
	 ProPanel.progress.setMaximum(fileNum);
	}
	
	
	
	public static  void setStatue(String userType,String filename, String status){
		 DefaultTableModel tableModel = (DefaultTableModel) table
		  .getModel();
		  
		  int rowCount=tableModel.getRowCount();
		  
		  for(int loop = 0; loop < rowCount; loop++){
			  String tempdir = (String)tableModel.getValueAt(loop, 0);
			  String tempfile = (String)tableModel.getValueAt(loop, 1);
			 
			  System.out.println(userType + " " + filename);
			  if(tempdir.equals(userType) &&
			     tempfile.equals(filename)){
				
				  tableModel.setValueAt(status, loop, 2);
			  }
		  }
	}

}
