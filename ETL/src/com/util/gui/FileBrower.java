package com.util.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.util.export.AccessImp;
import com.util.export.AccessRead;
import com.util.export.HxlsImp;
import com.util.export.XxlsImp;
import com.util.tools.Log;
import com.util.tools.StringUtil;

public class FileBrower {

	public static ArrayList<String> list = new ArrayList<String>();
	public static ArrayList<String> dirList = new ArrayList<String>();
	public static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
   
	/**
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Log.info("start now");
		FileBrower entry = new FileBrower();
		// 遍历一级目录，获取类型
		//entry.dbDirBrower();
		// 遍历文件
		//entry.dbFileBrower();
		// 处理文件
		//entry.dbBrower();

		// entry.dbFileReadHead();
	}
	
	public static void fileBrower(String startDir){
	
		// 遍历一级目录，获取类型
		dbDirBrower(startDir);
		// 遍历文件
		dbFileBrower();
	}

	public static void dbDirBrower(String startDir) {
		File file = new File(startDir);
		String[] dirfile = file.list();

		for (int loop = 0; loop < dirfile.length; loop++) {
			// System.out.println(dirfile[loop]);
			File tempdir = new File(dirfile[loop]);
			if (tempdir.isDirectory()) {
				System.out.println(tempdir.getAbsolutePath());
				dirList.add(tempdir.getAbsolutePath());
			}
		}

	}

	public static void dbFileBrower() {

		for (int loop = 0; loop < dirList.size(); loop++) {
			File file = new File(dirList.get(loop));
			ArrayList<String> list = new ArrayList<String>();
			dbFile(file, list);
			if(list.size() == 0){
				continue;
			}
			System.out.println(" - "+dirList.get(loop));
			String[] dirname = dirList.get(loop).split("\\\\");
			System.out.println(dirname[dirname.length - 1]);
			map.put(dirname[dirname.length - 1], list);
		}
	}

	public static void dbFile(File file, ArrayList<String> list) {

		File[] listfile = file.listFiles();

		for (int loop = 0; loop < listfile.length; loop++) {
			if (listfile[loop].isDirectory()) {
				dbFile(listfile[loop], list);
			} else if (listfile[loop].getAbsolutePath().endsWith(".mdb")
					|| listfile[loop].getAbsolutePath().endsWith(".xls")
					|| listfile[loop].getAbsolutePath().endsWith(".xlsx")) {
				System.out.println(listfile[loop].getAbsolutePath());
				list.add(listfile[loop].getAbsolutePath());
			}

		}

	}

	public static void importDB() throws SQLException, IOException {
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry en = (java.util.Map.Entry) it.next();
			// 返回与此项对应的键
			String dirname = (String) en.getKey();
			System.out.println("--->" + dirname);
			// 返回与此项对应的值
			ArrayList<String> list = (ArrayList<String>) en.getValue();

			dbFileBrower(dirname, list);

		}

	}

	public static void dbFileBrower(String dirname, ArrayList<String> list) {
		Iterator it = list.iterator();

		String name = null;
		int count = 0;
		while (it.hasNext()) {
			name = (String) it.next();
		    System.out.println("name -->"+StringUtil.getFileName(name));
		    
		    TablePanel.setStatue(dirname,StringUtil.getFileName(name),"入库中");
			if (name.endsWith(".mdb")) {
				AccessRead access = new AccessImp();
				access.read(name, dirname);
				Log.error("process " + name + "success");
			} else if (name.endsWith(".xls")) {
				System.out.println(name);
				HxlsImp xls2csv;
				try {
					xls2csv = new HxlsImp(name);
					xls2csv.process(name, dirname);
					Log.error("process " + name + "success");
				} catch (FileNotFoundException e) {
					Log.error("process " + name + "failed" + e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					Log.error("process " + name + "failed" + e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					Log.error("process " + name + "failed" + e.getMessage());
					e.printStackTrace();
				}

			} else {

				try {
					XxlsImp howto = new XxlsImp();
					howto.process(name, dirname);
					Log.error("process " + name + "success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.error("process " + name + "failed" + e.getMessage());
					e.printStackTrace();
				}
			}
			
			TablePanel.setStatue(dirname,StringUtil.getFileName(name),"完成");
			count++;
			ProPanel.progress.setValue(count);
			String pro = "执行进度 "+(count/TablePanel.fileNum)* 100 +"%";
			ProPanel.progress.setString(pro);

		}
	}
	

}
